package com.xebia.headerbuddy.models;

import org.junit.Assert;
import org.junit.Test;

public class RequestMethodsTest {

    @Test
    public void connectRequestShouldSetRightRequestMethod() {
        RequestBehaviour rb = new RequestBehaviour("http://Andonoz.com", HttpRequestMethod.CONNECT);

        Assert.assertEquals("POST", rb.getMethod());
    }

    @Test
    public void deleteRequestShouldSetRightRequestMethod() {
        RequestBehaviour rb = new RequestBehaviour("http://Andonoz.com", HttpRequestMethod.DELETE);

        Assert.assertEquals("DELETE", rb.getMethod());
    }

    @Test
    public void getRequestShouldSetRightRequestMethod() {
        RequestBehaviour rb = new RequestBehaviour("http://Andonoz.com", HttpRequestMethod.GET);

        Assert.assertEquals("GET", rb.getMethod());
    }

    @Test
    public void headRequestShouldSetRightRequestMethod() {
        RequestBehaviour rb = new RequestBehaviour("http://Andonoz.com", HttpRequestMethod.HEAD);

        Assert.assertEquals("HEAD", rb.getMethod());
    }

    @Test
    public void optionsRequestShouldSetRightRequestMethod() {
        RequestBehaviour rb = new RequestBehaviour("http://Andonoz.com", HttpRequestMethod.OPTIONS);

        Assert.assertEquals("OPTIONS", rb.getMethod());
    }

    @Test
    public void patchRequestShouldSetRightRequestMethod() {
        RequestBehaviour rb = new RequestBehaviour("http://Andonoz.com", HttpRequestMethod.PATCH);

        Assert.assertEquals("POST", rb.getMethod());
    }

    @Test
    public void postRequestShouldSetRightRequestMethod() {
        RequestBehaviour rb = new RequestBehaviour("http://Andonoz.com", HttpRequestMethod.POST);

        Assert.assertEquals("POST", rb.getMethod());
    }

    @Test
    public void putRequestShouldSetRightRequestMethod() {
        RequestBehaviour rb = new RequestBehaviour("http://Andonoz.com", HttpRequestMethod.PUT);

        Assert.assertEquals("PUT", rb.getMethod());
    }

    @Test
    public void traceRequestShouldSetRightRequestMethod() {
        RequestBehaviour rb = new RequestBehaviour("http://Andonoz.com", HttpRequestMethod.TRACE);

        Assert.assertEquals("TRACE", rb.getMethod());
    }
}
