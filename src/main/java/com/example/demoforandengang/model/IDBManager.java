package com.example.demoforandengang.model;

import java.util.List;

public interface IDBManager {
    void insertPerson(Person person);

    List<Person> readAllPersons();

    Person readPerson(int id);

    void updatePerson(Person person);

    void deletePerson(Person person);

    boolean login(Person person);
}
