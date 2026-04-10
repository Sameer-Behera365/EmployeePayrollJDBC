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

        EmployeePayrollService service = EmployeePayrollService.getInstance();

        // UC2 - Retrieve all employees
        System.out.println("\n=== UC2: All Employees ===");
        List<EmployeePayrollData> employees = service.getEmployeePayrollData();
        employees.forEach(System.out::println);

        // UC3 - Update salary with PreparedStatement
        System.out.println("\n=== UC3: Update Terisa Salary ===");
        service.updateSalaryWithPreparedStatement("Terisa", 3000000);
    }
}