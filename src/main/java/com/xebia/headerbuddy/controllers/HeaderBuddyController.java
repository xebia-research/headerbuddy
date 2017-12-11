package com.xebia.headerbuddy.controllers;

import com.xebia.headerbuddy.models.Header;
import com.xebia.headerbuddy.models.entities.Ecategory;
import com.xebia.headerbuddy.models.entities.repositories.EcategoryRepository;
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
    @Autowired
    private EcategoryRepository ecategoryRepository;


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

    //Can be deleted later, this shows how to communicatie with the database
    @GetMapping(path="/add")
    public @ResponseBody String addNewEcategory (@RequestParam String name) {
        Ecategory n = new Ecategory(name);
        ecategoryRepository.save(n);
        return "Saved";
    }

    @GetMapping(path="/name")
    public @ResponseBody Iterable<Ecategory> getAllEcategoryByName(@RequestParam String name) {
        // This returns a JSON or XML with the users
        return ecategoryRepository.findCategoryByName(name);
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Ecategory> getAllEcategories() {
        // This returns a JSON or XML with the users
        return ecategoryRepository.findAll();
    }

}
