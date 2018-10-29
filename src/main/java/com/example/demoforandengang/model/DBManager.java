package com.example.demoforandengang.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// macOS: MySQL server is located at /usr/local/mysql_server/
// macOS: MySQL log files are stored at /usr/local/mysql_server/data/logfile.log
public class DBManager {

    final String DB_URL = "jdbc:mysql://localhost:3306/nydb?useSSL=false&serverTimezone=UTC";
    final String DB_USER = "root";
    final String DB_PW = "";
    private Connection connection = null;
    public static void main(String[] args) {

    }

    public DBManager(){
        dbInit();
    }

    private void dbInit(){
        try {
            connection = DriverManager.getConnection(DB_URL,DB_USER, DB_PW);
            Statement statement = connection.createStatement();
            String sql = "SET GLOBAL log_output = 'FILE'";  // get log messages in a file
            statement.execute(sql);
            sql =  "SET GLOBAL general_log_file = 'logfile1234.log'";
            statement.execute(sql);
            sql =  "SET GLOBAL general_log = 'ON'";
            statement.execute(sql);
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
        String sql = "INSERT INTO person VALUES (null,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, person.getUname());
            statement.setString(2, person.getPassword());
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
                String password = resultSet.getString(3);
                list.add(new Person(id,uname, password));
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

    public boolean login(Person person) {
        String sql = "SELECT * FROM person WHERE uname = ? and password = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, person.getUname());
            preparedStatement.setString(2, person.getPassword());
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                // hvis vi er her, så véd vi at brugeren findes med korrekt password
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
