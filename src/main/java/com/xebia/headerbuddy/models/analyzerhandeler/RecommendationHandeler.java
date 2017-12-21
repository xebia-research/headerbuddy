package com.xebia.headerbuddy.models.analyzerhandeler;


import com.xebia.headerbuddy.models.entities.Evalue;

import java.util.HashSet;
import java.util.Set;

public class RecommendationHandeler extends AnalyzerHandeler {

    @Override
    public Set<Evalue> detectMissingHeaders(Set<Evalue> toAnalyseValues, Set<Evalue> toCompareValues) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<Evalue> detectHeaders(Set<Evalue> toAnalyseValues, Set<Evalue> toCompareValues) {
        Set<Evalue> foundValues = new HashSet<>();

        for (Evalue analyseValue : toAnalyseValues) {
            for (Evalue compareValue : toCompareValues) {

                //Header and value needs to be the same
                if (analyseValue.getHeader().getName().equals(compareValue.getHeader().getName()) && analyseValue.getValue().equals(compareValue.getValue())) {
                    foundValues.add(compareValue);
                    break;
                }
            }
        }

        return foundValues;
    }
}
