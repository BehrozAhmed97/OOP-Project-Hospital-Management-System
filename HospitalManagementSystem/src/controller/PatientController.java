package controller;

import model.Patient;
import exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientController {
    public void addPatient(Patient patient) throws DatabaseException {
        String sql = "INSERT INTO patients (id, name, contact, address, medical_history) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, patient.getId());
            stmt.setString(2, patient.getName());
            stmt.setString(3, patient.getContact());
            stmt.setString(4, patient.getAddress());
            stmt.setString(5, patient.getMedicalHistory());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error adding patient: " + e.getMessage());
        }
    }

    public List<Patient> getAllPatients() throws DatabaseException {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM patients";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                patients.add(new Patient(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("contact"),
                        rs.getString("address"),
                        rs.getString("medical_history")
                ));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error retrieving patients: " + e.getMessage());
        }
        return patients;
    }

    public Patient searchPatientById(int id) throws DatabaseException {
        String sql = "SELECT * FROM patients WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Patient(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("contact"),
                        rs.getString("address"),
                        rs.getString("medical_history")
                );
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error searching patient: " + e.getMessage());
        }
        return null;
    }
}