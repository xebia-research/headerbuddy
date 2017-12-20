package com.xebia.headerbuddy.models;

import com.xebia.headerbuddy.models.entities.Eheader;
import com.xebia.headerbuddy.models.entities.repositories.EheaderRepository;
import java.util.HashSet;
import java.util.Set;

public class Analyzer {

    private Set<Eheader> foundHeaders; //The headers from the url.
    private Set<Eheader> databaseDoHeaders; //Database do headers.
    private Set<Eheader> databaseDontHeaders; //Database don't headers.
    private Set<Eheader> databaseRecHeaders; //Database recommended headers.

    private Set<Eheader> missingDoHeaders;
    private Set<Eheader> foundDoHeaders; //Needed for second analyse
    private Set<Eheader> foundDontHeaders;

    public Analyzer(Set<Eheader> foundHeaders, EheaderRepository headerRepository) {
        databaseDoHeaders = new HashSet<>();
        databaseDontHeaders = new HashSet<>();
        databaseRecHeaders = new HashSet<>();

        this.foundHeaders = foundHeaders;

        organiseEntityHeaders(headerRepository.findAll());
        missingDoHeaders = detectMissingDoHeaders();
        foundDontHeaders = detectDontHeaders();

        //Separate DoHeaders from missingDoHeaders -> Needed for second analyse
        foundDoHeaders = databaseDoHeaders;
        foundDoHeaders.removeAll(missingDoHeaders);

        System.out.println("========= Found headers ============");
        for (Eheader header: foundHeaders){
            System.out.println(header.getName());
        }

        System.out.println();
        System.out.println("============ MISSING DO HEADERS ==============");
        for (Eheader header : missingDoHeaders){
            System.out.println(header.getName());
        }

        System.out.println();
        System.out.println("============ FOUND DO HEADERS ==============");
        for (Eheader header : foundDoHeaders){
            System.out.println(header.getName());
        }

        System.out.println();
        System.out.println("============ DONT HEADERS ==============");
        for (Eheader header : foundDontHeaders){
            System.out.println(header.getName());
        }

        System.out.println();
    }

    private Set<Eheader> detectMissingDoHeaders(){
        Set<Eheader> missingDoHeaders = new HashSet<>();
        boolean found;

        for (Eheader doHeader: databaseDoHeaders){
            found = false;

            for (Eheader foundHeader: foundHeaders){
                if (doHeader.getName().equals(foundHeader.getName())){
                    found = true;
                }
            }

            if (!found){
                missingDoHeaders.add(doHeader);
            }
        }

        return missingDoHeaders;
    }

    private Set<Eheader> detectDontHeaders(){
        Set<Eheader> foundDontHeaders = new HashSet<>();

        for (Eheader dontHeader: databaseDontHeaders){

            for (Eheader foundHeader: foundHeaders){
                if (dontHeader.getName().equals(foundHeader.getName())){
                    foundDontHeaders.add(dontHeader);
                    break;
                }
            }
        }

        return foundDontHeaders;
    }

    public void organiseEntityHeaders(Iterable<Eheader> entityHeaders) {
        entityHeaders.forEach(eheader -> {
            eheader.getValues().forEach(evalue -> {
                if(evalue.getCategory().getName().equals("do")) {
                    databaseDoHeaders.add(eheader);
                }
                else if (evalue.getCategory().getName().equals("dont")) {
                    databaseDontHeaders.add(eheader);
                }
                else if(evalue.getCategory().getName().equals("recommendation")){
                    databaseRecHeaders.add(eheader);
                }
            });
        });


    }
}