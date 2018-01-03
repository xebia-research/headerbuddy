package com.xebia.headerbuddy.models.analyzer;

import com.xebia.headerbuddy.models.entities.Evalue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DoAnalyzer implements Analyzer {

    @Override
    public Set<Evalue> analyze(Set<Evalue> toAnalyze, Set<Evalue> toCompare) {

        Set<Evalue> missingDoValues = new HashSet<>();
        //List to check if a header has already been marked missing
        List<String> missingHeaderNames = new ArrayList<>();

        for (Evalue doValue : toCompare) {
            boolean found = false;

            //Making sure that already detected missing headers are not checked again
            if (!missingHeaderNames.contains(doValue.getHeader().getName())) {

                for (Evalue analyseValue : toAnalyze) {

                    if (analyseValue.getHeader().getName().equals(doValue.getHeader().getName())) {
                        //Found an do header
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    //Do header was not found
                    missingDoValues.add(doValue);
                    missingHeaderNames.add(doValue.getHeader().getName());
                }
            }
        }

        //Return values with missing do headers
        return missingDoValues;
    }
}
