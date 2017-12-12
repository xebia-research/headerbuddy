package com.xebia.headerbuddy.models.requestmethods;

import com.xebia.headerbuddy.models.RequestBehaviour;


public class ConnectRequest extends RequestBehaviour {

    public ConnectRequest(String url) {
        super(url, "CONNECT");
    }
}

