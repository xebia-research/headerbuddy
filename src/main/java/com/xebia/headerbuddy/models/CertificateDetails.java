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
        this.type = certificate.getType();
        this.hashCode = certificate.hashCode();
        this.publicKeyAlgorithm = certificate.getPublicKey().getAlgorithm();
        this.publicKeyFormat = certificate.getPublicKey().getFormat();

        X509Certificate t = (X509Certificate) certificate;
        this.issuer = t.getIssuerX500Principal().getName();
        this.expireDate = t.getNotAfter();
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
