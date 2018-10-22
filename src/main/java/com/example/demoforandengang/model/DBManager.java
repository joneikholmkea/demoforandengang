package com.example.demoforandengang.model;

import java.sql.*;
import java.util.ArrayList;
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
        String sql = "INSERT INTO person VALUES (null,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, person.getUname());
            int rows = statement.executeUpdate();
            System.out.println("Rows added: " + rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Person> readAllPersons(){
        String sql = "SELECT * FROM person";
        List<Person> list = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String uname = resultSet.getString(2);
                list.add(new Person(id,uname));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Person readPerson(int id){
        return null;
    }



    public void updatePerson(Person person){

    }

    public void deletePerson(Person person){
        String sql = "DELETE FROM person WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, person.getId());
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Slettet " + rowsAffected + " række(r).");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
