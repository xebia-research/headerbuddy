package com.xebia.headerbuddy.models.dbentities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
    private String apiKey;
    private String email;

    protected User(){}

    public User(String apiKey, String email){
        this.apiKey = apiKey;
        this.email = email;
    }

    @Override
    public String toString(){
        return String.format(
                "User[apiKey='%d', email='%s']",
                apiKey, email);
    }
}
