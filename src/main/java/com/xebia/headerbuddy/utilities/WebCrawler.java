package com.xebia.headerbuddy.utilities;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class WebCrawler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    //unique set
    private Set<String> pagesVisited = new HashSet<>();
    //bunch of url's
    private List<String> pagesToVisit = new LinkedList<>();
    //starting domain
    private String startUrl;

    public WebCrawler(final String startUrl) {
        this.pagesToVisit.add(startUrl);
        this.startUrl = startUrl;
    }

    protected String getUrl() throws Exception {
        String url;

        // if true it continues till it finds an url that's not visited yet
        do {
            url = this.pagesToVisit.remove(0);
        } while (this.pagesVisited.contains(url));

        //check if domain is in the same starting domain
        String domain = new URL(url).getHost().startsWith("www.") ? new URL(url).getHost().substring(4) : new URL(url).getHost();
        String startDomain = new URL(startUrl).getHost().startsWith("www.") ? new URL(startUrl).getHost().substring(4) : new URL(startUrl).getHost();

        if (!domain.equals(startDomain)) {
            return getUrl();
        }

        this.pagesVisited.add(url);
        return url;
    }

    public Set<String> getVisitedPages() {
        return this.pagesVisited;
    }

    public void crawl(int limit) {
        try {
            while (!this.pagesToVisit.isEmpty()) {
                if (pagesVisited.size() >= limit) {
                    return;
                }

                String url = getUrl();
                try {
                    Document doc = Jsoup.connect(url).get();
                    Elements linksOnPage = doc.select("a[href]");

                    for (Element l : linksOnPage) {
                        URL uri = new URL(l.absUrl("href"));

                        //check for # and /
                        String lastCharInUri = uri.toString().substring(uri.toString().length() - 1, uri.toString().length());
                        if (lastCharInUri.equals("#") || lastCharInUri.equals("/")) {
                            pagesToVisit.add(uri.toString().substring(0, uri.toString().length() - 1));
                        } else {
                            pagesToVisit.add(uri.toString());
                        }
                    }
                } catch (Exception e) {
                    // do nothing
                    logger.error("url: " + url + " Error: " +  e.getMessage());
                }
            }
        } catch (Exception e) {
            // Ignore
            logger.error(e.getMessage());
        }
    }
}
