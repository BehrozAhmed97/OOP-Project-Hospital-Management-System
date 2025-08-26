# 🏥 Hospital Management System (Java + JDBC)

A **console-based Hospital Management System** built in Java using **Object-Oriented Programming (OOP)** principles and **JDBC** for database connectivity.  
This project manages hospital operations such as **patients, doctors, appointments, billing, and user authentication** with persistent storage in a MySQL database.

---

## 📌 Features
- 👨‍⚕️ **Patient Management** – Add, view, and search patients by ID  
- 🩺 **Doctor Management** – Add and view doctors  
- 📅 **Appointment Management** – Schedule and view appointments with date-time validation  
- 💰 **Billing System** – Generate and view patient bills  
- 🔐 **User Authentication** – Secure login with username/password  
- ⚡ **Error Handling** – Custom exceptions for invalid input and database errors  
- 💾 **Database Persistence** – MySQL database with JDBC integration  

---

## 🛠️ Tech Stack
- **Language:** Java (JDK 17 recommended, works with JDK 24 too)  
- **Database:** MySQL 8.0+  
- **Libraries:** MySQL JDBC Driver (v8.0.33+)  
- **IDE:** IntelliJ IDEA  

---

## 📂 Project Structure
The project is organized into multiple packages for modularity:

- `model` → Entity classes (`Person`, `Patient`, `Doctor`, `Appointment`, `User`, `Bill`)  
- `controller` → Business logic & database operations (`PatientController`, `DoctorController`, etc.)  
- `view` → Console UI (`ConsoleView`, `MenuHandler`)  
- `exceptions` → Custom exceptions (`InvalidInputException`, `DatabaseException`)  
- `Hospital` → Entry point (`Main.java`)  

---

## 🗄️ Database Schema
The database `hospital_db` includes the following tables:

- **patients**: (id, name, contact, address, medical_history)  
- **doctors**: (id, name, contact, address, specialization)  
- **appointments**: (id, patient_id, doctor_id, date_time)  
- **users**: (id, username, password, role)  
- **bills**: (id, patient_id, amount, description)  

👉 Use the provided `database_setup.sql` script to initialize the schema.  
It also creates a default admin user:  
username: admin
password: admin123

