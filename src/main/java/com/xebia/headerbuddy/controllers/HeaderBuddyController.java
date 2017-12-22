package com.xebia.headerbuddy.controllers;

import com.xebia.headerbuddy.annotations.ValidOutput;
import com.xebia.headerbuddy.annotations.ValidURL;
import com.xebia.headerbuddy.annotations.ValidAPIKey;
import com.xebia.headerbuddy.annotations.ValidEmail;
import com.xebia.headerbuddy.annotations.ValidMethod;
import com.xebia.headerbuddy.models.ApiKey;
import com.xebia.headerbuddy.models.entities.repositories.EuserRepository;
import com.xebia.headerbuddy.utilities.APIKeyGenerator;
import com.xebia.headerbuddy.utilities.MethodHandler;
import com.xebia.headerbuddy.utilities.WebCrawler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import com.xebia.headerbuddy.models.Header;
import com.xebia.headerbuddy.models.Report;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Validated
public class HeaderBuddyController {

    private Report report;

    @Autowired
    private EuserRepository userRepository;

    @RequestMapping(value = "/headerbuddy/api")
    public ResponseEntity headerBuddy(@RequestParam(value = "url", required = true) @ValidURL String url,
                                              @RequestParam(value = "key", required = true) @ValidAPIKey String key,
                                              @RequestParam(value = "output", defaultValue = "json", required = false) @ValidOutput String output,
                                              @RequestParam(value = "method", defaultValue = "get", required = false) @ValidMethod String method,
                                              @RequestParam(value = "crawl", defaultValue = "false", required = false) boolean crawl) throws Exception {

        // TODO integrate this in the report and make sure the found urls are also called
        if (crawl){
            WebCrawler crawler = new WebCrawler(url);
            crawler.crawl();
        }

        this.report = new Report(url);

        List<String> methodsInParameter = MethodHandler.getAllMethodsFromMethodParam(method);

        for (String methodInParameter : methodsInParameter) {
            List<Header> headers = MethodHandler.executeGivenMethod(methodInParameter, this.report.getUrl());
            this.report.addHeaders(headers);
            this.report.addMethod(methodInParameter);
        }

        return new ResponseEntity(this.report, HttpStatus.OK);
    }

    @RequestMapping(value = "/headerbuddy/key")
    public ResponseEntity requestApiKey(@RequestParam(value = "email", required = true) @ValidEmail String email) throws Exception{
        // Get the api key
        ApiKey key = APIKeyGenerator.getKey(userRepository, email);

        return new ResponseEntity(key, HttpStatus.OK);
    }
}
