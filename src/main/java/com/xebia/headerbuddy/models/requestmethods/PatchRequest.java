package com.xebia.headerbuddy.models.requestmethods;

import com.xebia.headerbuddy.models.RequestBehaviour;

import java.util.List;
import java.util.Map;

public class PatchRequest extends RequestBehaviour {

    public PatchRequest(String url) {
        super(url, "PATCH");
    }
}
