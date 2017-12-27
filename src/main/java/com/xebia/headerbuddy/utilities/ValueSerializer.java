package com.xebia.headerbuddy.utilities;

import com.xebia.headerbuddy.models.Header;
import com.xebia.headerbuddy.models.entities.Eheader;
import com.xebia.headerbuddy.models.entities.Evalue;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ValueSerializer {

    public static Set<Evalue> convertToEvalue(List<Header> headers) {
        Set<Evalue> valuesSet = new HashSet<>();

        for (Header header : headers) {
            Eheader hed = new Eheader(header.getName());

            for (String value : header.getValues()) {
                Evalue val = new Evalue(value);
                val.setHeader(hed);
                valuesSet.add(val);
            }
        }

        return valuesSet;
    }
}
