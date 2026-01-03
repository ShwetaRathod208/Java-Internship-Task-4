package Employee_Management_System;

import java.io.*;  // For file reading/writing

import java.util.ArrayList;  // For the employee list

public class EmployeeFileHandler {  // Class for file operations
    private static final String FILE_NAME = "employees.text";  // File name constant

    // Saves employees to file
    public void saveEmployees(ArrayList<Employee> employees) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {  // Opens file for writing
            for (Employee emp : employees) {  // Loops through employees
                writer.write(emp.toFileString());  // Writes serialized string
                writer.newLine();  // Adds newline
            }
            System.out.println("Data saved.");  // Confirms save
        } catch (IOException e) {  // Catches I/O errors
            System.out.println("Error saving: " + e.getMessage());  // Prints error
        }
    }

    // Loads employees from file
    public ArrayList<Employee> loadEmployees() {
        ArrayList<Employee> employees = new ArrayList<>();  // Creates empty list
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {  // Opens file for reading
            String line;
            while ((line = reader.readLine()) != null) {  // Reads each line
                employees.add(Employee.fromFileString(line));  // Deserializes and adds to list
            }
            System.out.println("Data loaded.");  // Confirms load
        } catch (IOException e) {  // Catches I/O errors
            System.out.println("No data file or error loading. Starting fresh.");  // Prints message
        }
        return employees;  // Returns the list
    }
}