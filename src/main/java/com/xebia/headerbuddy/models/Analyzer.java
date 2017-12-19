package com.xebia.headerbuddy.models;

import com.xebia.headerbuddy.models.entities.Ecategory;
import com.xebia.headerbuddy.models.entities.Eheader;
import com.xebia.headerbuddy.models.entities.Evalue;
import com.xebia.headerbuddy.models.entities.repositories.EheaderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Analyzer {

    private EheaderRepository headerRepository; //The header database.
    private Set<Eheader> foundHeaders; //The headers from the url.
    private Set<Eheader> foundDoHeaders; //Do rapport.
    private Set<Eheader> foundDontHeaders; //Don't rapport.
    private Set<Eheader> foundRecHeaders; //Recommendations rapport.
    private Set<Eheader> entityDoHeaders; //Database do headers.
    private Set<Eheader> entityDontHeaders; //Database don't headers.
    private Set<Eheader> entityRecHeaders; //Database recommended headers.
    private Iterable<Eheader> entityHeaders; //Database defined headers.

    public Analyzer(Set<Eheader> foundHeaders, EheaderRepository headerRepository) {
        this.headerRepository = headerRepository;
        entityHeaders = headerRepository.findAll();
        this.foundHeaders = foundHeaders;

        organiseEntityHeaders();
        organiseFoundHeaders();
    }

    public void organiseEntityHeaders() {
        entityHeaders.forEach(eheader -> {
            eheader.getValues().forEach(evalue -> {
                if(evalue.getCategory().getName()== "do") {
                    entityDoHeaders.add(eheader);
                }
                else if (evalue.getCategory().getName() == "dont") {
                    entityDontHeaders.add(eheader);
                }
                else if(evalue.getCategory().getName() == "recommendation"){
                    entityRecHeaders.add(eheader);
                }
            });
        });
    }

    public void organiseFoundHeaders() {
        foundHeaders.forEach(eheader -> {
            if (entityDoHeaders.contains(eheader)) {
                foundDoHeaders.add(eheader);
            } else if (entityDontHeaders.contains(eheader)) {
                foundDontHeaders.add(eheader);
            } else if (entityRecHeaders.contains(eheader)) {
                foundRecHeaders.add(eheader);
            }
        });
    }

    public Set<Eheader> getFoundDoHeaders() {
        return foundDoHeaders;
    }

    public Set<Eheader> getFoundDontHeaders() {
        return foundDontHeaders;
    }

    public Set<Eheader> getFoundRecHeaders() {
        return foundRecHeaders;
    }
}