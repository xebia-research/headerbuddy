package com.xebia.headerbuddy.controllers;

import com.xebia.headerbuddy.annotations.ValidAPIKey;
import com.xebia.headerbuddy.annotations.ValidMethod;
import com.xebia.headerbuddy.annotations.ValidOutput;
import com.xebia.headerbuddy.annotations.ValidURL;
import com.xebia.headerbuddy.models.Analyzer;
import com.xebia.headerbuddy.models.Header;
import com.xebia.headerbuddy.models.Report;
import com.xebia.headerbuddy.models.entities.Ereport;
import com.xebia.headerbuddy.models.entities.Euser;
import com.xebia.headerbuddy.models.entities.Evalue;
import com.xebia.headerbuddy.models.entities.repositories.EvalueRepository;
import com.xebia.headerbuddy.utilities.MethodHandler;
import com.xebia.headerbuddy.utilities.ValueSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Set;

@RestController
@Validated
public class HeaderBuddyController {

    @Autowired
    private EvalueRepository valueRepository;

    private Report report;

    @RequestMapping(value = "/headerbuddy/api")
    public ResponseEntity headerBuddy(@RequestParam(value = "url", required = true) @ValidURL String url,
                                              @RequestParam(value = "key", required = true) @ValidAPIKey String key,
                                              @RequestParam(value = "output", defaultValue = "json", required = false) @ValidOutput String output,
                                              @RequestParam(value = "method", defaultValue = "get", required = false) @ValidMethod String method,
                                              @RequestParam(value = "spider", defaultValue = "false", required = false) boolean spider) throws Exception {
        // Create Report
        this.report = new Report(url);

        try {
            List<String> methodsInParameter = MethodHandler.getAllMethodsFromMethodParam(method);

            for (String methodInParameter : methodsInParameter) {
                List<Header> headers = MethodHandler.executeGivenMethod(methodInParameter, this.report.getUrl());
                this.report.addHeaders(headers);
                this.report.addMethod(methodInParameter);
            }
        } catch (Exception e) {
            System.out.println("Message: " + e.getMessage());
        }

        return new ResponseEntity(this.report, HttpStatus.OK);
    }

    //Test, needs to be removed before merge with develop
    @RequestMapping(value = "/test")
    public ResponseEntity test(@RequestParam(value = "url", required = true) @ValidURL String url,
                        @RequestParam(value = "key", required = true) @ValidAPIKey String key,
                        @RequestParam(value = "output", defaultValue = "json", required = false) @ValidOutput String output,
                        @RequestParam(value = "method", defaultValue = "get", required = false) @ValidMethod String method,
                        @RequestParam(value = "spider", defaultValue = "false", required = false) boolean spider) throws Exception {
        // Create Report
        this.report = new Report(url);

        try {
            List<String> methodsInParameter = MethodHandler.getAllMethodsFromMethodParam(method);

            for (String methodInParameter : methodsInParameter) {
                List<Header> headers = MethodHandler.executeGivenMethod(methodInParameter, this.report.getUrl());
                this.report.addHeaders(headers);
                this.report.addMethod(methodInParameter);
            }

        } catch (Exception e) {
            System.out.println("Message: " + e.getMessage());
        }

        Set<Evalue> foundValues = ValueSerializer.convertToEvalue(report.getHeaders());
        Analyzer analyzer = new Analyzer(foundValues, valueRepository.findAll());

        Euser user = new Euser("12345", "test@gmail.com");

        Ereport reporte = analyzer.analyseHeaders(user);
        return new ResponseEntity(reporte, HttpStatus.OK);
    }
}
