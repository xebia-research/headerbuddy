package com.xebia.headerbuddy.unit;

import com.xebia.headerbuddy.utilities.MethodHandler;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MethodHandlerTest {
    static final String oneMethod = "GET";
    static final String multipleMethods = "GET,post,TrAce";
    static final String allMethods = "all";
    static final String allMethodsWithExtra = "all,get,post";

    static final List<String> oneMethodOutput = new ArrayList<String>(){{
        add("GetRequest");
    }};
    static final List<String> multipleMethodsOutput = new ArrayList<String>(){{
        add("GetRequest");
        add("PostRequest");
        add("TraceRequest");
    }};
    static final List<String> allMethodsOutput = new ArrayList<String>(){{
        add("PutRequest");
        add("HeadRequest");
        add("PostRequest");
        add("PatchRequest");
        add("ConnectRequest");
        add("GetRequest");
        add("TraceRequest");
        add("DeleteRequest");
        add("OptionsRequest");
    }};

    /*
    * Tests the static function "getAllMethodsFromMethodParam"
    * Should give the correct class names capital letters do not matter
    * Get -> GetRequest, post -> PostRequest, etc.
     */
    @Test
    public void shouldGiveCorrectClassNames() {
        List<String> oneRequest = MethodHandler.getAllMethodsFromMethodParam(oneMethod);
        List<String> multipleRequests = MethodHandler.getAllMethodsFromMethodParam(multipleMethods);
        List<String> allRequests = MethodHandler.getAllMethodsFromMethodParam(allMethods);
        List<String> allRequestsWithExtra = MethodHandler.getAllMethodsFromMethodParam(allMethodsWithExtra);

        Assert.assertEquals(oneMethodOutput, oneRequest);
        Assert.assertEquals(multipleMethodsOutput, multipleRequests);
        Assert.assertEquals(allMethodsOutput, allRequests);
        Assert.assertEquals(allMethodsOutput, allRequestsWithExtra);
    }

}
