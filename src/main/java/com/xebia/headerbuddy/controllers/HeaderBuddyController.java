package com.xebia.headerbuddy.controllers;

import com.xebia.headerbuddy.annotations.ValidOutput;
import com.xebia.headerbuddy.annotations.ValidURL;
import com.xebia.headerbuddy.models.ApiKey;
import com.xebia.headerbuddy.models.CertificateDetails;
import com.xebia.headerbuddy.models.CustomErrorModel;
import com.xebia.headerbuddy.models.Header;
import com.xebia.headerbuddy.models.HeaderAnalyzer;
import com.xebia.headerbuddy.models.entities.Ereport;
import com.xebia.headerbuddy.models.entities.Eurl;
import com.xebia.headerbuddy.models.entities.Euser;
import com.xebia.headerbuddy.models.entities.Evalue;
import com.xebia.headerbuddy.models.entities.repositories.EprofileRepository;
import com.xebia.headerbuddy.models.entities.repositories.EreportRepository;
import com.xebia.headerbuddy.models.entities.repositories.EurlRepository;
import com.xebia.headerbuddy.models.entities.repositories.EvalueRepository;
import com.xebia.headerbuddy.models.requestmethods.GetRequest;
import com.xebia.headerbuddy.utilities.ProtocolHandler;
import com.xebia.headerbuddy.utilities.WebCrawler;
import com.xebia.headerbuddy.utilities.MethodHandler;
import com.xebia.headerbuddy.utilities.ValueSerializer;
import com.xebia.headerbuddy.utilities.UrlSerializer;
import com.xebia.headerbuddy.utilities.APIKeyGenerator;
import com.xebia.headerbuddy.annotations.ValidAPIKey;
import com.xebia.headerbuddy.annotations.ValidEmail;
import com.xebia.headerbuddy.annotations.ValidMethod;
import com.xebia.headerbuddy.models.entities.repositories.EuserRepository;
import org.rythmengine.Rythm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@RestController
@Validated
public class HeaderBuddyController {

    // The logger
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EvalueRepository valueRepository;

    @Autowired
    private EuserRepository userRepository;

    @Autowired
    private EreportRepository reportRepository;

    @Autowired
    private EurlRepository urlRepository;

    @Autowired
    private EprofileRepository profileRepository;

    @Autowired
    private Environment env;

    @ApiOperation(value = "Creates a report of your service based on the response headers")
    @ApiResponses(value = {
            @ApiResponse(code = org.apache.http.HttpStatus.SC_BAD_REQUEST, message = "Failed on wrong input (parameter)", response = CustomErrorModel.class),
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "Success!", response = Ereport.class)
    })
    @RequestMapping(value = "/headerbuddy/api", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_HTML_VALUE})
    public ResponseEntity headerBuddy(
            @ApiParam(value = "The url to the service", required = true)
        @RequestParam(value = "url", required = true) @ValidURL String url,
            @ApiParam(value = "The api key")
        @RequestParam(value = "key", required = false) @ValidAPIKey String key,
            @ApiParam("The respose output type (xml, json)")
        @RequestParam(value = "output", defaultValue = "json", required = false) @ValidOutput String output,
            @ApiParam(value = "The methods used")
        @RequestParam(value = "method", defaultValue = "get", required = false) @ValidMethod String method,
            @ApiParam("The crawler")
        @RequestParam(value = "crawl", defaultValue = "false", required = false) boolean crawl) throws Exception {

        Set<String> visitedPages = new HashSet<>();

        if (crawl) {
            WebCrawler crawler = new WebCrawler(url);
            crawler.crawl(Integer.parseInt(env.getProperty("webcrawler.limit")));
            visitedPages = crawler.getVisitedPages();
        } else {
            visitedPages.add(url);
        }

        //Get the correct values by protocol profile.
        Set<Evalue> correctProtocolValues = ProtocolHandler.getEvaluesByProtocol(visitedPages, profileRepository.findAll(), valueRepository.findAll());

        List<Header> foundHeaders = MethodHandler.executeGivenMethod(MethodHandler.getAllMethodsFromMethodParam(method), visitedPages);

        Set<Evalue> foundValues = ValueSerializer.convertToEvalue(foundHeaders);
        Set<Eurl> visitedUrls = UrlSerializer.convertToEurl(visitedPages);
        HeaderAnalyzer headerAnalyzer = new HeaderAnalyzer(foundValues, correctProtocolValues);

        // Perform the actual analysis
        Euser user;
        if (key != null) {
            user = userRepository.findByApikey(key);
        } else {
            user = null;
        }

        Ereport report = new Ereport(user, headerAnalyzer.analyseHeaders());
        report.setProfile(ProtocolHandler.getUsedProtocol());
        report.setUrls(visitedUrls);

        // Save data
        reportRepository.save(report);

        for (Eurl visitedUrl : visitedUrls) {
            visitedUrl.setReport(report);
            urlRepository.save(visitedUrl);
        }

        //check certificate
        Optional<CertificateDetails> certificate = new GetRequest(url).getCertificateDetails();
        if (certificate.isPresent()) {
            report.setNote("Certificate end date: " + certificate.get().getExpireDate());
        }

        // log that the report was saved
        logger.info("report saved");

        if (output.equalsIgnoreCase("html")) {
            // Get html file from resources
            ClassLoader cl = getClass().getClassLoader();
            File htmlReport = new File(cl.getResource("report.html").getFile());

            // Return rendered file
            return new ResponseEntity(Rythm.render(htmlReport, report), HttpStatus.OK);
        }

        return new ResponseEntity(report, HttpStatus.OK);
    }

    @ApiOperation(value = "Creates an API key for the usage of the API")
    @ApiResponses(value = {
            @ApiResponse(code = org.apache.http.HttpStatus.SC_BAD_REQUEST, message = "Failed on wrong input (parameter)", response = CustomErrorModel.class),
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "Success!", response = ApiKey.class)
    })
    @RequestMapping(value = "/headerbuddy/key", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity requestApiKey(@ApiParam(value = "An email-address", required = true) @RequestParam(value = "email", required = true) @ValidEmail String email) throws Exception {
        // Get the api key
        ApiKey key = APIKeyGenerator.getKey(userRepository, email);

        // log that a response entity is being created
        logger.info("response entity is going to be created");

        return new ResponseEntity(key, HttpStatus.OK);
    }
}
