package com.xebia.headerbuddy.models.requestmethods;
import com.xebia.headerbuddy.models.RequestBehaviour;

public class PutRequest extends RequestBehaviour {

    public PutRequest(String url) {
        super(url, "PUT");
    }
}
