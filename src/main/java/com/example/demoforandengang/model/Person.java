package com.example.demoforandengang.model;

import com.example.demoforandengang.security.Hashing;

public class Person {

    private String id;
    private String uname;
    private String password;


    public Person(String id, String uname) {
        this.id = id;
        this.uname = uname;
    }
    public Person(){
    }

    public Person(String id, String uname, String password) {
        this.id = id;
        this.uname = uname;
        this.password = password;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getUname() {
        return uname;
    }

    public String getPassword() {
        return password ;
    }
    public String getHashedPassword() {
        return Hashing.getHash(password) ;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString(){
        return uname + " " + password;
    }
}
