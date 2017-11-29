package com.xebia.headerbuddy.models.requestmethods;

import com.xebia.headerbuddy.models.RequestBehaviour;

import java.util.List;
import java.util.Map;

public class TraceRequest extends RequestBehaviour {

    public TraceRequest(String url) {
        super(url, "TRACE");
    }
}
