package com.eventmanagementsystem;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO {

    public boolean addPayment(Payment payment) {
        String sql = "INSERT INTO payments (paymentId, ticketId, paymentDate, amount, paymentMethod) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, payment.getPaymentId());
            stmt.setInt(2, payment.getTicketId());
            stmt.setDate(3, payment.getPaymentDate());
            stmt.setDouble(4, payment.getAmountPaid());
            stmt.setString(5, payment.getPaymentMethod());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Payment> getAllPayments() {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT * FROM payments";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Payment payment = new Payment(
                    rs.getInt("paymentId"),
                    rs.getInt("ticketId"),
                    rs.getDate("paymentDate"),
                    rs.getDouble("amount"),
                    rs.getString("paymentMethod")
                );
                payments.add(payment);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payments;
    }
}

