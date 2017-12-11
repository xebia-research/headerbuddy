package com.xebia.headerbuddy.models.entities;

import com.google.api.client.util.DateTime;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "report")
public class Ereport {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    @NotNull
    @Column(columnDefinition = "DATETIME")
    private DateTime date;

    //Relations
    @ManyToMany
    public Set<Evalue> values;
    @ManyToOne
    @JoinColumn(name="user_id")
    public Euser user;
    @OneToMany(mappedBy="report")
    public Set<Eurl> urls;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }
}
