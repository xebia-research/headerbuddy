package com.xebia.headerbuddy.models;

import com.xebia.headerbuddy.models.entities.Ereport;
import com.xebia.headerbuddy.models.entities.Euser;
import com.xebia.headerbuddy.models.entities.Evalue;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Analyzer {

    private Set<Evalue> foundValues;
    private Set<Evalue> databaseDoValues; //Database do headers.
    private Set<Evalue> databaseDontValues; //Database don't headers.
    private Set<Evalue> databaseRecValues; //Database recommended headers.

    private Set<Evalue> missingDoValues;
    private Set<Evalue> foundDoValues;
    private Set<Evalue> foundDontValues;
    private Set<Evalue> foundRecValues;

    public Analyzer(Set<Evalue> foundValues, Iterable<Evalue> databaseHeaderValues) {
        databaseDoValues = new HashSet<>();
        databaseDontValues = new HashSet<>();
        databaseRecValues = new HashSet<>();

        this.foundValues = foundValues;
        organiseValuesByCategory(databaseHeaderValues);
    }

    //This method does the analyses
    public Ereport analyseHeaders(Euser user) {
        missingDoValues = detectMissingDoHeaders(foundValues, databaseDoValues);
        foundDoValues = detectDoHeaders(foundValues, databaseDoValues);
        foundDontValues = detectDontHeaders(foundValues, databaseDontValues);
        foundRecValues = detectRecHeaders(foundDoValues, databaseRecValues);

        //Create one set with all values needed for report
        Set<Evalue> reportValues = new HashSet<>();
        reportValues.addAll(missingDoValues);
        reportValues.addAll(foundDontValues);
        reportValues.addAll(foundRecValues);

        Ereport report = new Ereport(user, reportValues);

        return report;
    }

    private Set<Evalue> detectMissingDoHeaders(Set<Evalue> toAnalyseValues, Set<Evalue> doValues) {
        Set<Evalue> missingDoValues = new HashSet<>();
        List<String> missingHeaderNames = new ArrayList<>();


        for (Evalue doValue : doValues) {
            boolean found = false;

            //Making sure that already detected missing headers are not checked again
            if(!missingHeaderNames.contains(doValue.getHeader().getName())) {

                for (Evalue analyseValue : toAnalyseValues) {

                    if (analyseValue.getHeader().getName().equals(doValue.getHeader().getName())) {
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    missingDoValues.add(doValue);
                    missingHeaderNames.add(doValue.getHeader().getName());
                }
            }
        }

        return missingDoValues;
    }

    private Set<Evalue> detectDoHeaders(Set<Evalue> toAnalyseValues, Set<Evalue> doValues) {
        Set<Evalue> foundDoValues = new HashSet<>();

        for (Evalue analyseValue : toAnalyseValues) {
            for (Evalue doValue : doValues) {

                if (analyseValue.getHeader().getName().equals(doValue.getHeader().getName())) {
                    foundDoValues.add(analyseValue);
                    break;
                }
            }
        }

        return foundDoValues;
    }

    private Set<Evalue> detectDontHeaders(Set<Evalue> toAnalyseValues, Set<Evalue> dontValues) {
        Set<Evalue> foundDontValues = new HashSet<>();

        for (Evalue analyseValue : toAnalyseValues) {
            for (Evalue dontValue : dontValues) {

                if (analyseValue.getHeader().getName().equals(dontValue.getHeader().getName())) {
                    foundDontValues.add(dontValue);
                    break;
                }
            }
        }

        return foundDontValues;
    }

    private Set<Evalue> detectRecHeaders(Set<Evalue> toAnalyseValues, Set<Evalue> recValues) {
        Set<Evalue> foundRecValues = new HashSet<>();

        for (Evalue analyseValue : toAnalyseValues) {
            for (Evalue recValue : recValues) {

                //Header and value needs to be the same
                if (analyseValue.getHeader().getName().equals(recValue.getHeader().getName()) && analyseValue.getValue().equals(recValue.getValue())) {
                    foundRecValues.add(recValue);
                    break;
                }
            }
        }

        return foundRecValues;
    }

    private void organiseValuesByCategory(Iterable<Evalue> values) {
        for (Evalue value : values) {
            if (value.getCategory().getName().equals("do")) {
                databaseDoValues.add(value);
            } else if (value.getCategory().getName().equals("dont")) {
                databaseDontValues.add(value);
            } else if (value.getCategory().getName().equals("recommendation")) {
                databaseRecValues.add(value);
            }
        }
    }

}