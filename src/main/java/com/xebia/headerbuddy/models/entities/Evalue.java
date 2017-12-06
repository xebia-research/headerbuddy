package com.xebia.headerbuddy.models.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "value")
public class Evalue {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @NotNull
    private String value;

    private String description;

    @ManyToMany(mappedBy = "values")
    public Set<Ereport> reports;

    @ManyToOne
    @JoinColumn(name="category_fk")
    public Ecategory category;

    @ManyToOne
    @JoinColumn(name="header_fk")
    public Eheader header;
}