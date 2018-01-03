package com.xebia.headerbuddy.utilities;

import com.xebia.headerbuddy.models.entities.Eurl;
import java.util.HashSet;
import java.util.Set;

public class UrlSerializer {

    public static Set<Eurl> convertToEurl(Set<String> urls) {
        Set<Eurl> urlSet = new HashSet<>();

        for (String url : urls){
            urlSet.add(new Eurl(url));
        }

        return urlSet;
    }
}
