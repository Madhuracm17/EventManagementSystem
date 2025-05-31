package com.example.eventsysten.controller;

import com.example.eventsysten.entity.Event;
import com.example.eventsysten.entity.Registration;
import com.example.eventsysten.entity.TicketType;
import com.example.eventsysten.entity.Payment;
import com.example.eventsysten.repository.EventRepository;
import com.example.eventsysten.repository.RegistrationRepository;
import com.example.eventsysten.repository.TicketTypeRepository;
import com.example.eventsysten.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Controller
public class EventController {
    private static final Logger logger = LoggerFactory.getLogger(EventController.class);

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private TicketTypeRepository ticketTypeRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    // Landing page
    @GetMapping("/")
    public String home() {
        return "index"; // Return index.html
    }

    // User events page (renders events.html)
    @GetMapping("/events")
    public String viewEvents(Model model) {
        logger.info("Fetching events for user display");
        List<Event> events = eventRepository.findAll();
        model.addAttribute("events", events);
        return "events"; // User view
    }

    // Admin dashboard page (renders admin.html)
    @GetMapping("/admin")
    public String adminDashboard() {
        return "admin";
    }

    // Admin events list page (renders admin-events.html)
    @GetMapping("/admin/events")
    public String viewAdminEvents(Model model) {
        logger.info("Fetching events for admin display");
        List<Event> events = eventRepository.findAll();
        model.addAttribute("events", events);
        return "admin-events"; // Admin view
    }

    // Show create event form (linked from admin)
    @GetMapping("/events/create")
    public String showCreateEventForm(Model model) {
        model.addAttribute("event", new Event());
        return "create-event";
    }

    // Handle create event form submission
    @PostMapping("/events/create")
    public String handleCreateEventForm(@ModelAttribute Event event) {
        eventRepository.save(event);
        return "redirect:/admin/events"; // Redirect admin back to admin event list
    }

    // Show registration form (linked from user events page)
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        logger.info("Fetching data for registration form");
        List<Event> events = eventRepository.findAll();
        model.addAttribute("events", events);

        List<TicketType> ticketTypes = List.of(); // Default empty list
        if (!events.isEmpty()) {
            // Fetch ticket types for the first event if events exist
            ticketTypes = ticketTypeRepository.findByEventId(events.get(0).getId());
        }
        model.addAttribute("ticketTypes", ticketTypes);
        
        // Add a new Registration object for form binding
        model.addAttribute("registration", new Registration());

        return "register";
    }

    // Handle registration form submission
    @PostMapping("/register")
    public String handleRegistrationForm(
            @RequestParam("event.id") Long eventId,
            @RequestParam("ticketType.id") Long ticketTypeId,
            @RequestParam("numberOfTickets") Integer numberOfTickets,
            @RequestParam("attendeeName") String attendeeName,
            @RequestParam("attendeeEmail") String attendeeEmail,
            Model model) {

        Event event = eventRepository.findById(eventId)
            .orElseThrow(() -> new RuntimeException("Event not found"));
        TicketType ticketType = ticketTypeRepository.findById(ticketTypeId)
            .orElseThrow(() -> new RuntimeException("Ticket type not found"));

        Double totalPrice = ticketType.getPrice() * numberOfTickets;

        Registration registration = new Registration();
        registration.setEvent(event);
        registration.setTicketType(ticketType);
        registration.setNumberOfTickets(numberOfTickets);
        registration.setAttendeeName(attendeeName);
        registration.setAttendeeEmail(attendeeEmail);
        registration.setTotalPrice(totalPrice);
        registrationRepository.save(registration);

        // Create payment record
        Payment payment = new Payment();
        payment.setAmount(totalPrice);
        payment.setPaymentMethod("Credit Card");
        payment.setRegistration(registration);
        payment.setEvent(event);
        payment.setStatus("PENDING");
        paymentRepository.save(payment);

        model.addAttribute("message", "Registration successful!");
        return "redirect:/events"; // Redirect user back to user event list
    }

    // API: Get all events (for dynamic forms, etc.)
    @GetMapping("/api/events")
    @ResponseBody
    public List<Event> getEvents() {
        logger.info("Fetching all events (API)");
        return eventRepository.findAll();
    }

    // API: Create new event (existing API, not used by new form flow)
    @PostMapping("/api/events")
    @ResponseBody
    public Event createEventApi(@RequestBody Event event) {
        logger.info("Creating new event (API): {}", event.getName());
        return eventRepository.save(event);
    }

    // API: Create ticket type for an event
    @PostMapping("/api/events/{eventId}/ticket-types")
    @ResponseBody
    public TicketType createTicketType(@PathVariable Long eventId, @RequestBody TicketType ticketType) {
        logger.info("Creating ticket type for event {} (API): name={}, price={}", 
            eventId, ticketType.getName(), ticketType.getPrice());
        
        Event event = eventRepository.findById(eventId)
            .orElseThrow(() -> {
                logger.error("Event not found with id: {}", eventId);
                return new RuntimeException("Event not found");
            });
        
        if (ticketType.getPrice() == null || ticketType.getPrice() <= 0) {
            logger.error("Invalid price for ticket type: {}", ticketType.getPrice());
            throw new RuntimeException("Ticket price must be greater than 0");
        }
        
        ticketType.setEvent(event);
        TicketType savedTicketType = ticketTypeRepository.save(ticketType);
        logger.info("Successfully created ticket type (API): {}", savedTicketType);
        return savedTicketType;
    }

    // API: Get ticket types for an event
    @GetMapping("/api/events/{eventId}/ticket-types")
    @ResponseBody
    public List<TicketType> getTicketTypes(@PathVariable Long eventId) {
        logger.info("Fetching ticket types for event {} (API)", eventId);
        return ticketTypeRepository.findByEventId(eventId);
    }

    // API: Get all registrations
    @GetMapping("/api/events/registrations")
    @ResponseBody
    public List<Registration> getRegistrations() {
        logger.info("Fetching all registrations (API)");
        List<Registration> registrations = registrationRepository.findAll();
        logger.info("Found {} registrations (API)", registrations.size());
        // Logging registration details (optional for API)
        // registrations.forEach(reg -> ...);
        return registrations;
    }
}
