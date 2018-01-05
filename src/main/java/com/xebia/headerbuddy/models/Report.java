package com.xebia.headerbuddy.models;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

@JacksonXmlRootElement(localName = "report")
public class Report {

    // The logger
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //start url
    private String url;
    private Date date;
    @JacksonXmlElementWrapper(localName = "methods")
    @JacksonXmlProperty(localName = "method")
    private List<String> methods;
    @JacksonXmlElementWrapper(localName = "headers")
    @JacksonXmlProperty(localName = "header")
    private List<Header> headers;

    public Report(final String url) {
        this.url = url;
        this.date = new Date();
        this.headers = new ArrayList<>();
        this.methods = new ArrayList<>();
        // log that the report was created
        logger.info("Report created");
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
