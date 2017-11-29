package com.xebia.headerbuddy.models.requestmethods;

import com.xebia.headerbuddy.models.RequestBehaviour;

import java.util.List;
import java.util.Map;

public class DeleteRequest extends RequestBehaviour {

    public DeleteRequest(String url) {
        super(url, "DELETE");
    }
}
