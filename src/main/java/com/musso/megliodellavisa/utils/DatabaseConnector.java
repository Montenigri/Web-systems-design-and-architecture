package com.musso.megliodellavisa.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnector {
    private String url = "jdbc:mysql://localhost:3306/megliodellavisa";
    private String username = "root";
    private String password = "";
    public Connection getConnection(){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(this.url,this.username,this.password);
            System.out.println("Connected to Database.");
            return connection;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
            throw new RuntimeException("Cannot connect to database", e);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}