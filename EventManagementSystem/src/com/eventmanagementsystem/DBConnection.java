package com.eventmanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/event_management";  // DB name
    private static final String USER = "root";  // MySQL username
    private static final String PASSWORD = "";  // MySQL password 

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);  // Establish the connection
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;  // Return null if connection failed
    }
}
