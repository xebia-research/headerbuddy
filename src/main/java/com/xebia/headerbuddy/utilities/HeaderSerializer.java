package com.xebia.headerbuddy.utilities;

import com.xebia.headerbuddy.models.Header;
import com.xebia.headerbuddy.models.entities.Eheader;
import com.xebia.headerbuddy.models.entities.Evalue;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class HeaderSerializer {

    public static List<Header> serialize(Map<String, List<String>> headerMap, String url) {
        List<Header> headers = new ArrayList<>();
        //make a new header for every entry in the map
        for (Map.Entry<String, List<String>> entry : headerMap.entrySet()) {
            headers.add(new Header(entry.getKey(), new HeaderSerializer().serializeValues(entry.getValue()), url));
        }
        return headers;
    }

    //The values are a (multiple)big string(s)
    //serialize the big string to different values on , and ;
    private List<String> serializeValues(List<String> headerValues) {
        //make a big string of all the entries
        String valueString = headerValues.toString().trim();

        //delete the first and last char those are useless
        valueString = valueString.substring(1, valueString.length() - 1);

        List<String> values = new ArrayList<>();
        int lastKnown = 0;

        if (valueString.contains(",") || valueString.contains(";")) {
            for (int i = 0; i < valueString.length(); i++) {
                //check for a , or ;
                if (valueString.charAt(i) == ',' || valueString.charAt(i) == ';') {
                    //get the string from the lastKnown to the iterator and trim it
                    values.add(valueString.substring(lastKnown, i).trim());
                    //update the last known and skip the , or ;
                    lastKnown = i + 1;
                }
            }
        }
        //and add the last or only value and trim it (no , or ; at the end)
        values.add(valueString.substring(lastKnown, valueString.length()).trim());

        return values;
    }

    public static Set<Eheader> convertToEHeader(List<Header> headers) {

        Set<Eheader> eHeaders = new HashSet<Eheader>();

        for (Header header : headers) {
            Set<Evalue> headerValues = new HashSet<Evalue>();
            for (String value : header.getValues()) {
                headerValues.add(new Evalue(value));
            }
            eHeaders.add(new Eheader(header.getName(), headerValues));
        }

        return eHeaders;
    }
}