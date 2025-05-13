package com.eventmanagementsystem;

public class MainTest {
    public static void main(String[] args) {
        // Create a new User object
        User newUser = new User("John Doe", "john.doe@example.com", "password123", "user", "1234567890");

        // Create an instance of UserDAO
        UserDAO userDao = new UserDAO();

        // Add the new user to the database
        userDao.addUser(newUser);
    }
}

