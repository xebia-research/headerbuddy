package com.xebia.headerbuddy.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Report {
    //start url
    private String url;
    private Date date;
    private List<Header> headers;

    public Report(String url) {
        this.url = url;
        this.date = new Date();
        this.headers = new ArrayList<>();
    }

    public String getUrl() {
        return this.url;
    }

    public String getDate() {
        return this.date.toString();
    }

    public List<Header> getHeaders() {
        return this.headers;
    }

    public void addHeader(List<Header> headerList) {
        headers.addAll(headerList);
    }

    public void addHeader(Header header) {
        headers.add(header);
    }
}