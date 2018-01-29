package com.xebia.headerbuddy.utilities;

import com.xebia.headerbuddy.models.HttpRequestMethod;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MethodHandlerTest {
    static final String oneMethod = "GET";
    static final String multipleMethods = "GET,post,TrAce";
    static final String allMethods = "all";
    static final String allMethodsWithExtra = "all,get,post";

    static final List<HttpRequestMethod> oneMethodOutput = new ArrayList<HttpRequestMethod>() {{
        add(HttpRequestMethod.GET);
    }};
    static final List<HttpRequestMethod> multipleMethodsOutput = new ArrayList<HttpRequestMethod>() {{
        add(HttpRequestMethod.GET);
        add(HttpRequestMethod.POST);
        add(HttpRequestMethod.TRACE);
    }};
    static final List<HttpRequestMethod> allMethodsOutput = new ArrayList<HttpRequestMethod>() {{
        add(HttpRequestMethod.PUT);
        add(HttpRequestMethod.HEAD);
        add(HttpRequestMethod.POST);
        add(HttpRequestMethod.PATCH);
        add(HttpRequestMethod.CONNECT);
        add(HttpRequestMethod.GET);
        add(HttpRequestMethod.TRACE);
        add(HttpRequestMethod.DELETE);
        add(HttpRequestMethod.OPTIONS);
    }};

    /*
     * Tests the static function "getAllMethodsFromMethodParam"
     * Should give the correct class names capital letters do not matter
     * Get -> GetRequest, post -> PostRequest, etc.
     */
    @Test
    public void shouldGiveCorrectClassNames() {
        List<HttpRequestMethod> oneRequest = MethodHandler.getAllMethodsFromMethodParam(oneMethod);
        List<HttpRequestMethod> multipleRequests = MethodHandler.getAllMethodsFromMethodParam(multipleMethods);
        List<HttpRequestMethod> allRequests = MethodHandler.getAllMethodsFromMethodParam(allMethods);
        List<HttpRequestMethod> allRequestsWithExtra = MethodHandler.getAllMethodsFromMethodParam(allMethodsWithExtra);

        Assert.assertEquals(oneMethodOutput, oneRequest);
        Assert.assertEquals(multipleMethodsOutput, multipleRequests);
        Assert.assertEquals(allRequests.size(), HttpRequestMethod.values().length);
        Assert.assertEquals(allRequestsWithExtra.size(), HttpRequestMethod.values().length);

        for(HttpRequestMethod method : allMethodsOutput) {
            Assert.assertTrue(allRequests.contains(method));
            Assert.assertTrue(allRequestsWithExtra.contains(method));
        }
    }

}
