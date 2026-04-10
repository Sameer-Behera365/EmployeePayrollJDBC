package com.bridgelabz;

import java.util.List;

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

        // UC2 - Retrieve all employees
        EmployeePayrollService service = EmployeePayrollService.getInstance();
        System.out.println("\n=== UC2: All Employees ===");
        List<EmployeePayrollData> employees = service.getEmployeePayrollData();
        employees.forEach(System.out::println);
    }
}