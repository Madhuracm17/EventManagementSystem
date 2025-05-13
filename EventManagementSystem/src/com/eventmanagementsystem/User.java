package com.eventmanagementsystem;

public class User {
    private String name;
    private String email;
    private String password;
    private String role;
    private String phoneNumber;

    // Constructor
    public User(String name, String email, String password, String role, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.phoneNumber = phoneNumber;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
