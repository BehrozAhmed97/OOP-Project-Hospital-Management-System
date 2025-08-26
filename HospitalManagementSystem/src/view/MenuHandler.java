package view;

import controller.*;
import model.*;
import exceptions.DatabaseException;
import exceptions.InvalidInputException;

import java.time.LocalDateTime;
import java.util.Scanner;

public class MenuHandler {
    private ConsoleView view;
    private PatientController patientController;
    private DoctorController doctorController;
    private AppointmentController appointmentController;
    private BillingController billingController;
    private UserController userController;
    private Scanner scanner;
    private boolean isAuthenticated;

    public MenuHandler() {
        this.view = new ConsoleView();
        this.patientController = new PatientController();
        this.doctorController = new DoctorController();
        this.appointmentController = new AppointmentController();
        this.billingController = new BillingController();
        this.userController = new UserController();
        this.scanner = new Scanner(System.in);
        this.isAuthenticated = false;
    }

    public void start() {
        while (true) {
            view.displayMainMenu();
            String choice = scanner.nextLine();
            try {
                switch (choice) {
                    case "1":
                        handleLogin();
                        break;
                    case "2":
                        if (isAuthenticated) handleAddPatient();
                        else view.displayMessage("Please login first.");
                        break;
                    case "3":
                        if (isAuthenticated) handleViewPatients();
                        else view.displayMessage("Please login first.");
                        break;
                    case "4":
                        if (isAuthenticated) handleSearchPatient();
                        else view.displayMessage("Please login first.");
                        break;
                    case "5":
                        if (isAuthenticated) handleAddDoctor();
                        else view.displayMessage("Please login first.");
                        break;
                    case "6":
                        if (isAuthenticated) handleViewDoctors();
                        else view.displayMessage("Please login first.");
                        break;
                    case "7":
                        if (isAuthenticated) handleScheduleAppointment();
                        else view.displayMessage("Please login first.");
                        break;
                    case "8":
                        if (isAuthenticated) handleViewAppointments();
                        else view.displayMessage("Please login first.");
                        break;
                    case "9":
                        if (isAuthenticated) handleGenerateBill();
                        else view.displayMessage("Please login first.");
                        break;
                    case "10":
                        if (isAuthenticated) handleViewBills();
                        else view.displayMessage("Please login first.");
                        break;
                    case "11":
                        view.displayMessage("Exiting system...");
                        return;
                    default:
                        throw new InvalidInputException("Invalid option selected.");
                }
            } catch (InvalidInputException | DatabaseException e) {
                view.displayMessage("Error: " + e.getMessage());
            }
        }
    }

    private void handleLogin() throws DatabaseException, InvalidInputException {
        view.displayMessage("Enter username: ");
        String username = scanner.nextLine();
        view.displayMessage("Enter password: ");
        String password = scanner.nextLine();
        if (username.isEmpty() || password.isEmpty()) {
            throw new InvalidInputException("Username and password cannot be empty.");
        }
        isAuthenticated = userController.authenticate(username, password);
        view.displayMessage(isAuthenticated ? "Login successful!" : "Invalid credentials.");
    }

    private void handleAddPatient() throws InvalidInputException, DatabaseException {
        view.displayMessage("Enter Patient ID: ");
        int id = validateIntInput(scanner.nextLine());
        view.displayMessage("Enter Name: ");
        String name = scanner.nextLine();
        view.displayMessage("Enter Contact: ");
        String contact = scanner.nextLine();
        view.displayMessage("Enter Address: ");
        String address = scanner.nextLine();
        view.displayMessage("Enter Medical History: ");
        String medicalHistory = scanner.nextLine();
        Patient patient = new Patient(id, name, contact, address, medicalHistory);
        patientController.addPatient(patient);
        view.displayMessage("Patient added successfully.");
    }

    private void handleViewPatients() throws DatabaseException {
        view.displayPatients(patientController.getAllPatients());
    }

    private void handleSearchPatient() throws DatabaseException, InvalidInputException {
        view.displayMessage("Enter Patient ID: ");
        int id = validateIntInput(scanner.nextLine());
        Patient patient = patientController.searchPatientById(id);
        if (patient != null) {
            view.displayMessage(patient.getDetails());
        } else {
            view.displayMessage("Patient not found.");
        }
    }

    private void handleAddDoctor() throws InvalidInputException, DatabaseException {
        view.displayMessage("Enter Doctor ID: ");
        int id = validateIntInput(scanner.nextLine());
        view.displayMessage("Enter Name: ");
        String name = scanner.nextLine();
        view.displayMessage("Enter Contact: ");
        String contact = scanner.nextLine();
        view.displayMessage("Enter Address: ");
        String address = scanner.nextLine();
        view.displayMessage("Enter Specialization: ");
        String specialization = scanner.nextLine();
        Doctor doctor = new Doctor(id, name, contact, address, specialization);
        doctorController.addDoctor(doctor);
        view.displayMessage("Doctor added successfully.");
    }

    private void handleViewDoctors() throws DatabaseException {
        view.displayDoctors(doctorController.getAllDoctors());
    }

    private void handleScheduleAppointment() throws InvalidInputException, DatabaseException {
        view.displayMessage("Enter Appointment ID: ");
        int id = validateIntInput(scanner.nextLine());
        view.displayMessage("Enter Patient ID: ");
        int patientId = validateIntInput(scanner.nextLine());
        view.displayMessage("Enter Doctor ID: ");
        int doctorId = validateIntInput(scanner.nextLine());
        view.displayMessage("Enter Date and Time (yyyy-MM-dd HH:mm): ");
        String dateTimeStr = scanner.nextLine();
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr.replace(" ", "T"));
        Appointment appointment = new Appointment(id, patientId, doctorId, dateTime);
        appointmentController.addAppointment(appointment);
        view.displayMessage("Appointment scheduled successfully.");
    }

    private void handleViewAppointments() throws DatabaseException {
        view.displayAppointments(appointmentController.getAllAppointments());
    }

    private void handleGenerateBill() throws InvalidInputException, DatabaseException {
        view.displayMessage("Enter Bill ID: ");
        int id = validateIntInput(scanner.nextLine());
        view.displayMessage("Enter Patient ID: ");
        int patientId = validateIntInput(scanner.nextLine());
        view.displayMessage("Enter Amount: ");
        double amount = validateDoubleInput(scanner.nextLine());
        view.displayMessage("Enter Description: ");
        String description = scanner.nextLine();
        Bill bill = new Bill(id, patientId, amount, description);
        billingController.addBill(bill);
        view.displayMessage("Bill generated successfully.");
    }

    private void handleViewBills() throws InvalidInputException, DatabaseException {
        view.displayMessage("Enter Patient ID: ");
        int patientId = validateIntInput(scanner.nextLine());
        view.displayBills(billingController.getBillsByPatientId(patientId));
    }

    private int validateIntInput(String input) throws InvalidInputException {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Invalid numeric input: " + input);
        }
    }

    private double validateDoubleInput(String input) throws InvalidInputException {
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Invalid numeric input: " + input);
        }
    }
}