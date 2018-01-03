package com.xebia.headerbuddy.models.requestmethods;

import com.xebia.headerbuddy.models.RequestBehaviour;


public class HeadRequest extends RequestBehaviour {

    public HeadRequest(final String url) {
        super(url, "HEAD");
    }
}
