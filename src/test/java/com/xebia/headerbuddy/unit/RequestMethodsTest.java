package com.xebia.headerbuddy.unit;

import com.xebia.headerbuddy.models.RequestBehaviour;
import com.xebia.headerbuddy.models.requestmethods.*;
import org.junit.Assert;
import org.junit.Test;

public class RequestMethodsTest {

    @Test
    public void connectRequestShouldSetRightRequestMethod(){
        RequestBehaviour rb = new ConnectRequest("http://Andonoz.com");

        Assert.assertEquals("CONNECT", rb.methodName);
    }

    @Test
    public void deleteRequestShouldSetRightRequestMethod(){
        RequestBehaviour rb = new DeleteRequest("http://Andonoz.com");

        Assert.assertEquals("DELETE", rb.methodName);
    }

    @Test
    public void getRequestShouldSetRightRequestMethod(){
        RequestBehaviour rb = new GetRequest("http://Andonoz.com");

        Assert.assertEquals("GET", rb.methodName);
    }

    @Test
    public void headRequestShouldSetRightRequestMethod(){
        RequestBehaviour rb = new HeadRequest("http://Andonoz.com");

        Assert.assertEquals("HEAD", rb.methodName);
    }

    @Test
    public void optionsRequestShouldSetRightRequestMethod(){
        RequestBehaviour rb = new OptionsRequest("http://Andonoz.com");

        Assert.assertEquals("OPTIONS", rb.methodName);
    }

    @Test
    public void patchRequestShouldSetRightRequestMethod(){
        RequestBehaviour rb = new PatchRequest("http://Andonoz.com");

        Assert.assertEquals("PATCH", rb.methodName);
    }

    @Test
    public void postRequestShouldSetRightRequestMethod(){
        RequestBehaviour rb = new PostRequest("http://Andonoz.com");

        Assert.assertEquals("POST", rb.methodName);
    }

    @Test
    public void putRequestShouldSetRightRequestMethod(){
        RequestBehaviour rb = new PutRequest("http://Andonoz.com");

        Assert.assertEquals("PUT", rb.methodName);
    }

    @Test
    public void traceRequestShouldSetRightRequestMethod(){
        RequestBehaviour rb = new TraceRequest("http://Andonoz.com");

        Assert.assertEquals("TRACE", rb.methodName);
    }
}
