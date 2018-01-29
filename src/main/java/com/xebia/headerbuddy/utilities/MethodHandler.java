package com.xebia.headerbuddy.utilities;

import com.xebia.headerbuddy.models.Header;
import com.xebia.headerbuddy.models.HttpRequestMethod;
import com.xebia.headerbuddy.models.RequestBehaviour;
import org.eclipse.jgit.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public final class MethodHandler {

    private MethodHandler() {
        //utility class
    }

    /*
     * @param {method} String of method(s) divided by a ,
     * @return {List<HttpRequestMethod>}
     * Returns a list of all the HttpRequestMethods that are requested in the method parameter
     */
    public static List<HttpRequestMethod> getAllMethodsFromMethodParam(String method) {
        // Get requested methods to perform from the url
        List<String> methodsToPerform = new ArrayList<>();
        Collections.addAll(methodsToPerform, StringUtils.toLowerCase(method).split(","));

        // List of our httpRequestMethods
        List<HttpRequestMethod> classNames = new ArrayList<>();

        // If all the methods are requested
        if (methodsToPerform.contains("all")) {
            classNames = Arrays.asList(HttpRequestMethod.values());
            return classNames;
        }

        for (String methodToPerform : methodsToPerform) {
            // Create string to match the 'simple' name of the class (get becomes 'GetRequest')
            classNames.add(HttpRequestMethod.valueOf(methodToPerform.toUpperCase()));
        }

        return classNames;
    }

    /*
     * @param {requestMethods} All the requested HttpRequestMethods;
     * @param {urls} the urls from the report
     * @return {List<header>} it returns the outcome from the doRequest method which is a list of headers
     * Makes an instance of the class names and executes the doRequest method
     */
    public static List<Header> executeGivenMethod(List<HttpRequestMethod> requestMethods, Set<String> urls) throws Exception {
        List<Header> foundHeaders = new ArrayList<>();

        for (HttpRequestMethod requestMethod : requestMethods) {
            // Execute request for each url
            for (String url : urls) {

                // Create a instance for the call
                RequestBehaviour instance = new RequestBehaviour(url, requestMethod);
                foundHeaders.addAll(instance.doRequest());
            }
        }

        return foundHeaders;
    }
}
