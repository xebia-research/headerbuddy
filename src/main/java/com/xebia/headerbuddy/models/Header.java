package com.xebia.headerbuddy.models;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Header {
    private Optional<String> name;
    @JacksonXmlElementWrapper(localName = "values")
    @JacksonXmlProperty(localName = "value")
    private List<String> values;
    private List<String> urls;

    public Header(String name, List<String> values, String site) {
        this.name = Optional.ofNullable(name);
        this.values = values;

        this.urls = new ArrayList<String>();
        this.addUrl(site);
    }

    public String getName() {
        if (!this.name.isPresent()) {
            return "UNSPECIFIED";
        }
        return this.name.get();
    }

    public void setName(String name) {
        this.name = Optional.ofNullable(name);
    }

    public List<String> getValues() {
        return this.values;
    }

    public void addValue(String value) {
        this.values.add(value);
    }

    public List<String> getUrls() {
        return this.urls;
    }

    public void addUrl(String url) {
        this.urls.add(url);
    }

}
