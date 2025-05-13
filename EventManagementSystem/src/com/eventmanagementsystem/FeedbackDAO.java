package com.eventmanagementsystem;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FeedbackDAO {

    public boolean addFeedback(Feedback feedback) {
        String sql = "INSERT INTO feedback (feedbackId, userId, eventId, rating, comment) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, feedback.getFeedbackId());
            stmt.setInt(2, feedback.getUserId());
            stmt.setInt(3, feedback.getEventId());
            stmt.setInt(4, feedback.getRating());
            stmt.setString(5, feedback.getComment());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Feedback> getAllFeedbacks() {
        List<Feedback> feedbacks = new ArrayList<>();
        String sql = "SELECT * FROM feedback";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Feedback feedback = new Feedback(
                    rs.getInt("feedbackId"),
                    rs.getInt("userId"),
                    rs.getInt("eventId"),
                    rs.getInt("rating"),
                    rs.getString("comment")
                );
                feedbacks.add(feedback);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return feedbacks;
    }
}
