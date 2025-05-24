package com.example.eventmanagement.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;
    private LocalDate date;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

}
