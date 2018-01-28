package com.xebia.headerbuddy.utilities;

import com.xebia.headerbuddy.models.Header;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HeaderSerializerTest {
    static final String WEBSITE = "http://website.com";
    static final String HEADER1 = "X-Frame-Options";
    static final String HEADER2 = "Server";
    static final String VALUE1 = "Value1=1";
    static final String VALUE2 = "value2";
    static final String VALUE3 = "value3=httponly";

    @Test
    public void serializeShouldConvertMapInHeaderList() {

        Map<String, List<String>> headerMap = new HashMap<>();
        List<String> stringList = new ArrayList<>();

        stringList.add(VALUE1);
        stringList.add(VALUE2);
        stringList.add(VALUE3);

        headerMap.put(HEADER1, stringList);
        headerMap.put(HEADER2, stringList);

        List<Header> headers = HeaderSerializer.serialize(headerMap, WEBSITE);

//      Test if the right headers were created
        Header h1 = headers.get(0);
        String v1_1 = h1.getValues().get(0);
        String v1_2 = h1.getValues().get(1);
        String v1_3 = h1.getValues().get(2);
        String u1_1 = h1.getUrls().get(0);

        Header h2 = headers.get(1);
        String v2_1 = h2.getValues().get(0);
        String v2_2 = h2.getValues().get(1);
        String v2_3 = h2.getValues().get(2);
        String u2_1 = h2.getUrls().get(0);

        Assert.assertEquals(HEADER1, h1.getName());
        Assert.assertEquals(VALUE1, v1_1);
        Assert.assertEquals(VALUE2, v1_2);
        Assert.assertEquals(VALUE3, v1_3);
        Assert.assertEquals(WEBSITE, u1_1);
        Assert.assertEquals(HEADER2, h2.getName());
        Assert.assertEquals(VALUE1, v2_1);
        Assert.assertEquals(VALUE2, v2_2);
        Assert.assertEquals(VALUE3, v2_3);
        Assert.assertEquals(WEBSITE, u2_1);
    }

    @Test
    public void serializeValuesShouldSplitValuesByComma() {
        Map<String, List<String>> headerMap = new HashMap<>();
        List<String> stringList = new ArrayList<>();

        stringList.add(VALUE1 + "," + VALUE2 + "," + VALUE3);
        headerMap.put(HEADER1, stringList);

        List<Header> headers = HeaderSerializer.serialize(headerMap, WEBSITE);
        Header h1 = headers.get(0);

        Assert.assertEquals(VALUE1, h1.getValues().get(0));
        Assert.assertEquals(VALUE2, h1.getValues().get(1));
        Assert.assertEquals(VALUE3, h1.getValues().get(2));
    }

    @Test
    public void serializeValuesShouldSplitValuesBySemicolon() {
        Map<String, List<String>> headerMap = new HashMap<>();
        List<String> stringList = new ArrayList<>();

        stringList.add(VALUE1 + ";" + VALUE2 + ";" + VALUE3);
        headerMap.put(HEADER1, stringList);

        List<Header> headers = HeaderSerializer.serialize(headerMap, WEBSITE);
        Header h1 = headers.get(0);

        Assert.assertEquals(VALUE1, h1.getValues().get(0));
        Assert.assertEquals(VALUE2, h1.getValues().get(1));
        Assert.assertEquals(VALUE3, h1.getValues().get(2));
    }

}
