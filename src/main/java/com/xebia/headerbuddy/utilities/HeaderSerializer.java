package com.xebia.headerbuddy.utilities;

import com.xebia.headerbuddy.models.Header;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HeaderSerializer {

    public static List<Header> serialize(Map<String, List<String>> headerMap, String url) {
        List<Header> headers = new ArrayList<Header>();

        for (Map.Entry<String, List<String>> entry : headerMap.entrySet()) {
            headers.add(new Header(entry.getKey(), entry.getValue(), url));
        }

        return headers;
    }
}
