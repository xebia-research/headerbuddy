package com.xebia.headerbuddy.unit;

import com.xebia.headerbuddy.models.analysehandlers.AnalyzerHandeler;
import com.xebia.headerbuddy.models.analysehandlers.DefaultHandeler;
import com.xebia.headerbuddy.models.entities.Ecategory;
import com.xebia.headerbuddy.models.entities.Eheader;
import com.xebia.headerbuddy.models.entities.Evalue;
import org.junit.Assert;
import org.junit.Test;
import java.util.HashSet;
import java.util.Set;

public class AnalyseHandlersTest {
    Eheader header1 = new Eheader("header1");
    Eheader header2 = new Eheader("header2");
    Eheader header3 = new Eheader("header3");

    Ecategory cat1 = new Ecategory("category1");

    Evalue val1 = new Evalue("value1", "", cat1, header1);
    Evalue val2 = new Evalue("value2", "", cat1, header1);
    Evalue val3 = new Evalue("value3", "", cat1, header2);
    Evalue val4 = new Evalue("value4", "", cat1, header3);

    Set<Evalue> valueSet1 = new HashSet<Evalue>() {{
        add(val1);
        add(val2);
        add(val3);
    }};


    Set<Evalue> valueSet2 = new HashSet<Evalue>() {{
        add(val3);
        add(val4);
    }};

    @Test
    public void DefaultHandlerShouldSeprateDifferencesBetweenSetHeaders() {
        AnalyzerHandeler analyzerHandeler = new DefaultHandeler();
        Set<Evalue> resultSet = analyzerHandeler.detectMissingHeaders(valueSet1, valueSet2);

        // Assert
        //valueSet1 misses one header
        Assert.assertEquals(val4.getValue(), resultSet.iterator().next().getValue());
        Assert.assertEquals(header3.getName(), resultSet.iterator().next().getHeader().getName());
    }

    @Test
    public void DefaultHandlerShouldCreateSetForMatchingHeaders() {
        AnalyzerHandeler analyzerHandeler = new DefaultHandeler();
        Set<Evalue> resultSet = analyzerHandeler.detectHeaders(valueSet1, valueSet2);

        for (Evalue val : resultSet){
            System.out.println(val.getHeader().getName());
        }
        // Assert
        //valueSet1 and valueSet2 have one header in common
        Evalue resultVal = resultSet.iterator().next();
        Assert.assertEquals(header2.getName(), resultVal.getHeader().getName());
    }
}
