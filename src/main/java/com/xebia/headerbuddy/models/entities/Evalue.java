package com.xebia.headerbuddy.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "value")
public class Evalue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Integer id;
    @NotNull
    private String value;
    @Lob
    private String description;

    //Relations
    @JsonIgnore
    @ManyToMany(mappedBy = "values")
    private Set<Ereport> reports;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Ecategory category;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "header_id")
    private Eheader header;

    //Constructors
    public Evalue() {
        //Default Constructor.
    }

    public Evalue(final String value) {
        this.value = value;
    }

    public Evalue(final String value, final String description) {
        this.value = value;
        this.description = description;
    }

    public Evalue(final String value, final String description, final Ecategory category) {
        this.value = value;
        this.description = description;
        this.category = category;
    }

    public Evalue(final String value, final String description, final Ecategory category, final Eheader header) {
        this.value = value;
        this.description = description;
        this.category = category;
        this.header = header;
    }

    //Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public Set<Ereport> getReports() {
        return reports;
    }

    public void setReports(final Set<Ereport> reports) {
        this.reports = reports;
    }

    public Ecategory getCategory() {
        return category;
    }

    public void setCategory(final Ecategory category) {
        this.category = category;
    }

    public Eheader getHeader() {
        return header;
    }

    public void setHeader(final Eheader header) {
        this.header = header;
    }

}
