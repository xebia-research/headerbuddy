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
        String returnString = null;
        returnString += apiKey + ", " + email;
        return  returnString;
    }
}
