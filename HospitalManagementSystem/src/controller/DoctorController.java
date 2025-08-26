package controller;

import model.Doctor;
import exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorController {
    public void addDoctor(Doctor doctor) throws DatabaseException {
        String sql = "INSERT INTO doctors (id, name, contact, address, specialization) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, doctor.getId());
            stmt.setString(2, doctor.getName());
            stmt.setString(3, doctor.getContact());
            stmt.setString(4, doctor.getAddress());
            stmt.setString(5, doctor.getSpecialization());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error adding doctor: " + e.getMessage());
        }
    }

    public List<Doctor> getAllDoctors() throws DatabaseException {
        List<Doctor> doctors = new ArrayList<>();
        String sql = "SELECT * FROM doctors";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                doctors.add(new Doctor(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("contact"),
                        rs.getString("address"),
                        rs.getString("specialization")
                ));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error retrieving doctors: " + e.getMessage());
        }
        return doctors;
    }
}