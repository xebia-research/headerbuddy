package com.xebia.headerbuddy.models.requestmethods;

import com.xebia.headerbuddy.models.RequestBehaviour;

import java.util.List;
import java.util.Map;

public class HeadRequest extends RequestBehaviour {

    public HeadRequest(String url) {
        super(url, "HEAD");
    }
}
