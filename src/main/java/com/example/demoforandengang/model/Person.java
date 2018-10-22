package com.example.demoforandengang.model;

public class Person {

    private int id;
    private String uname;
    private String password;


//    public Person(int id, String uname, String password) {
//        this.id = id;
//        this.uname = uname;
//        this.password = password;
//    }

//    public Person(String uname) {
//        this.uname = uname;
//    }

    public Person(int id, String uname) {
        this.id = id;
        this.uname = uname;
    }
    public Person(){
    }

    public Person(int id, String uname, String password) {
        this.id = id;
        this.uname = uname;
        this.password = password;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
