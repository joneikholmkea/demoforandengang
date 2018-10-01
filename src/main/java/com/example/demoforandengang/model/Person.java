package com.example.demoforandengang.model;

public class Person {

    private int id;
    private String uname;

    public Person(int id, String uname) {
        this.id = id;
        this.uname = uname;
    }

    public int getId() {
        return id;
    }

    public String getUname() {
        return uname;
    }
}
