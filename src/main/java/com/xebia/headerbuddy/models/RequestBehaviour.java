package com.xebia.headerbuddy.models;

import com.sun.org.apache.xpath.internal.SourceTree;
import com.xebia.headerbuddy.utilities.HeaderSerializer;

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

    public List<Header> doRequest() throws Exception {
        URL target = new URL(this.url);

        HttpURLConnection connection = (HttpURLConnection) target.openConnection();
        switch (this.methodName) {
            case "PATCH":   connection.setRequestProperty("X-HTTP-Method-Override", "PATCH");
                            connection.setRequestMethod("POST");
                            break;
            case "CONNECT": connection.setRequestProperty("X-HTTP-Method-Override", "CONNECT");
                            connection.setRequestMethod("POST");
                            break;
            default:        connection.setRequestMethod(this.methodName);
                            break;
        }
        Map<String, List<String>> headerFields = connection.getHeaderFields();

        List<Header> headers = HeaderSerializer.serialize(headerFields, this.url);

        return headers;
    }
}
