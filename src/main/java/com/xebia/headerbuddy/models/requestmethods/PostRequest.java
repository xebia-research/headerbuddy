package com.xebia.headerbuddy.models.requestmethods;

import com.xebia.headerbuddy.models.RequestBehaviour;

import java.util.List;
import java.util.Map;

public class PostRequest extends RequestBehaviour{

    public PostRequest(String url) {
        super(url, "POST");
    }
}
