package com.xebia.headerbuddy.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "header")
public class Eheader {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotNull
    private String name;

    //Relations
    @JsonIgnore
    @ManyToMany(mappedBy = "headers", cascade = CascadeType.ALL)
    private Set<Eprofile> profiles;

    @JsonIgnore
    @OneToMany(mappedBy = "header")
    private Set<Evalue> values;

    //Constructors
    public Eheader(){

    }

    public Eheader(String name){
        this.name = name;
    }

    public Eheader(String name, Set<Evalue> values){
        this.name = name;
        this.values = values;
    }

    public Eheader(String name, Set<Eprofile> profiles, Set<Evalue> values) {
        this.name = name;
        this.profiles = profiles;
        this.values = values;
    }

    //Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Eprofile> getProfiles() {
        return profiles;
    }

    public void setProfiles(Set<Eprofile> profiles) {
        this.profiles = profiles;
    }

    public Set<Evalue> getValues() {
        return values;
    }

    public void setValues(Set<Evalue> values) {
        this.values = values;
    }

}
