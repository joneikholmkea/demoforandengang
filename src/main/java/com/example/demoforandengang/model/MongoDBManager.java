package com.example.demoforandengang.model;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;
import static com.mongodb.client.model.Filters.eq;
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
        Person person = new Person("empty", "Ole Jensen", "123");
        //insertPerson(person);
        deletePerson(person);
    }

    @Override
    public void insertPerson(Person person) {
        MongoCollection<Document> collection = mongoDatabase.getCollection("persons");
        Document document = new Document();
        document.put("uname", person.getUname());
        document.put("password", person.getHashedPassword());
        document.put("phones", "{'1':'12341234','2':'54323421'}");
        collection.insertOne(document);
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
        MongoCollection<Document> collection = mongoDatabase.getCollection("persons");
        Bson query = eq("uname",person.getUname());
        collection.deleteOne(query);
    }

    @Override
    public boolean login(Person person) {
        return false;
    }
}
