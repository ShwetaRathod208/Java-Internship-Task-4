
package Employee_Management_System;
import java.time.LocalDate;  // Imports LocalDate for handling dates (e.g., join date)

public class Employee {  // Defines the Employee class
    private int id;  // Unique identifier for the employee
    private String name;  // Employee's name
    private String department;  // Department they work in
    private String position;  // Job position
    private double salary;  // Salary amount
    private LocalDate joinDate;  // Date they joined (using LocalDate for date handling)

    // Constructor to create an Employee object with all attributes
    public Employee(int id, String name, String department, String position, double salary, LocalDate joinDate) {
        this.id = id;  // Assigns the ID
        this.name = name;  // Assigns the name
        this.department = department;  // Assigns the department
        this.position = position;  // Assigns the position
        this.salary = salary;  // Assigns the salary
        this.joinDate = joinDate;  // Assigns the join date
    }

    // Getter methods to retrieve attribute values
    public int getId() { return id; }  // Returns the ID
    public String getName() { return name; }  // Returns the name
    public String getDepartment() { return department; }  // Returns the department
    public String getPosition() { return position; }  // Returns the position
    public double getSalary() { return salary; }  // Returns the salary
    public LocalDate getJoinDate() { return joinDate; }  // Returns the join date

    // Setter methods to update attribute values (used in updates)
    public void setName(String name) { this.name = name; }  // Updates the name
    public void setDepartment(String department) { this.department = department; }  // Updates the department
    public void setPosition(String position) { this.position = position; }  // Updates the position
    public void setSalary(double salary) { this.salary = salary; }  // Updates the salary
    public void setJoinDate(LocalDate joinDate) { this.joinDate = joinDate; }  // Updates the join date

    // toString method for displaying employee details in a readable format
    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Dept: " + department + ", Pos: " + position + ", Salary: " + salary + ", Joined: " + joinDate;
        // Returns a formatted string with all details
    }

    // Method to serialize (convert) the employee to a comma-separated string for file saving
    public String toFileString() {
        return id + "," + name + "," + department + "," + position + "," + salary + "," + joinDate;
        // Combines attributes into a single string, separated by commas
    }

    // Static method to deserialize (convert back) a comma-separated string from file into an Employee object
    public static Employee fromFileString(String line) {
        String[] parts = line.split(",");  // Splits the line by commas into an array
        return new Employee(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3], Double.parseDouble(parts[4]), LocalDate.parse(parts[5]));
        // Parses each part and creates a new Employee object
    }
}