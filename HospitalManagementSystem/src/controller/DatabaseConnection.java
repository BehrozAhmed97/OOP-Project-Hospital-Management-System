package controller;

import exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/hospital_db";
    private static final String USER = "root";
    private static final String PASSWORD = "Szabist@28"; // Update with your MySQL password

    public static Connection getConnection() throws DatabaseException {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new DatabaseException("Failed to connect to database: " + e.getMessage());
        }
    }
}