package com.xebia.headerbuddy.utilities;

import com.xebia.headerbuddy.models.entities.Ereport;

public abstract class HtmlParser {
    public static String convertToHtml(Ereport report){
        // Stringbuilder for html
        StringBuilder htmlString = new StringBuilder();

        // Start of document
        htmlString.append("<!doctype html>");
        htmlString.append("<html>");

        // Head element
        htmlString.append("<head>");
        htmlString.append("<title>HeaderBuddy</title>");
        htmlString.append("</head>");

        // Body element
        htmlString.append("<body>");

        // Title
        htmlString.append("<p>");
        htmlString.append("<h1>Headerbuddy</h1>");
        htmlString.append("<hr />");
        htmlString.append("</p>");

        // User details
        htmlString.append("<p>");
        htmlString.append("<h3>User details:</h3>");
        htmlString.append("<span>Email: " + report.getUser().getEmail() + "</span>");
        htmlString.append("<br />");
        htmlString.append("<span>Api Key: " + report.getUser().getApikey() + "</span>");
        htmlString.append("<hr />");
        htmlString.append("</p>");

        // End of document
        htmlString.append("</body>");
        htmlString.append("</html>");

        return htmlString.toString();
    }
}
