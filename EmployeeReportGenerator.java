package Employee_Management_System;


import java.util.ArrayList;
import java.util.HashMap;

public class EmployeeReportGenerator {

    // SALARY REPORT
    public void generateSalaryReport(ArrayList<Employee> employees) {

        if (employees == null || employees.isEmpty()) {
            System.out.println("No employees to generate salary report.");
            return;
        }

        double totalSalary = 0;
        for (Employee e : employees) {
            totalSalary += e.getSalary();
        }

        double avgSalary = totalSalary / employees.size();

        System.out.println("\n=== SALARY REPORT ===");
        System.out.println("Total Employees : " + employees.size());
        System.out.println("Total Salary    : ₹" + totalSalary);
        System.out.println("Average Salary  : ₹" + avgSalary);
    }

    // DEPARTMENT REPORT
    public void generateDepartmentReport(ArrayList<Employee> employees) {

        if (employees == null || employees.isEmpty()) {
            System.out.println("No employees to generate department report.");
            return;
        }

        HashMap<String, ArrayList<Employee>> deptMap = new HashMap<>();

        for (Employee e : employees) {
            deptMap.computeIfAbsent(e.getDepartment(), k -> new ArrayList<>()).add(e);
        }

        System.out.println("\n=== DEPARTMENT REPORT ===");

        for (String dept : deptMap.keySet()) {
            System.out.println("\nDepartment: " + dept);
            System.out.println("--------------------------------------");

            for (Employee e : deptMap.get(dept)) {
                System.out.println(
                        "ID: E" + e.getId() +
                        ", Name: " + e.getName() +
                        ", Position: " + e.getPosition() +
                        ", Salary: ₹" + e.getSalary() +
                        ", Join Date: " + e.getJoinDate()
                );
            }
        }
    }
}
