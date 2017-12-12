package com.xebia.headerbuddy.models.requestmethods;
import com.xebia.headerbuddy.models.RequestBehaviour;


public class HeadRequest extends RequestBehaviour {

    public HeadRequest(String url) {
        super(url, "HEAD");
    }
}
