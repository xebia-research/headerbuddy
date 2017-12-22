package com.xebia.headerbuddy.models;

import com.xebia.headerbuddy.models.analyzerhandeler.AnalyzerHandeler;
import com.xebia.headerbuddy.models.analyzerhandeler.DefaultHandeler;
import com.xebia.headerbuddy.models.analyzerhandeler.DoHandeler;
import com.xebia.headerbuddy.models.analyzerhandeler.DontHandeler;
import com.xebia.headerbuddy.models.analyzerhandeler.RecommendationHandeler;
import com.xebia.headerbuddy.models.entities.Ereport;
import com.xebia.headerbuddy.models.entities.Euser;
import com.xebia.headerbuddy.models.entities.Evalue;
import java.util.HashSet;
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

    private AnalyzerHandeler analyzerHandeler;

    public Analyzer(final Set<Evalue> foundValues, final Iterable<Evalue> databaseHeaderValues) {
        databaseDoValues = new HashSet<>();
        databaseDontValues = new HashSet<>();
        databaseRecValues = new HashSet<>();

        this.foundValues = foundValues;
        organiseValuesByCategory(databaseHeaderValues);

    }

    //This method does the analyses
    public Ereport analyseHeaders(Euser user) {

        //Filter all headers with the handelers.
        analyzerHandeler = new DefaultHandeler();
        foundDoValues = analyzerHandeler.detectHeaders(foundValues, databaseDoValues);
        analyzerHandeler = new DoHandeler();
        missingDoValues = analyzerHandeler.detectMissingHeaders(foundValues, databaseDoValues);
        analyzerHandeler = new DontHandeler();
        foundDontValues = analyzerHandeler.detectHeaders(foundValues, databaseDontValues);
        analyzerHandeler = new RecommendationHandeler();
        foundRecValues = analyzerHandeler.detectHeaders(foundDoValues, databaseRecValues);


        //Create one set with all values needed for report
        Set<Evalue> reportValues = new HashSet<>();
        reportValues.addAll(missingDoValues);
        reportValues.addAll(foundDontValues);
        reportValues.addAll(foundRecValues);


        Ereport report = new Ereport(user, reportValues);

        return report;
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