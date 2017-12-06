package com.xebia.headerbuddy.models.entities;

import javax.persistence.*;
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
    @ManyToMany(mappedBy = "headers")
    public Set<Eprofile> profiles;

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

    @OneToMany(mappedBy="header")
    public Set<Evalue> values;
}
