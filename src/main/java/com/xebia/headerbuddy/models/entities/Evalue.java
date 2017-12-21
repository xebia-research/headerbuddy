package com.xebia.headerbuddy.models.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "value")
public class Evalue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotNull
    private String value;
    private String description;

    //Relations
    @ManyToMany(mappedBy = "values")
    private Set<Ereport> reports;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Ecategory category;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "header_id")
    private Eheader header;
    @OneToMany(mappedBy = "value")
    private Set<Eurl> urls;

    //Constructors
    public Evalue() {

    }

    public Evalue(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public Evalue(String value, String description, Ecategory category) {
        this.value = value;
        this.description = description;
        this.category = category;
    }

    public Evalue(String value, String description, Ecategory category, Eheader header) {
        this.value = value;
        this.description = description;
        this.category = category;
        this.header = header;
    }

    //Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Ereport> getReports() {
        return reports;
    }

    public void setReports(Set<Ereport> reports) {
        this.reports = reports;
    }

    public Ecategory getCategory() {
        return category;
    }

    public void setCategory(Ecategory category) {
        this.category = category;
    }

    public Eheader getHeader() {
        return header;
    }

    public void setHeader(Eheader header) {
        this.header = header;
    }

    public Set<Eurl> getUrls() {
        return urls;
    }

    public void setUrls(Set<Eurl> urls) {
        this.urls = urls;
    }

}
