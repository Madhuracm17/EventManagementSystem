package com.eventmanagementsystem;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventDAO {

    // Insert new Event
    public boolean addEvent(Event event) {
        String sql = "INSERT INTO events (eventId, eventName, description, date, location, totalSeats, organizerId) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, event.getEventId());
            stmt.setString(2, event.getEventName());
            stmt.setString(3, event.getEventDescription());
            stmt.setDate(4, event.getEventDateTime());
            stmt.setString(5, event.getVenue()); // Venue
            stmt.setInt(6, event.getTotalSeats()); // Total seats
            stmt.setInt(7, event.getOrganizerId()); // Organizer ID

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Get Event by ID
    public Event getEventById(int id) {
        String sql = "SELECT * FROM events WHERE eventId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Event(
                    rs.getInt("eventId"),
                    rs.getString("eventName"),
                    rs.getString("description"),
                    rs.getDate("date"),
                    rs.getString("location"),
                    rs.getInt("totalSeats"), // Correct column name
                    rs.getInt("organizerId") // Corrected column name
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Get All Events
    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT * FROM events";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Event event = new Event(
                    rs.getInt("eventId"),
                    rs.getString("eventName"),
                    rs.getString("description"),
                    rs.getDate("date"),
                    rs.getString("location"),
                    rs.getInt("totalSeats"), // Corrected column name
                    rs.getInt("organizerId") // Corrected column name
                );
                events.add(event);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return events;
    }

    // Update Event
    public boolean updateEvent(Event event) {
        String sql = "UPDATE events SET eventName=?, description=?, date=?, location=?, totalSeats=?, organizerId=? WHERE eventId=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, event.getEventName());
            stmt.setString(2, event.getEventDescription());
            stmt.setDate(3, event.getEventDateTime());
            stmt.setString(4, event.getVenue());
            stmt.setInt(5, event.getTotalSeats()); // Corrected to totalSeats
            stmt.setInt(6, event.getOrganizerId()); // Corrected to organizerId
            stmt.setInt(7, event.getEventId());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Delete Event
    public boolean deleteEvent(int id) {
        String sql = "DELETE FROM events WHERE eventId=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
