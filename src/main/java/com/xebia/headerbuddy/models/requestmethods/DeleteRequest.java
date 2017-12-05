package com.xebia.headerbuddy.models.requestmethods;
import com.xebia.headerbuddy.models.RequestBehaviour;


public class DeleteRequest extends RequestBehaviour {

    public DeleteRequest(String url) {
        super(url, "DELETE");
    }
}
