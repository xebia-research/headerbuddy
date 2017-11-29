package com.xebia.headerbuddy.models.requestmethods;

import com.xebia.headerbuddy.models.RequestBehaviour;

import java.util.List;
import java.util.Map;

public class ConnectRequest extends RequestBehaviour {

    public ConnectRequest(String url) {
        super(url, "CONNECT");
    }
}
