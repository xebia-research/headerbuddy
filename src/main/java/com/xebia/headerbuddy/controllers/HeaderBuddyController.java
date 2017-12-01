package com.xebia.headerbuddy.controllers;


import com.xebia.headerbuddy.models.*;
import com.xebia.headerbuddy.models.requestmethods.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HeaderBuddyController {

    @RequestMapping("/headerbuddy/api")
    public Report headerBuddy(@RequestParam(value = "url", required = true) String url) {
        Report report = new Report(url);
        try {
           List<Header> t = new GetRequest(url).doRequest();
           report.addHeader(t);
           return report;

        } catch (Exception e) {
            System.out.println("Message:  " + e.getMessage());
            return report;
        }
    }

}
