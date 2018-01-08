package com.xebia.headerbuddy.controllers;

import com.xebia.headerbuddy.annotations.ValidOutput;
import com.xebia.headerbuddy.annotations.ValidURL;
import com.xebia.headerbuddy.models.HeaderAnalyzer;
import com.xebia.headerbuddy.models.Header;
import com.xebia.headerbuddy.models.entities.Ereport;
import com.xebia.headerbuddy.models.entities.Eurl;
import com.xebia.headerbuddy.models.entities.Euser;
import com.xebia.headerbuddy.models.entities.Evalue;
import com.xebia.headerbuddy.models.entities.repositories.EreportRepository;
import com.xebia.headerbuddy.models.entities.repositories.EurlRepository;
import com.xebia.headerbuddy.models.entities.repositories.EvalueRepository;
import com.xebia.headerbuddy.utilities.WebCrawler;
import com.xebia.headerbuddy.utilities.MethodHandler;
import com.xebia.headerbuddy.utilities.ValueSerializer;
import com.xebia.headerbuddy.utilities.UrlSerializer;
import com.xebia.headerbuddy.utilities.APIKeyGenerator;
import com.xebia.headerbuddy.annotations.ValidAPIKey;
import com.xebia.headerbuddy.annotations.ValidEmail;
import com.xebia.headerbuddy.annotations.ValidMethod;
import com.xebia.headerbuddy.models.ApiKey;
import com.xebia.headerbuddy.models.entities.repositories.EuserRepository;
import org.rythmengine.Rythm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@Validated
public class HeaderBuddyController {

    @Autowired
    private EvalueRepository valueRepository;

    @Autowired
    private EuserRepository userRepository;

    @Autowired
    private EreportRepository reportRepository;

    @Autowired
    private EurlRepository urlRepository;

    @RequestMapping(value = "/headerbuddy/api")
    public ResponseEntity headerBuddy(@RequestParam(value = "url", required = true) @ValidURL String url,
                                              @RequestParam(value = "key", required = true) @ValidAPIKey String key,
                                              @RequestParam(value = "output", defaultValue = "json", required = false) @ValidOutput String output,
                                              @RequestParam(value = "method", defaultValue = "get", required = false) @ValidMethod String method,
                                              @RequestParam(value = "crawl", defaultValue = "false", required = false) boolean crawl) throws Exception {

        List<Header> foundHeaders = new ArrayList<>();
        Set<String> visitedPages = new HashSet<>();

        if (crawl) {

            WebCrawler crawler = new WebCrawler(url);
            crawler.crawl();
            visitedPages = crawler.getVisitedPages();
        } else {
            visitedPages.add(url);
        }

        List<String> methodsInParameter = MethodHandler.getAllMethodsFromMethodParam(method);

        for (String methodInParameter : methodsInParameter) {
            List<Header> headers = MethodHandler.executeGivenMethod(methodInParameter, url);
            foundHeaders.addAll(headers);
        }

        Set<Evalue> foundValues = ValueSerializer.convertToEvalue(foundHeaders);
        Set<Eurl> visitedUrls = UrlSerializer.convertToEurl(visitedPages);
        HeaderAnalyzer headerAnalyzer = new HeaderAnalyzer(foundValues, valueRepository.findAll());

        // Perform the actual analysis
        Euser user = userRepository.findByApikey(key);
        Ereport report = headerAnalyzer.analyseHeaders(user);
        report.setUrls(visitedUrls);

        // Save data
        reportRepository.save(report);
        for (Eurl visitedUrl : visitedUrls) {
            visitedUrl.setReport(report);
            urlRepository.save(visitedUrl);
        }

        if (output.equalsIgnoreCase("html")) {
            // Get html file from resources
            ClassLoader cl = getClass().getClassLoader();
            File htmlReport = new File(cl.getResource("report.html").getFile());

            // Return rendered file
            return new ResponseEntity(Rythm.render(htmlReport, report), HttpStatus.OK);
        }

        return new ResponseEntity(report, HttpStatus.OK);
    }

    @RequestMapping(value = "/headerbuddy/key")
    public ResponseEntity requestApiKey(@RequestParam(value = "email", required = true) @ValidEmail String email) throws Exception {
        // Get the api key
        ApiKey key = APIKeyGenerator.getKey(userRepository, email);

        return new ResponseEntity(key, HttpStatus.OK);
    }
}
