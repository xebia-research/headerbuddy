package com.xebia.headerbuddy.utilities;

import com.xebia.headerbuddy.models.entities.Ecategory;
import com.xebia.headerbuddy.models.entities.Eheader;
import com.xebia.headerbuddy.models.entities.Eprofile;
import com.xebia.headerbuddy.models.entities.Evalue;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class ProtocolHandlerTest {

    // Arrange
    static final Eheader header1 = new Eheader("header1");
    static final Eheader header2 = new Eheader("header2");
    static final Eheader header3 = new Eheader("header3");
    static final Eheader header4 = new Eheader("header4");

    static final Ecategory cat1 = new Ecategory("do");

    static final Evalue val1 = new Evalue("value1", "", cat1, header1);
    static final Evalue val2 = new Evalue("value2", "", cat1, header2);
    static final Evalue val3 = new Evalue("unsafe", "", cat1, header3);
    static final Evalue val4 = new Evalue("onlyhttps", "", cat1, header4);

    static final Eprofile prof1 = new Eprofile("http");
    static final Eprofile prof2 = new Eprofile("https");

    //Dummy database data for values.
    static final Iterable<Evalue> databaseValues = new HashSet<Evalue>() {{
        add(val1);
        add(val2);
        add(val3);
        add(val4);
    }};

    //Dummy database data for profiles.
    static final Iterable<Eprofile> databaseProfiles = new HashSet<Eprofile>(){{
        add(prof1);
        add(prof2);
    }};

    @Test
    public void getEvaluesByProtocolTestWithOnlyHttp(){
        //arrange

        String profile = "web";
        //Setting the profiles to the headers.
        header1.setProfiles(new HashSet<Eprofile>(){{
            add(prof1);
            add(prof2);
        }});

        header2.setProfiles(new HashSet<Eprofile>(){{
            add(prof1);
            add(prof2);
        }});

        header3.setProfiles(new HashSet<Eprofile>(){{
            add(prof1);
            add(prof2);
        }});

        header4.setProfiles(new HashSet<Eprofile>(){{
            add(prof2);
        }});

        //Urls to get protocol.
        Set<String> listOfUrlsWithHttpOnly = new HashSet<String>(){{
           add("http://andonoz.com/");
           add("http://andonoz.com/Waifu.html");
           add("http://andonoz.com/Contact.html");
        }};

        // Act
        Set<Evalue> resultSet = ProtocolHandler.getEvaluesByProtocol(listOfUrlsWithHttpOnly, profile, databaseProfiles, databaseValues);

        // Assert
        // Check if only http headers are added.
        Assert.assertTrue(resultSet.contains(val1));
        Assert.assertTrue(resultSet.contains(val2));
        Assert.assertTrue(resultSet.contains(val3));
        Assert.assertTrue(!resultSet.contains(val4));
    }

    @Test
    public void getEvaluesByProtocolTestWithOnlyHttps(){
        //arrange

        String profile = "web";

        //Setting the profiles to the headers.
        header1.setProfiles(new HashSet<Eprofile>(){{
            add(prof1);
            add(prof2);
        }});

        header2.setProfiles(new HashSet<Eprofile>(){{
            add(prof1);
            add(prof2);
        }});

        header3.setProfiles(new HashSet<Eprofile>(){{
            add(prof1);
            add(prof2);
        }});

        header4.setProfiles(new HashSet<Eprofile>(){{
            add(prof2);
        }});

        //Urls to get protocol.
        Set<String> listOfUrlsWithHttpOnly = new HashSet<String>(){{
            add("https://andonoz.com/");
            add("https://andonoz.com/Waifu.html");
            add("https://andonoz.com/Contact.html");
        }};

        // Act
        Set<Evalue> resultSet = ProtocolHandler.getEvaluesByProtocol(listOfUrlsWithHttpOnly, profile, databaseProfiles, databaseValues);

        // Assert
        // Check if http & https headers are added.
        Assert.assertTrue(resultSet.contains(val1));
        Assert.assertTrue(resultSet.contains(val2));
        Assert.assertTrue(resultSet.contains(val3));
        Assert.assertTrue(resultSet.contains(val4));
    }

    @Test
    public void getEvaluesByProtocolTestWithHttpAndHttps(){
        //arrange

        String profile = "web";

        //Setting the profiles to the headers.
        header1.setProfiles(new HashSet<Eprofile>(){{
            add(prof1);
            add(prof2);
        }});

        header2.setProfiles(new HashSet<Eprofile>(){{
            add(prof1);
            add(prof2);
        }});

        header3.setProfiles(new HashSet<Eprofile>(){{
            add(prof1);
            add(prof2);
        }});

        header4.setProfiles(new HashSet<Eprofile>(){{
            add(prof2);
        }});

        //Urls to get protocol.
        Set<String> listOfUrlsWithHttpOnly = new HashSet<String>(){{
            add("http://andonoz.com/");
            add("http://andonoz.com/Waifu.html");
            add("https://andonoz.com/Contact.html");
        }};

        // Act
        Set<Evalue> resultSet = ProtocolHandler.getEvaluesByProtocol(listOfUrlsWithHttpOnly, profile, databaseProfiles, databaseValues);

        // Assert
        // Check if http & https headers are added.
        Assert.assertTrue(resultSet.contains(val1));
        Assert.assertTrue(resultSet.contains(val2));
        Assert.assertTrue(resultSet.contains(val3));
        Assert.assertTrue(resultSet.contains(val4));
    }

    @Test
    public void getEvaluesByProtocolTestWithUnknownProtocolsReturnHttpAsDefault(){
        //arrange

        String profile = "web";

        //Setting the profiles to the headers.
        header1.setProfiles(new HashSet<Eprofile>(){{
            add(prof1);
            add(prof2);
        }});

        header2.setProfiles(new HashSet<Eprofile>(){{
            add(prof1);
            add(prof2);
        }});

        header3.setProfiles(new HashSet<Eprofile>(){{
            add(prof1);
            add(prof2);
        }});

        header4.setProfiles(new HashSet<Eprofile>(){{
            add(prof2);
        }});

        //Urls to get protocol.
        Set<String> listOfUrlsWithHttpOnly = new HashSet<String>(){{
            add("ftp://andonoz.com/");
            add("ftp://andonoz.com/Waifu.html");
            add("ftp://andonoz.com/Contact.html");
        }};

        // Act
        Set<Evalue> resultSet = ProtocolHandler.getEvaluesByProtocol(listOfUrlsWithHttpOnly, profile, databaseProfiles, databaseValues);

        // Assert
        // Check if only http headers are added.
        Assert.assertTrue(resultSet.contains(val1));
        Assert.assertTrue(resultSet.contains(val2));
        Assert.assertTrue(resultSet.contains(val3));
        Assert.assertTrue(!resultSet.contains(val4));
    }
}
