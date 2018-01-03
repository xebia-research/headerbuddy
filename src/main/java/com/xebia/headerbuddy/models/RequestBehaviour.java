package com.xebia.headerbuddy.models;

import com.xebia.headerbuddy.utilities.HeaderSerializer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

public abstract class RequestBehaviour {
    private String url;
    private String methodName;

    public RequestBehaviour(final String url, final String methodName) {
        this.url = url;
        this.methodName = methodName;
    }

    public List<Header> doRequest() throws Exception {
        URL target = new URL(this.url);

        HttpURLConnection connection = (HttpURLConnection) target.openConnection();
        switch (this.methodName) {
            case "PATCH":
                connection.setRequestProperty("X-HTTP-Method-Override", "PATCH");
                connection.setRequestMethod("POST");
                break;
            case "CONNECT":
                connection.setRequestProperty("X-HTTP-Method-Override", "CONNECT");
                connection.setRequestMethod("POST");
                break;
            default:
                connection.setRequestMethod(this.methodName);
                break;
        }

        Map<String, List<String>> headerFields = connection.getHeaderFields();

        List<Header> headers = HeaderSerializer.serialize(headerFields, this.url);

        return headers;
    }

    //Getters and setters

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}
