package com.example.eventmanagement.controller;

import com.example.eventmanagement.model.Event;
import com.example.eventmanagement.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    // Create new event
    @PostMapping("/create")
    public Event createEvent(@RequestBody Event event) {
        return eventRepository.save(event);
    }

    // List all events
    @GetMapping("/list")
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
}
