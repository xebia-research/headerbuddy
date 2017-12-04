package com.xebia.headerbuddy.controllers;


import com.xebia.headerbuddy.models.*;
import com.xebia.headerbuddy.models.entities.*;
import com.xebia.headerbuddy.models.entities.EheaderRepository;
import com.xebia.headerbuddy.models.requestmethods.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HeaderBuddyController {
    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private EheaderRepository eheaderRepository;


    @RequestMapping("/headerbuddy/api")
    public List<Header> headerBuddy(@RequestParam(value = "url", required = true) String url) {
        List<Header> t;
        try {
            t = new GetRequest(url).doRequest();
            return t;

        } catch (Exception e) {
            System.out.println("Message:  " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @GetMapping(path="/add")
    public @ResponseBody String addNewEheader (@RequestParam String name) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        Eheader n = new Eheader();
        n.setName(name);
        eheaderRepository.save(n);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Eheader> getAllEheaders() {
        // This returns a JSON or XML with the users
        return eheaderRepository.findAll();
    }

}
