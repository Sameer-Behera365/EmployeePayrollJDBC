package com.bridgelabz;

public class EmployeePayrollMain {
    public static void main(String[] args) {
        // UC1 - Test DB Connection
        try {
            java.sql.Connection con = DBConnection.getConnection();
            if (con != null) {
                System.out.println("Connection is successful!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}