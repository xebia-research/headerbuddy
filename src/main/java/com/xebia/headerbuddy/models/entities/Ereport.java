package com.xebia.headerbuddy.models.entities;

import com.google.api.client.util.DateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.security.Timestamp;
import java.util.Date;

@Entity
@Table(name = "report")
public class Ereport {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    @NotNull
    @Column(columnDefinition = "DATETIME")
    private DateTime date;

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
