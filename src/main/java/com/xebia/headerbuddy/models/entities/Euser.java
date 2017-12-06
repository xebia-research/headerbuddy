package com.xebia.headerbuddy.models.entities;

import com.google.api.client.util.DateTime;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Set;

@Entity
@Table(name = "user")
public class Euser {

    @Id
    private String apikey;

    @NotNull
    private String email;

    @NotNull
    @Column(columnDefinition = "DATETIME")
    private DateTime creationdate;

    public String getApikey() { return apikey;}

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public DateTime getCreationdate() {
        return creationdate;
    }

    public void setCreationdate(DateTime creationdate) {
        this.creationdate = creationdate;
    }

    @OneToMany(mappedBy="user")
    public Set<Ereport> reports;
}
