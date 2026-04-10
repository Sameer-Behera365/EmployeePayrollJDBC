package com.bridgelabz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String JDBC_URL =
            "jdbc:mysql://localhost:3306/payroll_service?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "Sonu@2003"; // put your mysql password here

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded!");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Cannot find driver!", e);
        }
        Connection con = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
        System.out.println("Connection established: " + con);
        return con;
    }
}