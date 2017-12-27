package com.xebia.headerbuddy.models.analyzer;

import com.xebia.headerbuddy.models.entities.Evalue;
import java.util.HashSet;
import java.util.Set;

public class RecommendationAnalyzer implements Analyzer {

    @Override
    public Set<Evalue> analyze(Set<Evalue> toAnalyze, Set<Evalue> toCompare) {

        Set<Evalue> foundValues = new HashSet<>();

        for (Evalue analyseValue : toAnalyze) {
            for (Evalue compareValue : toCompare) {

                //Header and value needs to be the same
                if (analyseValue.getHeader().getName().equals(compareValue.getHeader().getName()) && analyseValue.getValue().equals(compareValue.getValue())) {
                    //Found not recommended value
                    foundValues.add(compareValue);
                    break;
                }
            }
        }

        //Return values with wrong settings
        return foundValues;
    }
}
