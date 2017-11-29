package com.xebia.headerbuddy.controllers;


import com.xebia.headerbuddy.models.*;
import com.xebia.headerbuddy.models.requestmethods.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HeaderBuddyController
{

    @RequestMapping("/headerbuddy/api")
    public List<Header> headerBuddy(@RequestParam(value = "url", required = true) String url)
    {
        List<Header> t;
        try{
            t = new GetRequest(url).doRequest();
            return t;

        }catch (Exception e){
            System.out.println("Message:  "  + e.getMessage());
            return t = new ArrayList<>();
        }
    }

}
