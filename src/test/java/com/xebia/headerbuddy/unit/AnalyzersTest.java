package com.xebia.headerbuddy.unit;

import com.xebia.headerbuddy.models.analyzer.Analyzer;
import com.xebia.headerbuddy.models.analyzer.DoAnalyzer;
import com.xebia.headerbuddy.models.analyzer.DontAnalyzer;
import com.xebia.headerbuddy.models.analyzer.RecommendationAnalyzer;
import com.xebia.headerbuddy.models.entities.Ecategory;
import com.xebia.headerbuddy.models.entities.Eheader;
import com.xebia.headerbuddy.models.entities.Evalue;
import org.junit.Assert;
import org.junit.Test;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class AnalyzersTest {

    @Test
    public void DoAnalyzerShouldDetectMissingDoValues(){
        Analyzer analyzer = new DoAnalyzer();

        // Arrange
        Eheader header1 = new Eheader("header1");
        Eheader header2 = new Eheader("header2");
        Eheader header3 = new Eheader("header3");

        Ecategory cat1 = new Ecategory("category1");

        Evalue val1 = new Evalue("value1", "", cat1, header1);
        Evalue val2 = new Evalue("value2", "", cat1, header2);
        Evalue val3 = new Evalue("unsafe", "", cat1, header3);

        Set<Evalue> toAnalyseValues = new HashSet<Evalue>() {{
            add(val1);
            add(val2);
        }};

        Set<Evalue> toCompareValues = new HashSet<Evalue>() {{
            add(val2);
            add(val3);
        }};

        // Act
        Set<Evalue> resultSet = analyzer.analyze(toAnalyseValues, toCompareValues);

        // Assert
        // ToAnalyze set misses one do header (header 3)
        Assert.assertEquals(header3.getName(), resultSet.iterator().next().getHeader().getName());
    }

    @Test
    public void DontAnalyzerShouldDetectDontValues(){
        Analyzer analyzer = new DontAnalyzer();

        // Arrange
        Eheader header1 = new Eheader("header1");
        Eheader header2 = new Eheader("header2");
        Eheader header3 = new Eheader("header3");

        Ecategory cat1 = new Ecategory("category1");

        Evalue val1 = new Evalue("value1", "", cat1, header1);
        Evalue val2 = new Evalue("value2", "", cat1, header2);
        Evalue val3 = new Evalue("unsafe", "", cat1, header3);

        Set<Evalue> toAnalyseValues = new HashSet<Evalue>() {{
            add(val1);
            add(val2);
            add(val3);
        }};

        Set<Evalue> toCompareValues = new HashSet<Evalue>() {{
            add(val2);
            add(val3);
        }};

        // Act
        Set<Evalue> resultSet = analyzer.analyze(toAnalyseValues, toCompareValues);

        // Assert
        // ToAnalyze has two Don't headers
        Assert.assertEquals(2, resultSet.size());

        Iterator<Evalue> itr = resultSet.iterator();
        Assert.assertEquals(header3.getName(), itr.next().getHeader().getName());
        Assert.assertEquals(header2.getName(), itr.next().getHeader().getName());
    }

    @Test
    public void RecommendationAnalyzerShouldDetectRecValues(){
        Analyzer analyzer = new RecommendationAnalyzer();

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
        Set<Evalue> resultSet = analyzer.analyze(toAnalyseValues, toCompareValues);

        // Assert
        // Should find one match "unsafe"
        Assert.assertEquals(val3.getValue(), resultSet.iterator().next().getValue());
        Assert.assertEquals(val3.getHeader().getName(), resultSet.iterator().next().getHeader().getName());
    }

}
