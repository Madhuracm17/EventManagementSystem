package com.eventmanagementsystem;

import java.sql.*;

public class TicketDAO {

    // Insert new Ticket
    public boolean addTicket(Ticket ticket) {
        String sql = "INSERT INTO tickets (ticketId, eventId, userId, quantity, price) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, ticket.getTicketId());
            stmt.setInt(2, ticket.getEventId());
            stmt.setInt(3, ticket.getUserId());
            stmt.setInt(4, ticket.getQuantity());
            stmt.setDouble(5, ticket.getPrice());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Get Ticket by ID
    public Ticket getTicketById(int id) {
        String sql = "SELECT * FROM tickets WHERE ticketId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Ticket(
                    rs.getInt("ticketId"),
                    rs.getInt("eventId"),
                    rs.getInt("userId"),
                    rs.getInt("quantity"),
                    rs.getDouble("price")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Get Ticket Quantity
    public int getQuantity(int eventId) {
        String sql = "SELECT quantity FROM tickets WHERE eventId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, eventId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("quantity"); // Return the quantity of tickets available for the given event
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // Return 0 if no tickets are found or an error occurs
    }

    // Update Ticket
    public boolean updateTicket(Ticket ticket) {
        String sql = "UPDATE tickets SET eventId=?, userId=?, quantity=?, price=? WHERE ticketId=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, ticket.getEventId());
            stmt.setInt(2, ticket.getUserId());
            stmt.setInt(3, ticket.getQuantity());
            stmt.setDouble(4, ticket.getPrice());
            stmt.setInt(5, ticket.getTicketId());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Delete Ticket
    public boolean deleteTicket(int id) {
        String sql = "DELETE FROM tickets WHERE ticketId=?";
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
