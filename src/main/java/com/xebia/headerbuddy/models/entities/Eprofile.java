package com.xebia.headerbuddy.models.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.ManyToMany;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "profile")
public class Eprofile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotNull
    private String name;

    //Relations
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Eheader> headers;

    //Constructors
    public Eprofile() {
        //Default Constructor.
    }

    public Eprofile(final String name) {
        this.name = name;
    }

    public Eprofile(final String name, final Set<Eheader> headers) {
        this.name = name;
        this.headers = headers;
    }

    //Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Set<Eheader> getHeaders() {
        return headers;
    }

    public void setHeaders(final Set<Eheader> headers) {
        this.headers = headers;
    }

}
