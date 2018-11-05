package com.example.demoforandengang.model;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import java.util.List;

public class MongoDBManager implements IDBManager {

    private MongoClient mongoClient = new MongoClient("localhost", 27017);
    private MongoDatabase mongoDatabase;

    public static void main(String[] args) {
        MongoDBManager mongoDBManager = new MongoDBManager();
        System.out.println("MongoDB i gang...");
    }

    public MongoDBManager(){
        mongoDatabase = mongoClient.getDatabase("myMongoDB");
        System.out.println("database: " + mongoDatabase.getName());
    }

    @Override
    public void insertPerson(Person person) {

    }

    @Override
    public List<Person> readAllPersons() {
        return null;
    }

    @Override
    public Person readPerson(int id) {
        return null;
    }

    @Override
    public void updatePerson(Person person) {

    }

    @Override
    public void deletePerson(Person person) {

    }

    @Override
    public boolean login(Person person) {
        return false;
    }
}
