package com.xebia.headerbuddy.unit;

import com.xebia.headerbuddy.utilities.WebCrawler;
import org.junit.Assert;
import org.junit.Test;

public class WebCrawlerTest {

    private String targetUrl = "http://www.example.exam.ple/";
    private WebCrawler crawler = new WebCrawler(targetUrl);

    @Test
    public void visitedPagesShouldContainTargetUrl(){
        // Arrange
        // ...

        // Act
        try {
            crawler.crawl();
        } catch (Exception e){
            // Ignored
        }

        // Assert
        Assert.assertTrue(crawler.getVisitedPages().contains(targetUrl));
    }
}
