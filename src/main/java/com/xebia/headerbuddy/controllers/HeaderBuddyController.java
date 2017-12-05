package com.xebia.headerbuddy.controllers;

import com.xebia.headerbuddy.annotations.ValidAPIKey;
import com.xebia.headerbuddy.annotations.ValidMethod;
import com.xebia.headerbuddy.annotations.ValidURL;
import com.xebia.headerbuddy.models.*;
import com.xebia.headerbuddy.models.requestmethods.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Pattern;
import java.util.List;

@RestController
@Validated
public class HeaderBuddyController {

    // Regex to validate given output type (only accepts "json", "xml" or "html" (capitalization doesn't matter))
    private final String REGEX_OUTPUT = "(?i)^(json|xml|html)$";

    @RequestMapping("/headerbuddy/api")
    public Report headerBuddy(@RequestParam(value = "url", required = true) @ValidURL String url,
                              @RequestParam(value = "key", required = true) @ValidAPIKey String key,
                              @RequestParam(value = "output", defaultValue = "json", required = false) @Pattern(regexp = REGEX_OUTPUT) String output,
                              @RequestParam(value = "method", defaultValue = "get", required = false) @ValidMethod String method,
                              @RequestParam(value = "spider", defaultValue = "false", required = false) boolean spider) {
        Report report = new Report(url);

        try {
           List<Header> headersFromRequest = new GetRequest(url).doRequest();
           report.addHeaders(headersFromRequest);
           return report;

        } catch (Exception e) {
            System.out.println("Message:  " + e.getMessage());
            return report;
        }
    }
}
