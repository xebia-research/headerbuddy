package com.xebia.headerbuddy.models.requestmethods;

import com.xebia.headerbuddy.models.RequestBehaviour;


public class ConnectRequest extends RequestBehaviour {

    public ConnectRequest(final String url) {
        super(url, "CONNECT");
    }
}

