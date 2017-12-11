package com.xebia.headerbuddy.models.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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

    //Relations
    @ManyToMany(mappedBy = "values")
    public Set<Ereport> reports;
    @ManyToOne
    @JoinColumn(name="category_id")
    public Ecategory category;
    @ManyToOne
    @JoinColumn(name="header_id")
    public Eheader header;
    @OneToMany(mappedBy="value")
    public Set<Eurl> urls;

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getValue() { return value; }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}