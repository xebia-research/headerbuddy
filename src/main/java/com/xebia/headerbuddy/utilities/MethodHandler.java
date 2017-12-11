package com.xebia.headerbuddy.utilities;

import com.xebia.headerbuddy.models.Header;
import com.xebia.headerbuddy.models.RequestBehaviour;
import org.reflections.Reflections;

import java.util.*;

public class MethodHandler {

    /*
    * @param {method} String of method(s) divided by a ,
    * @return {List<String>}
    * Returns a list of all the class names that are requested in the method parameter
    */
    public static List<String> getAllMethodsFromMethodParam(String method) {
        // List of the names of our request classes that the user wants to use
        List<String> classNames = new ArrayList<>();

        // Get requested methods to perform from the url
        List<String> methodsToPerform = new ArrayList<>();
        Collections.addAll(methodsToPerform, method.toLowerCase().split(","));

        // Get our supported http method classes
        Reflections reflections = new Reflections("com.xebia.headerbuddy.models");
        Set<Class<? extends RequestBehaviour>> methodClasses = reflections.getSubTypesOf(RequestBehaviour.class);

        // If all the methods are requested
        if(methodsToPerform.contains("all")) {
            for (Class<? extends RequestBehaviour> methodClass : methodClasses) {
                classNames.add(methodClass.getSimpleName());
            }

            return classNames;
        }

        for (String methodToPerform : methodsToPerform) {
            // Create string to match the 'simple' name of the class (get becomes 'GetRequest')
            classNames.add(methodToPerform.substring(0,1).toUpperCase() + methodToPerform.substring(1).toLowerCase() + "Request");
        }

        return classNames;
    }

    /*
    * @param {requestMethod} name of a class in the requestmethods package;
    * @param {url} the url from the report
    * @return {List<header>} it returns the outcome from the doRequest method which is a list of headers
    * Makes an instance of the class names and executes the doRequest method
    */

    public static List<Header> executeGivenMethod(String requestMethod, String url) throws Exception {

        // Setup a new instance of the requested class
        Class classInst = Class.forName("com.xebia.headerbuddy.models.requestmethods." + requestMethod);

        // Create new instance of found class
        RequestBehaviour instance = (RequestBehaviour) classInst.getConstructor(String.class).newInstance(url);
        return instance.doRequest();
    }
}
