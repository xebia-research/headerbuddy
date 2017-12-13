package com.xebia.headerbuddy.controllers;


import com.xebia.headerbuddy.annotations.ValidAPIKey;
import com.xebia.headerbuddy.annotations.ValidMethod;
import com.xebia.headerbuddy.annotations.ValidURL;
import com.xebia.headerbuddy.utilities.APIKeyGenerator;
import com.xebia.headerbuddy.utilities.MethodHandler;
import org.springframework.validation.annotation.Validated;
import com.xebia.headerbuddy.models.Header;
import com.xebia.headerbuddy.models.Report;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.constraints.Pattern;
import java.util.List;

@RestController
@Validated
public class HeaderBuddyController {

    // Regex to validate given output type (only accepts "json", "xml" or "html" (capitalization doesn't matter))
    static final String REGEX_PARAM_OUTPUT = "(?i)^(json|xml|html)$";

    private Report report;

    @RequestMapping("/headerbuddy/api")
    public Report headerBuddy(@RequestParam(value = "url", required = true) @ValidURL String url,
                              @RequestParam(value = "key", required = true) @ValidAPIKey String key,
                              @RequestParam(value = "output", defaultValue = "json", required = false) @Pattern(regexp = REGEX_PARAM_OUTPUT, message = "Output is not recognized") String output,
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
        } catch(Exception e) {
            System.out.println("Message: " + e.getMessage());
        }

        return this.report;
    }

    @RequestMapping("/headerbuddy/key")
    public String requestApiKey(){
        return APIKeyGenerator.generate();
    }
}
