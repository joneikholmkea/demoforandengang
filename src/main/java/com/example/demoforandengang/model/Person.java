package com.example.demoforandengang.model;

public class Person {

    private int id;
    private String uname;

    public Person(String uname) {
        this.uname = uname;
    }

//    public Person(int id, String uname) {
//        this.id = id;
//        this.uname = uname;
//    }
    public void setUname(String uname) {
        this.uname = uname;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getUname() {
        return uname;
    }
}
