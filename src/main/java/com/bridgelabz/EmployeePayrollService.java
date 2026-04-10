package com.bridgelabz;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeePayrollService {

    private static EmployeePayrollService instance;

    private EmployeePayrollService() {}

    public static EmployeePayrollService getInstance() {
        if (instance == null)
            instance = new EmployeePayrollService();
        return instance;
    }

    // UC2 - Get all employees
    public List<EmployeePayrollData> getEmployeePayrollData() {
        List<EmployeePayrollData> list = new ArrayList<>();
        String sql = "SELECT * FROM employee_payroll";
        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new EmployeePayrollData(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("salary"),
                        rs.getString("gender"),
                        rs.getDate("start").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // UC3 - Update salary using Statement
    public void updateSalaryWithStatement(String name, double salary) {
        String sql = "UPDATE employee_payroll SET salary = " + salary +
                " WHERE name = '" + name + "'";
        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement()) {
            int rows = stmt.executeUpdate(sql);
            System.out.println("Rows updated: " + rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // UC4 - Update salary using PreparedStatement
    public void updateSalaryWithPreparedStatement(String name, double salary) {
        String sql = "UPDATE employee_payroll SET salary = ? WHERE name = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDouble(1, salary);
            ps.setString(2, name);
            int rows = ps.executeUpdate();
            System.out.println("Rows updated (PreparedStatement): " + rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // UC5 - Get employees by date range
    public List<EmployeePayrollData> getEmployeesByDateRange(
            LocalDate start, LocalDate end) {
        List<EmployeePayrollData> list = new ArrayList<>();
        String sql = "SELECT * FROM employee_payroll WHERE start BETWEEN ? AND ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(start));
            ps.setDate(2, Date.valueOf(end));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new EmployeePayrollData(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("salary"),
                        rs.getString("gender"),
                        rs.getDate("start").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // UC6 - Add new employee
    public void addEmployee(String name, double salary,
                            String gender, LocalDate startDate) {
        String sql = "INSERT INTO employee_payroll (name, salary, gender, start)" +
                " VALUES (?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setDouble(2, salary);
            ps.setString(3, gender);
            ps.setDate(4, Date.valueOf(startDate));
            ps.executeUpdate();
            System.out.println("Employee added: " + name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // UC7 - Salary stats by gender
    public Map<String, Double> getSalaryStatsByGender(String function) {
        Map<String, Double> result = new HashMap<>();
        String sql = "SELECT gender, " + function +
                "(salary) FROM employee_payroll GROUP BY gender";
        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                result.put(rs.getString(1), rs.getDouble(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}