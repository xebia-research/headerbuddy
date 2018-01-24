package com.xebia.headerbuddy.unit;

import com.xebia.headerbuddy.models.CertificateDetails;
import com.xebia.headerbuddy.models.HttpRequestMethod;
import com.xebia.headerbuddy.models.RequestBehaviour;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class CertificateDetailsTest {
    private final String correctUrl = "https://google.com";
    private final String badUrl = "http://andonoz.com";

    @Test
    public void getCertificateFromHttpsUrl() {
        Optional<CertificateDetails> certificate = new RequestBehaviour(correctUrl, HttpRequestMethod.GET).getCertificateDetails();

        Assert.assertTrue("There should be a certificate present", certificate.isPresent());
    }

    @Test
    public void getCertificateFromHttpUrl() {
        Optional<CertificateDetails> certificate = new RequestBehaviour(badUrl, HttpRequestMethod.GET).getCertificateDetails();

        Assert.assertTrue("There should be no certificate present because of http connection", !certificate.isPresent());
    }
}
