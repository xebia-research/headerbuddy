package com.xebia.headerbuddy.controllers;


import com.xebia.headerbuddy.models.requestmethods.GetRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class HeaderBuddyController
{

    @RequestMapping("/headerbuddy/api")
    public String headerBuddy(@RequestParam(value = "url", required = true) String url)
    {
        try{
            Map<String, List<String>> t = new GetRequest(url).doRequest();
            return t.toString();

        }catch (Exception e){
            System.out.println("Message:  "  + e.getMessage());
            return e.getMessage();
        }
    }

}
