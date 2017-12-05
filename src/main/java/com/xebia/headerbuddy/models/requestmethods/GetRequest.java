package com.xebia.headerbuddy.models.requestmethods;
import com.xebia.headerbuddy.models.RequestBehaviour;

public class GetRequest extends RequestBehaviour {

    public GetRequest(String url) {
        super(url, "GET");
    }
}
