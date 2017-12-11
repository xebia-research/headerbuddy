package com.xebia.headerbuddy.models.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "category")
public class Ecategory {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @NotNull
    private String name;

    //Relations
    @OneToMany(mappedBy="value")
    public Set<Evalue> values;

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

    //Constructors
    public Ecategory(){}

    public Ecategory(String name){
        this.name = name;
    }
}
