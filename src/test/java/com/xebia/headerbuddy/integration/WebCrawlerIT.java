package com.xebia.headerbuddy.integration;

import com.xebia.headerbuddy.utilities.WebCrawler;
import org.eclipse.jgit.util.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

public class WebCrawlerIT {

    private String targetUrl = "http://www.andonoz.com/";
    private WebCrawler crawler = new WebCrawler(targetUrl);

    @Before
    public void init(){
        try{
            crawler.crawl();
        } catch (Exception e){
            // Ignored
        }
    }

    @Test
    public void visitedPagesShouldContainTargetUrl() {
        Assert.assertTrue(crawler.getVisitedPages().contains(targetUrl));
    }

    @Test
    public void visitedPagesShouldOnlyContainLinksWithinDomain() {
        String domain = "";
        try {
            // Get domain of targetUrl
            URL url = new URL(targetUrl);
            domain = url.getHost();
        } catch (MalformedURLException e) {
            // Ignored
        }

        for (String url : crawler.getVisitedPages()) {
            if (!url.contains(domain)) {
                // Fail test if a link is found outside of domain
                Assert.fail();
            }
        }
    }

    @Test
    public void visitedPagesShouldNotContainDuplicates() {
        Set<String> visitedPages = crawler.getVisitedPages();

        // Loop through all visited pages
        for(String urlToFind : visitedPages){
            int count = 0;

            // Check per url how many times it occurs
            for (String url : visitedPages){
                if (urlToFind.equals(url)){
                    count++;
                }
            }
            // If the url occurs more than one time there are duplicates
            // So fail
            if (count > 1){
                Assert.fail();
            }
        }
    }
}