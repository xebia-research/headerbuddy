package com.xebia.headerbuddy.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Report {
    //start url
    private String url;
    private Date date;
    private List<String> methods;
    private List<Header> headers;

    public Report(String url) {
        this.url = url;
        this.date = new Date();
        this.headers = new ArrayList<>();
        this.methods = new ArrayList<>();
    }

    public String getUrl() {
        return this.url;
    }

    public String getDate() {
        return this.date.toString();
    }

    public List<String> getMethods() {
        return this.methods;
    }

    public List<Header> getHeaders() {
        return this.headers;
    }

    public void addHeaders(List<Header> headerList) {
        // TODO there is not a checker yet for duplicates
        headers.addAll(headerList);
    }

    public void addHeader(Header header) {
        headers.add(header);
    }

    public void addMethod(String method) {
        methods.add(method);
    }

}
