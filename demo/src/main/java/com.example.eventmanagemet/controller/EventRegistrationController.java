package com.example.eventmanagement.controller;

import com.example.eventmanagement.model.EventRegistration;
import com.example.eventmanagement.repository.EventRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registrations")
public class EventRegistrationController {

    @Autowired
    private EventRegistrationRepository registrationRepository;

    // Register user for an event
    @PostMapping("/register")
    public EventRegistration register(@RequestBody EventRegistration registration) {
        return registrationRepository.save(registration);
    }

    // List all registrations for a user
    @GetMapping("/user/{userId}")
    public List<EventRegistration> getUserRegistrations(@PathVariable Long userId) {
        return registrationRepository.findByUserId(userId);
    }

    // List all users for an event
    @GetMapping("/event/{eventId}")
    public List<EventRegistration> getEventRegistrations(@PathVariable Long eventId) {
        return registrationRepository.findByEventId(eventId);
    }
}
