package com.xebia.headerbuddy.models;

import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Date;

public class CertificateDetails {
    private String type;
    private int hashCode;
    private String publicKeyAlgorithm;
    private String publicKeyFormat;
    private Date expireDate;
    private String issuer;


    public CertificateDetails(final Certificate[] certificateDetails) {
        setCertificate(certificateDetails[0]);
    }

    public void setCertificate(final Certificate certificate) {
        type = certificate.getType();
        hashCode = certificate.hashCode();
        publicKeyAlgorithm = certificate.getPublicKey().getAlgorithm();
        publicKeyFormat = certificate.getPublicKey().getFormat();

        X509Certificate t = (X509Certificate) certificate;
        issuer = t.getIssuerX500Principal().getName();
        expireDate = t.getNotAfter();
    }

    public String getType() {
        return type;
    }

    public int getHashCode() {
        return hashCode;
    }

    public String getPublicKeyAlgorithm() {
        return publicKeyAlgorithm;
    }

    public String getPublicKeyFormat() {
        return publicKeyFormat;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public String getIssuer() {
        return issuer;
    }
}
