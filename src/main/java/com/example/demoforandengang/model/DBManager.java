package com.example.demoforandengang.model;

import java.sql.*;
import java.util.List;

public class DBManager {

    final String DB_URL = "jdbc:mysql://localhost:3306/nydb?useSSL=false&serverTimezone=UTC";
    final String DB_USER = "root";
    final String DB_PW = "";
    private Connection connection = null;
    public static void main(String[] args) {
        DBManager dbManager = new DBManager();
        dbManager.dbInit();
        // test den nye metode insertPerson(...) her
        Person p = new Person("Sikker Ole med id");
        dbManager.insertPerson(p);
    }

    public DBManager(){
        dbInit();
    }

    private void dbInit(){
        try {
            connection = DriverManager.getConnection(DB_URL,DB_USER, DB_PW);
            System.out.println("OK dbInit() " + connection.getCatalog());
        }catch (Exception e){
            System.out.println("fejl i dbInit() " + e);
        }
    }

    public void unsecureInsertPerson(Person person){
        String sql = "INSERT INTO person VALUES (null, '" + person.getUname() + "')";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertPerson(Person person){
        String sql = "INSERT INTO person VALUES (?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, 0);
            statement.setString(2, person.getUname());
            int rows = statement.executeUpdate();
            System.out.println("Rows added: " + rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Person> readAllPersons(){
        return null;
    }

    public Person readPerson(int id){
        return null;
    }



    public void updatePerson(Person person){

    }

    public void deletePerson(Person person){

    }
}
