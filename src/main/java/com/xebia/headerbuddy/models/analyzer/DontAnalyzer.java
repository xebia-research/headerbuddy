package com.xebia.headerbuddy.models.analyzer;

import com.xebia.headerbuddy.models.entities.Evalue;
import java.util.HashSet;
import java.util.Set;

public class DontAnalyzer implements Analyzer {

    @Override
    public Set<Evalue> analyze(Set<Evalue> toAnalyze, Set<Evalue> toCompare) {

        Set<Evalue> foundDontValues = new HashSet<>();

        for (Evalue analyseValue : toAnalyze) {
            for (Evalue dontValue : toCompare) {

                if (analyseValue.getHeader().getName().equalsIgnoreCase(dontValue.getHeader().getName())) {
                    //Found a don't header
                    foundDontValues.add(dontValue);
                    break;
                }
            }
        }

        //Return found values with don't headers
        return foundDontValues;
    }
}
