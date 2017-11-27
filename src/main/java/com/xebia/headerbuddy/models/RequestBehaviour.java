package com.xebia.headerbuddy.models;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.List;

public abstract class RequestBehaviour {
    public String url;
    public String methodName;

    public RequestBehaviour(String url, String methodName){
        this.url = url;
        this.methodName = methodName;
    }

    public Map<String, List<String>> doRequest() throws Exception {
        URL target = new URL(this.url);

        HttpURLConnection connection = (HttpURLConnection) target.openConnection();
        connection.setRequestMethod(this.methodName);
        Map<String, List<String>> headers = connection.getHeaderFields();

        return headers;
    }
}
