package com.xebia.headerbuddy.models;

import com.xebia.headerbuddy.models.entities.Eheader;
import com.xebia.headerbuddy.models.entities.Evalue;
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
    private Set<Eheader> recommendedHeaders;

    public Analyzer(Set<Eheader> foundHeaders, EheaderRepository headerRepository) {
        databaseDoHeaders = new HashSet<>();
        databaseDontHeaders = new HashSet<>();
        databaseRecHeaders = new HashSet<>();
        foundDoHeaders = new HashSet<>();

        this.foundHeaders = foundHeaders;

        organiseEntityHeaders(headerRepository.findAll());
        missingDoHeaders = detectMissingDoHeaders();
        foundDontHeaders = detectDontHeaders();
        recommendedHeaders = detectRecommandations(foundDoHeaders);


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
            for (Evalue value : header.getValues()){
                System.out.println("    " + value.getValue());
            }
        }

        System.out.println();
        System.out.println("============ DONT HEADERS ==============");
        for (Eheader header : foundDontHeaders){
            System.out.println(header.getName());
        }

        System.out.println();
        System.out.println("============ REC HEADERS VALUE ==============");
        for (Eheader header : recommendedHeaders){
            for (Evalue value : header.getValues()){
                System.out.println(value.getValue());
                System.out.println(value.getDescription());
            }
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
                    foundDoHeaders.add(foundHeader); // Add to list for second analyses
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

    private Set<Eheader> detectRecommandations(Set<Eheader> headers){
        Set<Eheader> recHeaders = new HashSet<>();

        System.out.println("========== TEST ===============");
        //TEST
        for (Eheader testh : databaseRecHeaders){
            for (Evalue testv : testh.getValues()){
                System.out.println(testv.getValue() + ", header: " + testv.getHeader().getName() + ", cat: " + testv.getCategory().getName());
            }
        }
        System.out.println("========== TEST END ===============");


        for (Eheader header : headers){
            for (Eheader recHeader : databaseRecHeaders){
                if (header.getName().equals(recHeader.getName())){ //Found recommended header
                    System.out.println("We need to analyse: " + header.getName());

                    for (Evalue value : header.getValues()){
                        System.out.println("Value: " + value.getValue());

                        for (Evalue recvalue : recHeader.getValues()){
                            if (value.getValue().equals(recvalue.getValue())){
                                System.out.println("Found same value: " + recvalue.getValue());
                                recHeaders.add(recHeader);
                            }
                        }
                    }
                }
            }
        }

        return recHeaders;
    }

    private void organiseEntityHeaders(Iterable<Eheader> entityHeaders) {
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