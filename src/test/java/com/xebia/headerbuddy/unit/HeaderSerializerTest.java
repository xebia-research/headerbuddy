package com.xebia.headerbuddy.unit;

import java.util.ArrayList;
import com.xebia.headerbuddy.models.Header;
import com.xebia.headerbuddy.utilities.HeaderSerializer;
import org.junit.Assert;
import org.junit.Test;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HeaderSerializerTest {

    @Test
    public void serializeShouldConvertMapInHeaderList(){
        final String WEBSITE = "http://website.com";
        final String HEADER1 = "X-Frame-Options";
        final String HEADER2 = "Server";
        final String VALUE1 = "on";
        final String VALUE2 = "off";

        Map<String, List<String>> headerMap = new HashMap<>();
        List<String> stringList = new ArrayList<>();

        stringList.add(VALUE1);
        stringList.add(VALUE2);

        headerMap.put(HEADER1, stringList);
        headerMap.put(HEADER2, stringList);

        List<Header> headers = HeaderSerializer.serialize(headerMap, WEBSITE);

//      Test if the right headers were created
        Header h1 = headers.get(0);
        String v1_1 = h1.getValues().get(0);
        String v1_2 = h1.getValues().get(1);
        String u1_1 = h1.getUrls().get(0);

        Header h2 = headers.get(1);
        String v2_1 = h2.getValues().get(0);
        String v2_2 = h2.getValues().get(1);
        String u2_1 = h2.getUrls().get(0);

        Assert.assertEquals(HEADER1, h1.getName());
        Assert.assertEquals(VALUE1, v1_1);
        Assert.assertEquals(VALUE2, v1_2);
        Assert.assertEquals(WEBSITE, u1_1);
        Assert.assertEquals(HEADER2, h2.getName());
        Assert.assertEquals(VALUE1, v2_1);
        Assert.assertEquals(VALUE2, v2_2);
        Assert.assertEquals(WEBSITE, u2_1);
    }
}
