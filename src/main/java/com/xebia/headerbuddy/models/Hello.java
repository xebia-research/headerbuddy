package com.xebia.headerbuddy.models;

public class Hello
{
    private int id;
    private String greeting;


    public Hello(int id, String greeting)
    {
        this.id = id;
        this.greeting = greeting;
    }

    public int getId()
    {
        return this.id;
    }

    public String getGreeting()
    {
        return this.greeting;
    }
}
