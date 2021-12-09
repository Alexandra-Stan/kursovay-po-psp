package dataBase;

import constants.Constants;

import java.sql.*;
import java.util.LinkedList;

public class Database {
    private  Connection connection;
    private  Statement statement;

    public Database() {
        connectionToDB();
        Tables tables = new Tables(connection,statement);
        tables.createTablesInDataBase();
    }

    public void connectionToDB(){
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(Constants.HOST_DATABASE+Constants.NAME_DATABASE,
                    Constants.USER_DATABASE,
                    Constants.PASSWORD_DATABASE);
            statement= connection.createStatement();

            System.out.println("Соединение с базой данных выполнено");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void appendNewUserInDatabase(String name,String lastName,String login,String password){

        try {
            String query = " insert into "+Constants.USERS_TABLE+ " (login, password,name,lastName )"
                    + " values ( ?, ?,?,?)";

            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1, login);
            preparedStmt.setString (2, password);
           // preparedStmt.setString (3, roll);
            preparedStmt.setString (3, name);
            preparedStmt.setString (4, lastName);


            preparedStmt.executeUpdate();
            System.out.println("Новый пользователь был добавлен");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public String verificationUserInDataBase(String login, String password){
        String user="";
        try {
            String query = "SELECT " + Constants.LOGIN + "," + Constants.PASSWORD+","+Constants.ID+","+Constants.ROLL + " FROM " + Constants.USERS_TABLE +
                    " WHERE " + Constants.LOGIN + " = " + "'" + login + "'" + " AND " + Constants.PASSWORD + " = " + "'" + password + "'";

            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                    user+=rs.getString(Constants.LOGIN)+" ";
                    user+=rs.getString(Constants.PASSWORD)+" ";
                    user+=rs.getString(Constants.ROLL);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(user.equals("")) {
            System.out.println("Пользователь не найден");
            return "false";
        }
        else {
            return user;
        }
    }

    public LinkedList<String> conclusionAllUsers() {

        LinkedList<String> users = new LinkedList<>();
        String query = "SELECT "+Constants.ID+" , " + Constants.NAME_USER+" , "+Constants.LAST_NAME_USER+" , "
                +Constants.LOGIN+" , " + Constants.PASSWORD+" , " + Constants.ROLL+ " FROM " + Constants.USERS_TABLE ;
        ResultSet rs = null;
        String user="";
        try {
            rs = statement.executeQuery(query);

            while (rs.next()) {

                user+=rs.getString(Constants.ID)+" ";
                user+=rs.getString(Constants.NAME_USER)+" ";
                user+=rs.getString(Constants.LAST_NAME_USER)+" ";
                user+=rs.getString(Constants.LOGIN)+" ";
                user+=rs.getString(Constants.PASSWORD)+" ";
                user+=rs.getString(Constants.ROLL)+" ";
                users.add(user);
                user="";
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }


    public void deleteUserInDataBase(int id)  {
        System.out.println(id);
        String selectSQL = "DELETE FROM "+Constants.USERS_TABLE +  " WHERE id = ?";
        try {
            connection.prepareStatement(selectSQL);
            PreparedStatement preparedStmt = connection.prepareStatement(selectSQL);
            preparedStmt.setInt(1, id);
            preparedStmt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    public  void redactionUserDatabase(int id,String name ,String lastName,String login ,String password,String roll )  {

        String query = "UPDATE users SET name  = ?, lastName = ? ,login = ?,password = ? , roll = ?   WHERE id = ?";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString   (1, name);
            preparedStmt.setString(2, lastName);
            preparedStmt.setString(3, login);
            preparedStmt.setString(4, password);
            preparedStmt.setString(8, roll);
            preparedStmt.setInt(9, id);

            preparedStmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public LinkedList<String> showAllDiscounts(){

        LinkedList<String> list = new LinkedList<>();
        String query = "SELECT "+Constants.DISCOUNT_ID +" , " + Constants.DISCOUNT_NAME +" , "+Constants.DISCOUNT_SURNAME +" , "
                +Constants.DISCOUNT_NUMBER +" ," +Constants.DISCOUNT_SHOP + " FROM " + Constants.DISCOUNTS_TABLE;
        ResultSet rs = null;
        String contribution="";
        try {
            rs = statement.executeQuery(query);

            while (rs.next()) {

                contribution+=rs.getString(Constants.DISCOUNT_ID)+" ";
                contribution+=rs.getString(Constants.DISCOUNT_NAME)+" ";
                contribution+=rs.getString(Constants.DISCOUNT_SURNAME)+" ";
                contribution+=rs.getString(Constants.DISCOUNT_NUMBER)+" ";
                contribution+=rs.getString(Constants.DISCOUNT_SHOP)+" ";

                list.add(contribution);

                contribution="";
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public void addDiscountInDataBase(String name, String surname, String dnumber, String shopName){

        try {
            String query = " insert into "+Constants.DISCOUNTS_TABLE +" (name, surname,dnumber,shopName)"
                    + " values ( ?, ?,?,?)";

            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1, name);
            preparedStmt.setString (2, surname);
            preparedStmt.setString (3, dnumber);
            preparedStmt.setString (4, shopName);


            preparedStmt.executeUpdate();
            System.out.println("Новая скидка была добавлена ");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteDiscountInDataBase(int id)  {
        System.out.println(id);
        String selectSQL = "DELETE FROM "+Constants.DISCOUNTS_TABLE +  " WHERE id = ?";
        try {
            connection.prepareStatement(selectSQL);
            PreparedStatement preparedStmt = connection.prepareStatement(selectSQL);
            preparedStmt.setInt(1, id);
            preparedStmt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public  void redactionDiscountInDataBase(int id, String name, String surname, String dnumber, String shopName )  {

        String query = "UPDATE "+Constants.DISCOUNTS_TABLE +" SET name  = ?, surname = ? ,dnumber = ?,shopName = ?   WHERE id = ?";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString   (1, name);
            preparedStmt.setString(2, surname);
            preparedStmt.setString(3, dnumber);
            preparedStmt.setString(4, shopName);
            preparedStmt.setInt(5, id);

            preparedStmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}
