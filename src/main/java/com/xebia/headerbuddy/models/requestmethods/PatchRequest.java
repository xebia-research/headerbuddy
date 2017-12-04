package com.xebia.headerbuddy.models.requestmethods;
import com.xebia.headerbuddy.models.RequestBehaviour;

public class PatchRequest extends RequestBehaviour {

    public PatchRequest(String url) {
        super(url, "PATCH");
    }
}
