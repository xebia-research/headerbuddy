package com.xebia.headerbuddy.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeaderBuddyController
{

    @RequestMapping("/headerbuddy/api")
    public String headerBuddy(@RequestParam(value = "url", required = true) String url)
    {
        return "Hello World";
    }

}
