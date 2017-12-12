package com.xebia.headerbuddy.models.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "header")
public class Eheader
{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    @NotNull
    private String name;

    //Relations
    @ManyToMany(mappedBy = "headers")
    public Set<Eprofile> profiles;
    @OneToMany(mappedBy="header")
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
}
