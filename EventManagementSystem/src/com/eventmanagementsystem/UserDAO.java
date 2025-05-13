package com.eventmanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAO 
{
	//data access object
    // Database credentials 
    private static final String DB_URL = "jdbc:mysql://localhost:3306/event_management";
    private static final String DB_USER = "MySQL username";  //  MySQL username
    private static final String DB_PASSWORD = "MySQL password";  //  MySQL password

    // Method to add a user to the database
    public void addUser(User user) 
    {
        // SQL query to insert user data
        String sql = "INSERT INTO users (name, email, password, role, phone_number) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) 
        {
            // Set the parameters for the query
            pstmt.setString(1, user.getName());        // Set name
            pstmt.setString(2, user.getEmail());       // Set email
            pstmt.setString(3, user.getPassword());    // Set password
            pstmt.setString(4, user.getRole());        // Set role
            pstmt.setString(5, user.getPhoneNumber()); // Set phone number
            // Execute the query
            int rowsAffected = pstmt.executeUpdate();
            // Check if the user was added successfully
            if (rowsAffected > 0) 
            {
                System.out.println("User added successfully!");
            }
        } 
        catch (SQLException e) 
        {
            System.out.println("Failed to add User.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) 
    {
        // Example usage
        User newUser = new User("John Doe", "john.doe@example.com", "password123", "user", "1234567890");
        UserDAO userDao = new UserDAO();
        userDao.addUser(newUser);
    }
}

