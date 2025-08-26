package model;

public class Doctor extends Person {
    private String specialization;

    public Doctor(int id, String name, String contact, String address, String specialization) {
        super(id, name, contact, address);
        this.specialization = specialization;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public String getDetails() {
        return "Doctor ID: " + getId() + ", Name: " + getName() + ", Contact: " + getContact() +
                ", Address: " + getAddress() + ", Specialization: " + specialization;
    }
}