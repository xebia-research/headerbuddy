package com.xebia.headerbuddy.models;

public class CustomErrorModel {
    private String error;

    public CustomErrorModel(String message) {
        this.error = message;
    }

    public String getError() {
        return this.error;
    }
}
