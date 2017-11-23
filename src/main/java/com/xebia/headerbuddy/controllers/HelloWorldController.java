package com.xebia.headerbuddy.controllers;


import com.xebia.headerbuddy.models.Hello;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController
{

    @RequestMapping("/helloworld")
    public Hello helloworld(@RequestParam(value = "name", defaultValue = "World") String name)
    {
        return new Hello(1, "Hello " + name);
    }

}
