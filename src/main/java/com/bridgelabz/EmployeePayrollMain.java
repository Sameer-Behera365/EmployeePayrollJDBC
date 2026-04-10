package com.bridgelabz;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class EmployeePayrollMain {
    public static void main(String[] args) {

        EmployeePayrollService service = EmployeePayrollService.getInstance();

        // UC2 - Retrieve all employees
        System.out.println("\n=== UC2: All Employees ===");
        List<EmployeePayrollData> employees = service.getEmployeePayrollData();
        employees.forEach(System.out::println);

        // UC3 - Update salary with Statement
        System.out.println("\n=== UC3: Update Terisa Salary (Statement) ===");
        service.updateSalaryWithStatement("Terisa", 3000000);

        // UC4 - Update salary with PreparedStatement
        System.out.println("\n=== UC4: Update Terisa Salary (PreparedStatement) ===");
        service.updateSalaryWithPreparedStatement("Terisa", 3000000);

        // UC5 - Employees by date range
        System.out.println("\n=== UC5: Employees joined between 2018 and now ===");
        List<EmployeePayrollData> byDate = service.getEmployeesByDateRange(
                LocalDate.of(2018, 1, 1), LocalDate.now());
        byDate.forEach(System.out::println);

        // UC6 - Add new employee
        System.out.println("\n=== UC6: Adding new employee ===");
        service.addEmployee("John", 2000000, "M", LocalDate.of(2021, 6, 15));

        // UC7 - Salary stats by gender
        System.out.println("\n=== UC7: SUM of salary by gender ===");
        Map<String, Double> sumResult = service.getSalaryStatsByGender("SUM");
        sumResult.forEach((gender, sum) ->
                System.out.println("Gender: " + gender + " | SUM: " + sum));

        System.out.println("\n=== UC7: AVG of salary by gender ===");
        Map<String, Double> avgResult = service.getSalaryStatsByGender("AVG");
        avgResult.forEach((gender, avg) ->
                System.out.println("Gender: " + gender + " | AVG: " + avg));
    }
}