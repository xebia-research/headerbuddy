package com.xebia.headerbuddy.models.analysehandlers;

import com.xebia.headerbuddy.models.entities.Evalue;
import java.util.HashSet;
import java.util.Set;

public class DontHandeler extends AnalyzerHandeler {
    @Override
    public Set<Evalue> detectMissingHeaders(Set<Evalue> toAnalyseValues, Set<Evalue> toCompareValues) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<Evalue> detectHeaders(Set<Evalue> toAnalyseValues, Set<Evalue> toCompareValues) {
        Set<Evalue> foundDontValues = new HashSet<>();

        for (Evalue analyseValue : toAnalyseValues) {
            for (Evalue dontValue : toCompareValues) {

                if (analyseValue.getHeader().getName().equals(dontValue.getHeader().getName())) {
                    foundDontValues.add(dontValue);
                    break;
                }
            }
        }

        return foundDontValues;
    }
}