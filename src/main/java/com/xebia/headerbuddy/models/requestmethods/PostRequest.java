package com.xebia.headerbuddy.models.requestmethods;

import com.xebia.headerbuddy.models.RequestBehaviour;

public class PostRequest extends RequestBehaviour {

    public PostRequest(final String url) {
        super(url, "POST");
    }
}
