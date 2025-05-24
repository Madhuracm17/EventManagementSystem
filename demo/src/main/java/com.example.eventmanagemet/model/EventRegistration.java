package com.example.eventmanagement.model;

import jakarta.persistence.*;

@Entity
public class EventRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long eventId;

    public EventRegistration() {}

    public EventRegistration(Long userId, Long eventId) {
        this.userId = userId;
        this.eventId = eventId;
    }

    public Long getId() { return id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getEventId() { return eventId; }
    public void setEventId(Long eventId) { this.eventId = eventId; }
}
