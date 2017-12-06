package com.xebia.headerbuddy.controllers;

import com.xebia.headerbuddy.annotations.ValidAPIKey;
import com.xebia.headerbuddy.annotations.ValidMethod;
import com.xebia.headerbuddy.annotations.ValidURL;
import com.xebia.headerbuddy.models.*;
import com.xebia.headerbuddy.models.requestmethods.*;
import org.reflections.Reflections;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Set;

@RestController
@Validated
public class HeaderBuddyController{

    // Regex to validate given output type (only accepts "json", "xml" or "html" (capitalization doesn't matter))
    private final String REGEX_OUTPUT = "(?i)^(json|xml|html)$";

    @RequestMapping("/headerbuddy/api")
    public Report headerBuddy(@RequestParam(value = "url", required = true) @ValidURL String url,
                              @RequestParam(value = "key", required = true) @ValidAPIKey String key,
                              @RequestParam(value = "output", defaultValue = "json", required = false) @Pattern(regexp = REGEX_OUTPUT) String output,
                              @RequestParam(value = "method", defaultValue = "get", required = false) @ValidMethod String method,
                              @RequestParam(value = "spider", defaultValue = "false", required = false) boolean spider)
            throws Exception {

        // Get methods to perform
        String[] methodsToPerform = method.split(",");

        // Get supported http method classes
        Reflections reflections = new Reflections("com.xebia.headerbuddy.models");
        Set<Class<? extends RequestBehaviour>> methodClasses = reflections.getSubTypesOf(RequestBehaviour.class);

        // Create string to match the 'simple' name of the class (get becomes 'GetRequest')
        // For now only the first method in the list is executed
        // TODO support multiple request methods
        String firstMethod = methodsToPerform[0].substring(0,1).toUpperCase() + methodsToPerform[0].substring(1).toLowerCase() + "Request";

        // Create Report
        Report report = new Report(url);

        for(Class<? extends RequestBehaviour> methodClass : methodClasses){
            // If class names match create instance
            if (methodClass.getSimpleName().equals(firstMethod)){
                // Create new instance of found class and save outcome
                List<Header> headersFromRequest = methodClass.getConstructor(String.class).newInstance(url).doRequest();

                // Add outcome to report and return the report
                report.addHeaders(headersFromRequest);

                // Return the report in requested format
                // TODO make prettier
                switch (output){
                    case "json":
                        return report;
                    case "xml":
                        // TODO support XML
                        throw new Exception("XML output not supported!");
                    case "html":
                        // TODO support HTML
                        throw new Exception("HTML output not supported!");
                }
            }
        }

        // Program should never get this far
        throw new Exception("Oops! Something went wrong.");
    }
}
