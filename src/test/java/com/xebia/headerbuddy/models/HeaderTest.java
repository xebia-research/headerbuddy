package com.xebia.headerbuddy.models;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class HeaderTest {
    static final String HEADERNAME1 = "Server";
    static final String HEADERNAME2 = "X-Frame-Options";
    static final String HEADERVALUE1 = "On";
    static final String HEADERVALUE2 = "Off";
    static final String SITE1 = "http://website1.com";
    static final String SITE2 = "http://website2.com";
    List<String> HEADERVALUELIST = new ArrayList<>();

    @Test
    public void shouldSetRightAttributes() {
        List<String> headervalues = new ArrayList<>();
        headervalues.add(HEADERVALUE1);
        headervalues.add((HEADERVALUE2));
        Header header = new Header(HEADERNAME1, headervalues, SITE1);

        Assert.assertEquals(HEADERNAME1, header.getName());
        Assert.assertEquals(HEADERVALUE1, header.getValues().get(0));
        Assert.assertEquals(HEADERVALUE2, header.getValues().get(1));
        Assert.assertEquals(SITE1, header.getUrls().get(0));
    }

    @Test
    public void shouldSetName() {
        Header header = new Header(HEADERNAME1, HEADERVALUELIST, SITE1);

        header.setName(HEADERNAME2);

        Assert.assertEquals(HEADERNAME2, header.getName());
    }

    @Test
    public void shouldAddWebsite() {
        Header header = new Header(HEADERNAME1, HEADERVALUELIST, SITE1);

        header.addUrl(SITE2);

        Assert.assertEquals(SITE1, header.getUrls().get(0));
        Assert.assertEquals(SITE2, header.getUrls().get(1));
    }

    @Test
    public void shouldAddValue() {
        Header header = new Header(HEADERNAME1, HEADERVALUELIST, SITE1);

        header.addValue(HEADERVALUE1);
        header.addValue(HEADERVALUE2);

        Assert.assertEquals(2, header.getValues().size());
        Assert.assertEquals(HEADERVALUE1, header.getValues().get(0));
        Assert.assertEquals(HEADERVALUE2, header.getValues().get(1));
    }
}
