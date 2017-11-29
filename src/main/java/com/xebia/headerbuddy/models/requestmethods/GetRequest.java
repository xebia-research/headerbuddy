package com.xebia.headerbuddy.models.requestmethods;

import com.xebia.headerbuddy.models.RequestBehaviour;

import java.util.List;
import java.util.Map;

public class GetRequest extends RequestBehaviour {

    public GetRequest(String url) {
        super(url, "GET");
    }
}
