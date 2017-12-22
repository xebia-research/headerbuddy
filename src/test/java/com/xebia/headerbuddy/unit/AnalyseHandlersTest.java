package com.xebia.headerbuddy.unit;

import com.xebia.headerbuddy.models.analysehandlers.AnalyzerHandeler;
import com.xebia.headerbuddy.models.analysehandlers.DefaultHandeler;
import com.xebia.headerbuddy.models.analysehandlers.DontHandeler;
import com.xebia.headerbuddy.models.analysehandlers.RecommendationHandeler;
import com.xebia.headerbuddy.models.entities.Ecategory;
import com.xebia.headerbuddy.models.entities.Eheader;
import com.xebia.headerbuddy.models.entities.Evalue;
import org.junit.Assert;
import org.junit.Test;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class AnalyseHandlersTest {

    @Test
    public void DefaultHandlerShouldSeprateDifferencesBetweenSetHeaders() {
        // Arrange
        Eheader header1 = new Eheader("header1");
        Eheader header2 = new Eheader("header2");
        Eheader header3 = new Eheader("header3");

        Ecategory cat1 = new Ecategory("category1");

        Evalue val1 = new Evalue("value1", "", cat1, header1);
        Evalue val2 = new Evalue("value2", "", cat1, header1);
        Evalue val3 = new Evalue("value3", "", cat1, header2);
        Evalue val4 = new Evalue("value4", "", cat1, header3);

        Set<Evalue> toAnalyseValues = new HashSet<Evalue>() {{
            add(val1);
            add(val2);
            add(val3);
        }};


        Set<Evalue> toCompareValues = new HashSet<Evalue>() {{
            add(val3);
            add(val4);
        }};

        AnalyzerHandeler analyzerHandeler = new DefaultHandeler();

        // Act
        Set<Evalue> resultSet = analyzerHandeler.detectMissingHeaders(toAnalyseValues, toCompareValues);

        // Assert
        // toAnalyseValues misses one header
        Assert.assertEquals(val4.getValue(), resultSet.iterator().next().getValue());
        Assert.assertEquals(header3.getName(), resultSet.iterator().next().getHeader().getName());
    }

    @Test
    public void DefaultHandlerShouldCreateSetForMatchingHeaders() {
        // Arrange
        // Arrange
        Eheader header1 = new Eheader("header1");
        Eheader header2 = new Eheader("header2");
        Eheader header3 = new Eheader("header3");

        Ecategory cat1 = new Ecategory("category1");

        Evalue val1 = new Evalue("value1", "", cat1, header1);
        Evalue val2 = new Evalue("value2", "", cat1, header1);
        Evalue val3 = new Evalue("value3", "", cat1, header2);
        Evalue val4 = new Evalue("value4", "", cat1, header3);

        Set<Evalue> toAnalyseValues = new HashSet<Evalue>() {{
            add(val1);
            add(val2);
            add(val3);
        }};


        Set<Evalue> toCompareValues = new HashSet<Evalue>() {{
            add(val3);
            add(val4);
        }};

        AnalyzerHandeler analyzerHandeler = new DefaultHandeler();

        // Act
        Set<Evalue> resultSet = analyzerHandeler.detectHeaders(toAnalyseValues, toCompareValues);

        // Assert
        // toAnalyseValues and toCompareValues have one header in common
        Evalue resultVal = resultSet.iterator().next();
        Assert.assertEquals(header2.getName(), resultVal.getHeader().getName());
    }

    @Test
    public void RecommendationHandelerShouldDetectRecValue(){
        AnalyzerHandeler analyzerHandeler = new RecommendationHandeler();

        // Arrange
        Eheader header1 = new Eheader("header1");

        Ecategory cat1 = new Ecategory("category1");

        Evalue val1 = new Evalue("value1", "", cat1, header1);
        Evalue val2 = new Evalue("value2", "", cat1, header1);
        Evalue val3 = new Evalue("unsafe", "", cat1, header1);

        Set<Evalue> toAnalyseValues = new HashSet<Evalue>() {{
            add(val1);
            add(val2);
            add(val3);
        }};

        Set<Evalue> toCompareValues = new HashSet<Evalue>() {{
            add(val3);
        }};

        // Act
        Set<Evalue> resultSet =analyzerHandeler.detectHeaders(toAnalyseValues, toCompareValues);

        // Assert
        // Should find one match "unsafe"
        Assert.assertEquals(val3.getValue(), resultSet.iterator().next().getValue());
        Assert.assertEquals(val3.getHeader().getName(), resultSet.iterator().next().getHeader().getName());
    }

}
