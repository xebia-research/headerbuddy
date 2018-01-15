package com.xebia.headerbuddy.utilities;

import com.xebia.headerbuddy.models.Header;
import com.xebia.headerbuddy.models.RequestBehaviour;
import org.apache.commons.lang.WordUtils;
import org.eclipse.jgit.util.StringUtils;
import org.reflections.Reflections;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public final class MethodHandler {

    private MethodHandler() {
        //utility class
    }

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
        Collections.addAll(methodsToPerform, StringUtils.toLowerCase(method).split(","));

        // Get our supported http method classes
        Reflections reflections = new Reflections("com.xebia.headerbuddy.models");
        Set<Class<? extends RequestBehaviour>> methodClasses = reflections.getSubTypesOf(RequestBehaviour.class);

        // If all the methods are requested
        if (methodsToPerform.contains("all")) {
            for (Class<? extends RequestBehaviour> methodClass : methodClasses) {
                classNames.add(methodClass.getSimpleName());
            }

            return classNames;
        }

        for (String methodToPerform : methodsToPerform) {
            // Create string to match the 'simple' name of the class (get becomes 'GetRequest')
            classNames.add(WordUtils.capitalize(methodToPerform.substring(0, 1))
                    + StringUtils.toLowerCase(methodToPerform.substring(1)) + "Request");
        }

        return classNames;
    }

    /*
     * @param {requestMethods} names of a class in the requestmethods package;
     * @param {urls} the urls from the report
     * @return {List<header>} it returns the outcome from the doRequest method which is a list of headers
     * Makes an instance of the class names and executes the doRequest method
     */
    public static List<Header> executeGivenMethod(List<String> requestMethods, Set<String> urls) throws Exception {
        List<Header> foundHeaders = new ArrayList<>();

        for (String requestMethod : requestMethods) {

            // Setup a new instance of the requested class
            Class classInst = Class.forName("com.xebia.headerbuddy.models.requestmethods." + requestMethod);

            // Execute request for each url
            for (String url : urls) {

                // Create new instance of found class
                RequestBehaviour instance = (RequestBehaviour) classInst.getConstructor(String.class).newInstance(url);
                foundHeaders.addAll(instance.doRequest());
            }
        }

        return foundHeaders;
    }
}
