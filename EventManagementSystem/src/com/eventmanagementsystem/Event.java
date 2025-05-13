package com.eventmanagementsystem;

import java.sql.Date;

public class Event {
    private int eventId;
    private String eventName;
    private String eventDescription;
    private Date eventDateTime;
    private String venue;
    private int totalSeats;
    private int organizerId;

    // Constructor
    public Event(int eventId, String eventName, String eventDescription, Date eventDateTime, String venue, int totalSeats, int organizerId) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventDateTime = eventDateTime;
        this.venue = venue;
        this.totalSeats = totalSeats;
        this.organizerId = organizerId;
    }

    // Getters and Setters
    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public Date getEventDateTime() {
        return eventDateTime;
    }

    public void setEventDateTime(Date eventDateTime) {
        this.eventDateTime = eventDateTime;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getOrganizerId() {
        return organizerId;
    }
    public void setOrganizerId(int organizerId) {
        this.organizerId = organizerId;
    }
}
