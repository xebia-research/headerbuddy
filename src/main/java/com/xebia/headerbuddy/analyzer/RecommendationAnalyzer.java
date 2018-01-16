package com.xebia.headerbuddy.analyzer;

import com.xebia.headerbuddy.models.entities.Evalue;
import java.util.HashSet;
import java.util.Set;

public class RecommendationAnalyzer implements Analyzer {

    @Override
    public Set<Evalue> analyze(Set<Evalue> toAnalyze, Set<Evalue> toCompare) {

        Set<Evalue> foundValues = new HashSet<>();

        for (Evalue analyseValue : toAnalyze) {
            for (Evalue compareValue : toCompare) {

                String analyseHeaderName = analyseValue.getHeader().getName();
                String compareHeaderName = compareValue.getHeader().getName();
                String analyseValueName = analyseValue.getValue();
                String compareValueName = compareValue.getValue();

                //Header and value needs to be the same. If the value of the database is '*' it needs to be added in any case.
                if (analyseHeaderName.equalsIgnoreCase(compareHeaderName) && (analyseValueName.equalsIgnoreCase(compareValueName) || compareValueName.equalsIgnoreCase("*"))) {
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
