package com.xebia.headerbuddy.controllers;

import com.xebia.headerbuddy.models.ValidURL;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
public class HeaderBuddyController
{
    @RequestMapping("/headerbuddy/api")
    public String headerbuddy(@ValidURL(message = "Custom message") @RequestParam(value = "url", required = true) String url)
    {
        return url;
    }
}
