package com.xebia.headerbuddy.models.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "value")
public class Evalue {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @NotNull
    private String value;

    private String description;

    @ManyToOne
    @JoinColumn(name="category_fk")
    public Ecategory category;
}