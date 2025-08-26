package controller;

import model.Bill;
import exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BillingController {
    public void addBill(Bill bill) throws DatabaseException {
        String sql = "INSERT INTO bills (id, patient_id, amount, description) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, bill.getId());
            stmt.setInt(2, bill.getPatientId());
            stmt.setDouble(3, bill.getAmount());
            stmt.setString(4, bill.getDescription());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error adding bill: " + e.getMessage());
        }
    }

    public List<Bill> getBillsByPatientId(int patientId) throws DatabaseException {
        List<Bill> bills = new ArrayList<>();
        String sql = "SELECT * FROM bills WHERE patient_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, patientId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                bills.add(new Bill(
                        rs.getInt("id"),
                        rs.getInt("patient_id"),
                        rs.getDouble("amount"),
                        rs.getString("description")
                ));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error retrieving bills: " + e.getMessage());
        }
        return bills;
    }
}