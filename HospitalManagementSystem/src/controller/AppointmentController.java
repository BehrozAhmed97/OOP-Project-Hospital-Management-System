package controller;

import model.Appointment;
import exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AppointmentController {
    public void addAppointment(Appointment appointment) throws DatabaseException {
        String sql = "INSERT INTO appointments (id, patient_id, doctor_id, date_time) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, appointment.getId());
            stmt.setInt(2, appointment.getPatientId());
            stmt.setInt(3, appointment.getDoctorId());
            stmt.setString(4, appointment.getDateTime().toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error adding appointment: " + e.getMessage());
        }
    }

    public List<Appointment> getAllAppointments() throws DatabaseException {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM appointments";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                appointments.add(new Appointment(
                        rs.getInt("id"),
                        rs.getInt("patient_id"),
                        rs.getInt("doctor_id"),
                        LocalDateTime.parse(rs.getString("date_time"), formatter)
                ));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error retrieving appointments: " + e.getMessage());
        }
        return appointments;
    }
}