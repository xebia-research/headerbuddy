package com.xebia.headerbuddy.models;

import com.xebia.headerbuddy.utilities.HeaderSerializer;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class RequestBehaviour {
    private String url;
    private String methodName;

    public RequestBehaviour(final String url, final String methodName) {
        this.url = url;
        this.methodName = methodName;
    }

    public List<Header> doRequest() throws Exception {
        URL target = new URL(this.url);

        HttpURLConnection connection = (HttpURLConnection) target.openConnection();
        switch (this.methodName) {
            case "PATCH":
                connection.setRequestProperty("X-HTTP-Method-Override", "PATCH");
                connection.setRequestMethod("POST");
                break;
            case "CONNECT":
                connection.setRequestProperty("X-HTTP-Method-Override", "CONNECT");
                connection.setRequestMethod("POST");
                break;
            default:
                connection.setRequestMethod(this.methodName);
                break;
        }

        Map<String, List<String>> headerFields = connection.getHeaderFields();

        List<Header> headers = HeaderSerializer.serialize(headerFields, this.url);

        return headers;
    }

    public Optional<CertificateDetails> getCertificateDetails() {
        Optional<CertificateDetails> details = Optional.empty();

        try {
            URL target = new URL(this.url);

            HttpsURLConnection connection = (HttpsURLConnection) target.openConnection();
            connection.connect();

            details = Optional.ofNullable(new CertificateDetails(connection.getServerCertificates()));
        } catch (MalformedURLException malEx) {
            //if it does'nt work the list is empty
            //TODO log error
        } catch (IOException ex) {
            //if it does'nt work the list is empty
            //TODO log error
        } catch (Exception e) {
            //if it does'nt work the list is empty
            //TODO log error
        }
        return details;
    }

    //Getters and setters

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

}
