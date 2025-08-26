package view;

import model.Appointment;
import model.Bill;
import model.Doctor;
import model.Patient;

import java.util.List;

public class ConsoleView {
    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayMainMenu() {
        System.out.println("\n=== Hospital Management System ===");
        System.out.println("1. Login");
        System.out.println("2. Add Patient");
        System.out.println("3. View Patients");
        System.out.println("4. Search Patient");
        System.out.println("5. Add Doctor");
        System.out.println("6. View Doctors");
        System.out.println("7. Schedule Appointment");
        System.out.println("8. View Appointments");
        System.out.println("9. Generate Bill");
        System.out.println("10. View Bills");
        System.out.println("11. Exit");
        System.out.print("Choose an option: ");
    }

    public void displayPatients(List<Patient> patients) {
        System.out.println("\n=== Patient List ===");
        for (Patient patient : patients) {
            System.out.println(patient.getDetails());
        }
    }

    public void displayDoctors(List<Doctor> doctors) {
        System.out.println("\n=== Doctor List ===");
        for (Doctor doctor : doctors) {
            System.out.println(doctor.getDetails());
        }
    }

    public void displayAppointments(List<Appointment> appointments) {
        System.out.println("\n=== Appointment List ===");
        for (Appointment appointment : appointments) {
            System.out.println(appointment.getDetails());
        }
    }

    public void displayBills(List<Bill> bills) {
        System.out.println("\n=== Bill List ===");
        for (Bill bill : bills) {
            System.out.println(bill.getDetails());
        }
    }
}