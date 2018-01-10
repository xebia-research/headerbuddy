package com.xebia.headerbuddy.models;

import com.xebia.headerbuddy.models.analyzer.Analyzer;
import com.xebia.headerbuddy.models.analyzer.DoAnalyzer;
import com.xebia.headerbuddy.models.analyzer.RecommendationAnalyzer;
import com.xebia.headerbuddy.models.analyzer.DontAnalyzer;
import com.xebia.headerbuddy.models.entities.Evalue;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class HeaderAnalyzer {

    private Set<Evalue> foundValues;
    private Set<Evalue> databaseDoValues; //Database do headers.
    private Set<Evalue> databaseDontValues; //Database don't headers.
    private Set<Evalue> databaseRecValues; //Database recommended headers.

    private Set<Evalue> missingDoValues;
    private Set<Evalue> foundDontValues;
    private Set<Evalue> foundRecValues;

    public HeaderAnalyzer(final Set<Evalue> foundValues, final Iterable<Evalue> databaseHeaderValues) {
        databaseDoValues = new HashSet<>();
        databaseDontValues = new HashSet<>();
        databaseRecValues = new HashSet<>();

        this.foundValues = foundValues;
        organiseValuesByCategory(databaseHeaderValues);

    }

    //This method does the analyses
    public Set<Evalue> analyseHeaders() {

        Analyzer analyzer = new DoAnalyzer();
        missingDoValues = analyzer.analyze(foundValues, databaseDoValues);
        analyzer = new DontAnalyzer();
        foundDontValues = analyzer.analyze(foundValues, databaseDontValues);
        analyzer = new RecommendationAnalyzer();
        foundRecValues = analyzer.analyze(foundValues, databaseRecValues);

        // Create one set with all values needed for report
        Set<Evalue> reportValues = new LinkedHashSet<>();
        reportValues.addAll(missingDoValues);
        reportValues.addAll(foundDontValues);
        reportValues.addAll(foundRecValues);

        return reportValues;
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
