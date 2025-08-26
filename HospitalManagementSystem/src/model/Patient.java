package model;

public class Patient extends Person {
    private String medicalHistory;

    public Patient(int id, String name, String contact, String address, String medicalHistory) {
        super(id, name, contact, address);
        this.medicalHistory = medicalHistory;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    @Override
    public String getDetails() {
        return "Patient ID: " + getId() + ", Name: " + getName() + ", Contact: " + getContact() +
                ", Address: " + getAddress() + ", Medical History: " + medicalHistory;
    }
}