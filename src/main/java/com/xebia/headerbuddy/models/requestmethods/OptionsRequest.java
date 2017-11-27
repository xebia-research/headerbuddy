package com.xebia.headerbuddy.models.requestmethods;

import com.xebia.headerbuddy.models.RequestBehaviour;

import java.util.List;
import java.util.Map;

public class OptionsRequest extends RequestBehaviour {

    public OptionsRequest(String url, String methodName) {
        super(url, methodName);
    }

    @Override
    public Map<String, List<String>> doRequest() throws Exception {
        return super.doRequest();
    }
}
