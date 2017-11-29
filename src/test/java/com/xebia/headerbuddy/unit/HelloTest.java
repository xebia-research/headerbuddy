package com.xebia.headerbuddy.unit;

import org.junit.Assert;
import org.junit.Test;

public class HelloTest {

    @Test
    public void testGetId(){
        Hello p1 = new Hello(1, "Hallo");

        Assert.assertEquals(p1.getId(), 1);
    }

    @Test
    public void testGetGreeting(){
        Hello p1 = new Hello(1, "Hallo");

        Assert.assertEquals(p1.getGreeting(), "Hallo");
    }
}