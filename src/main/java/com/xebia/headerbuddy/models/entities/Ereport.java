package com.xebia.headerbuddy.models.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "report")
public class Ereport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull
    @Column(columnDefinition = "DATETIME")
    private Date date;

    //Relations
    @ManyToMany
    private Set<Evalue> values;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Euser user;
    @OneToMany(mappedBy = "report")
    private Set<Eurl> urls;

    //Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Set<Evalue> getValues() {
        return values;
    }

    public void setValues(Set<Evalue> values) {
        this.values = values;
    }

    public Euser getUser() {
        return user;
    }

    public void setUser(Euser user) {
        this.user = user;
    }

    public Set<Eurl> getUrls() {
        return urls;
    }

    public void setUrls(Set<Eurl> urls) {
        this.urls = urls;
    }

}
