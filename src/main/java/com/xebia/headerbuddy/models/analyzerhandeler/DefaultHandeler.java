package com.xebia.headerbuddy.models.analyzerhandeler;

import com.xebia.headerbuddy.models.entities.Evalue;

import java.util.HashSet;
import java.util.Set;

public class DefaultHandeler extends AnalyzerHandeler {

    @Override
    public Set<Evalue> detectMissingHeaders(Set<Evalue> toAnalyseValues, Set<Evalue> toCompareValues) {

        Set<Evalue> missingValues = new HashSet<>();
        for (Evalue compareValue : toCompareValues) {
            boolean found = false;

            for (Evalue analyseValue : toAnalyseValues) {

                if (analyseValue.getHeader().getName().equals(compareValue.getHeader().getName())) {
                    found = true;
                    break;
                }
            }

            if (!found) {
                missingValues.add(compareValue);
            }
        }
        return missingValues;
    }

    @Override
    public Set<Evalue> detectHeaders(Set<Evalue> toAnalyseValues, Set<Evalue> toCompareValues) {
        Set<Evalue> foundValues = new HashSet<>();

        for (Evalue analyseValue : toAnalyseValues) {
            for (Evalue compareValue : toCompareValues) {


                if (analyseValue.getHeader().getName().equals(compareValue.getHeader().getName())) {
                    foundValues.add(analyseValue);
                    break;
                }
            }
        }

        return foundValues;
    }
}
