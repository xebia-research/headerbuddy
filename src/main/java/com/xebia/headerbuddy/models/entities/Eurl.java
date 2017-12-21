package com.xebia.headerbuddy.models.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "url")
public class Eurl {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotNull
    private String url;

    //Relations
    @ManyToOne
    @JoinColumn(name = "report_id")
    private Ereport report;
    @ManyToOne
    @JoinColumn(name = "value_id")
    private Evalue value;

    //Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Ereport getReport() {
        return report;
    }

    public void setReport(Ereport report) {
        this.report = report;
    }

    public Evalue getValue() {
        return value;
    }

    public void setValue(Evalue value) {
        this.value = value;
    }

}
