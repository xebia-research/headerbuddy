package com.xebia.headerbuddy.models.requestmethods;
import com.xebia.headerbuddy.models.RequestBehaviour;

public class TraceRequest extends RequestBehaviour {

    public TraceRequest(String url) {
        super(url, "TRACE");
    }
}
