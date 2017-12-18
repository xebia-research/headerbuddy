package com.xebia.headerbuddy.models;

import com.xebia.headerbuddy.models.entities.Ecategory;
import com.xebia.headerbuddy.models.entities.Eheader;
import com.xebia.headerbuddy.models.entities.Evalue;

import java.util.List;

public class Analyzer {

    private List<Eheader> foundHeaders; //The headers from the url.
    private List<Eheader> foundDoHeaders; //do rapport
    private List<Eheader> entityHeaders; //database defined headers.

    public Analyzer(List<Eheader> foundHeaders, List<Eheader> entityHeaders) {
        this.foundHeaders = foundHeaders;
        this.entityHeaders = entityHeaders;
    }

//    public void FindDoHeaders() {
//        for (Eheader eh : foundHeaders) {
//            for (Evalue ev : eh.getValues()) {
//                for(Ecategory ec : ev.getCategory()){
//
//                }
//
//
//            }
//        }
//    }
}