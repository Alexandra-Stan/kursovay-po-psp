package dataBase;

import constants.Constants;

import java.sql.*;

public class Tables {
    private final Connection connection;
    private final Statement statement;

    public Tables(Connection connection, Statement statement) {
        this.connection = connection;
        this.statement = statement;

    }

    public void createTablesInDataBase(){
        addTableUserInDateBase();
        addTableProduct();
        addTableAdminsInDateBase();
        //addTableShopDiscInDateBase();
        addTableShopInDateBase();
    }

    private void addTableUserInDateBase(){
        if(tableExists(Constants.USERS_TABLE)) {
            try {
                String SQL = "CREATE TABLE "+Constants.USERS_TABLE +
                        "( " +
                        " id  SERIAL PRIMARY KEY," +
                        " login VARCHAR (50), " +
                        " name VARCHAR (50), " +
                        " lastName VARCHAR (50), " +
                        " password VARCHAR (50), " +
                        " roll VARCHAR (50)"+
                        ")";
                System.out.println("Таблица 1 создана!");
                statement.executeUpdate(SQL);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void addTableProduct(){
        if(tableExists(Constants.DISCOUNTS_TABLE)) {
            try {
                String SQL = "CREATE TABLE "+Constants.DISCOUNTS_TABLE +
                        "( " +
                        " id  SERIAL PRIMARY KEY," +
                        " name VARCHAR (50), " +
                        " surname VARCHAR (50), " +
                        " dnumber VARCHAR (50), " +
                        " shopName VARCHAR (50) " +
                        ")";
                System.out.println("Таблица 2 создана!");
                statement.executeUpdate(SQL);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    private void addTableShopInDateBase(){
        if(tableExists(Constants.SHOP_TABLE)) {
            try {
                String SQL = "CREATE TABLE "+Constants.SHOP_TABLE +
                        "( " +
                        " id  SERIAL PRIMARY KEY," +
                        " shopName VARCHAR (50), " +
                        " address VARCHAR (50) " +
                        ")";
                System.out.println("Таблица 3 создана!");
                statement.executeUpdate(SQL);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    private void addTableAdminsInDateBase(){
        if(tableExists(Constants.ADMIN_TABLE)) {
            try {
                String SQL = "CREATE TABLE "+Constants.ADMIN_TABLE +
                        "( " +
                        " id  SERIAL PRIMARY KEY," +
                        " login VARCHAR (50), " +
                        " password VARCHAR (50) " +
                        ")";
                System.out.println("Таблица 4 создана!");
                statement.executeUpdate(SQL);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

//    private void addTableShopDiscInDateBase(){
//        if(tableExists(Constants.SHOPDISC_TABLE)) {
//            try {
//                String SQL = "CREATE TABLE "+Constants.SHOPDISC_TABLE +
//                        "( " +
//                        " id  SERIAL PRIMARY KEY," +
//                        " shopDiscName VARCHAR (50), " +
//                        " discList VARCHAR (50) " +
//                        ")";
//                System.out.println("Таблица 2 создана!");
//                statement.executeUpdate(SQL);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
    private boolean tableExists(String nameTable){

        try{
            DatabaseMetaData md = connection.getMetaData();
            ResultSet rs = md.getTables(null, null, nameTable, null);
            rs.last();
            return rs.getRow() <= 0;

        }catch(SQLException ignored){

        }

        return true;
    }
}
