package com.xebia.headerbuddy.models.analyzerhandeler;

import com.xebia.headerbuddy.models.entities.Evalue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DoHandeler extends AnalyzerHandeler {
    @Override
    public Set<Evalue> detectMissingHeaders(Set<Evalue> toAnalyseValues, Set<Evalue> toCompareValues) {
        Set<Evalue> missingDoValues = new HashSet<>();
        //List to check if a header has already been marked missing
        List<String> missingHeaderNames = new ArrayList<>();

        for (Evalue doValue : toCompareValues) {
            boolean found = false;

            //Making sure that already detected missing headers are not checked again
            if (!missingHeaderNames.contains(doValue.getHeader().getName())) {

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

    @Override
    public Set<Evalue> detectHeaders(Set<Evalue> toAnalyseValues, Set<Evalue> toCompareValues) {
        throw new UnsupportedOperationException();
    }
}

