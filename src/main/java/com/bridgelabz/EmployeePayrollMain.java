package com.bridgelabz;

import java.time.LocalDate;
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

        // UC4 - Retrieve employees by date range
        System.out.println("\n=== UC4: Employees joined between 2018 and now ===");
        List<EmployeePayrollData> byDate = service.getEmployeesByDateRange(
                LocalDate.of(2018, 1, 1), LocalDate.now());
        byDate.forEach(System.out::println);
    }
}