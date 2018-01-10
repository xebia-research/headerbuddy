package com.xebia.headerbuddy.models.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "report")
public class Ereport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private int id;
    @NotNull
    @Column(columnDefinition = "DATETIME")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;

    //Relations
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Euser user;

    @JsonProperty("visited pages")
    @OneToMany(mappedBy = "report")
    private Set<Eurl> urls;

    @JsonProperty("advice")
    @ManyToMany
    private Set<Evalue> values;

    //Constructors
    public Ereport() {
        //Default Constructor.
    }

    public Ereport(final Euser user) {
        this.user = user;
        this.date = new Date();
    }

    public Ereport(final Euser user, final Set<Evalue> values) {
        this.date = new Date();
        this.user = user;
        this.values = values;
    }

    //Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(final Date date) {
        this.date = date;
    }

    public Set<Evalue> getValues() {
        return values;
    }

    public void setValues(final Set<Evalue> values) {
        this.values = values;
    }

    public Euser getUser() {
        return this.user;
    }

    public void setUser(final Euser user) {
        this.user = user;
    }

    public Set<Eurl> getUrls() {
        return urls;
    }

    public void setUrls(final Set<Eurl> urls) {
        this.urls = urls;
    }
}
