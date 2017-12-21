package com.xebia.headerbuddy.models.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "category")
public class Ecategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotNull
    private String name;

    //Relations
    @OneToMany(mappedBy = "value")
    private Set<Evalue> values;

    //Constructors
    public Ecategory() {
    }

    public Ecategory(String name) {
        this.name = name;
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

    public Set<Evalue> getValues() {
        return values;
    }

    public void setValues(Set<Evalue> values) {
        this.values = values;
    }

}
