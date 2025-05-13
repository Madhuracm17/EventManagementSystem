package com.eventmanagementsystem;

public class Ticket {
    private int ticketId;
    private int eventId;
    private int userId;
    private int quantity;
    private double price;

    // Constructor
    public Ticket(int ticketId, int eventId, int userId, int quantity, double price) {
        this.ticketId = ticketId;
        this.eventId = eventId;
        this.userId = userId;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters and Setters
    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
