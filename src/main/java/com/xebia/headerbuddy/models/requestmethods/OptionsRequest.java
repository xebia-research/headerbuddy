package com.xebia.headerbuddy.models.requestmethods;

import com.xebia.headerbuddy.models.RequestBehaviour;

public class OptionsRequest extends RequestBehaviour {

    public OptionsRequest(final String url) {
        super(url, "OPTIONS");
    }
}
