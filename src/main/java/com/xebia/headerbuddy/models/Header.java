package com.xebia.headerbuddy.models;

import java.util.ArrayList;
import java.util.List;

public class Header {
    private String name;
    private List<String> values;
    private List<String> urls;

    public Header(String name, List<String> values, String site) {
        this.name = name;
        this.values = values;

        this.urls = new ArrayList<String>();
        this.addUrl(site);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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
