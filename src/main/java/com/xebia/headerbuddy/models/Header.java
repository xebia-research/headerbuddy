package com.xebia.headerbuddy.models;

import java.util.ArrayList;
import java.util.List;

public class Header {
    private String name;
    private List<String> values;
    private List<String> websites;

    public Header(String name, List<String> values, String site) {
        this.name = name;
        this.values = values;

        this.websites = new ArrayList<String>();
        this.addWebsite(site);
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

    public List<String> getWebsites() {
        return this.websites;
    }

    public void addWebsite(String url) {
        this.websites.add(url);
    }
}
