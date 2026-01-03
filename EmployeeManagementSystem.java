package Employee_Management_System;
import java.time.LocalDate;  // For date handling
import java.util.*;  // For collections like ArrayList and HashMap

public class EmployeeManagementSystem {  // Main class for the system
    private ArrayList<Employee> employees = new ArrayList<>();  // List to store all employees
    private HashMap<Integer, Employee> employeeMap = new HashMap<>();  // Map for quick ID-based lookup
    private int nextId = 1;  // Auto-incrementing ID for new employees
    private EmployeeFileHandler fileHandler = new EmployeeFileHandler();  // Instance for file operations
    private EmployeeReportGenerator reportGenerator = new EmployeeReportGenerator(); // Instance for reports

    // Constructor: Loads data from file on startup
    public EmployeeManagementSystem() {
        loadData(); // load employees first
        
    }

    private void loadData() {
        ArrayList<Employee> loaded = fileHandler.loadEmployees();

        employees.clear();
        employeeMap.clear();
        nextId = 1;

        for (Employee emp : loaded) {
            employees.add(emp);
            employeeMap.put(emp.getId(), emp);
            if (emp.getId() >= nextId) {
                nextId = emp.getId() + 1;
            }
        }
    }

    // Saves employees to file
    private void saveData() {
        fileHandler.saveEmployees(employees);  // Delegates to file handler
    }

    // CRUD: Adds a new employee
    public void addEmployee(String name, String department, String position, double salary, LocalDate joinDate) {
        Employee emp = new Employee(nextId++, name, department, position, salary, joinDate);  // Creates new Employee
        employees.add(emp);  // Adds to list
        employeeMap.put(emp.getId(), emp);  // Adds to map
        saveData();
    }

     
 // CRUD: Displays all employees
    public void viewAllEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees.");
            return;
        }

        System.out.println("=== ALL EMPLOYEES ===");
        System.out.println("ID | Name       | Department   | Position   | Salary     | Join Date");
        System.out.println("---------------------------------------------------------------");

        for (Employee e : employees) {
            System.out.println(
                "E" + e.getId() + " | " +
                e.getName() + " | " +
                e.getDepartment() + " | " +
                e.getPosition() + " | " +
                "₹" + e.getSalary() + " | " +
                e.getJoinDate()
            );
        }
    }


    
 // CRUD: Displays employee by ID
    public void viewEmployeeById(int id) {
        Employee e = employeeMap.get(id);  // Lookup employee in map

        if (e == null) {
            System.out.println("Employee with ID E" + id + " not found.");
            return;
        }

        // Print table header
        System.out.println("=== EMPLOYEE DETAILS ===");
        System.out.println("ID | Name       | Department   | Position   | Salary     | Join Date");
        System.out.println("---------------------------------------------------------------");

        // Print the employee details
        System.out.println(
            "E" + e.getId() + " | " +
            e.getName() + " | " +
            e.getDepartment() + " | " +
            e.getPosition() + " | " +
            "₹" + e.getSalary() + " | " +
            e.getJoinDate()
        );
    }


    // CRUD: Updates an employee
    public void updateEmployee(int id, String name, String department, String position, double salary, LocalDate joinDate) {
        Employee emp = employeeMap.get(id);  // Finds employee
        if (emp == null) {  // If not found
            System.out.println("Not found.");  // Prints message
            return;  // Exits
        }
        if (name != null && !name.isEmpty()) emp.setName(name);  // Updates name if provided
        if (department != null && !department.isEmpty()) emp.setDepartment(department);  // Updates department
        if (position != null && !position.isEmpty()) emp.setPosition(position);  // Updates position
        if (salary >= 0) emp.setSalary(salary);  // Updates salary if valid
        if (joinDate != null) emp.setJoinDate(joinDate);  // Updates date if provided
        saveData();
    }

    // CRUD: Deletes an employee
    public void deleteEmployee(int id) {
        Employee emp = employeeMap.remove(id);  // Removes from map
        if (emp != null) {  // If found
            employees.remove(emp);// Removes from list
            saveData();
             
        } else {
            System.out.println("Not found.");  // If not found
        }
    }

    public void searchByName(String name) {
        ArrayList<Employee> results = new ArrayList<>();

        for (Employee e : employees) {
            if (e.getName().toLowerCase().contains(name.toLowerCase())) {
                results.add(e);
            }
        }

        if (results.isEmpty()) {
            System.out.println("No employees found with name: " + name);
            return;
        }

        System.out.println("=== SEARCH RESULTS BY NAME ===");
        System.out.println("ID | Name       | Department   | Position   | Salary     | Join Date");
        System.out.println("---------------------------------------------------------------");

        for (Employee e : results) {
            System.out.println(
                "E" + e.getId() + " | " +
                e.getName() + " | " +
                e.getDepartment() + " | " +
                e.getPosition() + " | " +
                "₹" + e.getSalary() + " | " +
                e.getJoinDate()
            );
        }
    }


    public void searchByDepartment(String department) {
        ArrayList<Employee> results = new ArrayList<>();

        for (Employee e : employees) {
            if (e.getDepartment().toLowerCase().contains(department.toLowerCase())) {
                results.add(e);
            }
        }

        if (results.isEmpty()) {
            System.out.println("No employees found in department: " + department);
            return;
        }

        System.out.println("=== SEARCH RESULTS BY DEPARTMENT ===");
        System.out.println("ID | Name       | Department   | Position   | Salary     | Join Date");
        System.out.println("---------------------------------------------------------------");

        for (Employee e : results) {
            System.out.println(
                "E" + e.getId() + " | " +
                e.getName() + " | " +
                e.getDepartment() + " | " +
                e.getPosition() + " | " +
                "₹" + e.getSalary() + " | " +
                e.getJoinDate()
            );
        }
    }

    // Generates salary report
    public void generateSalaryReport() {
        reportGenerator.generateSalaryReport(employees);  // Delegates to report generator
    }

    // Generates department report
    public void generateDepartmentReport() {
        reportGenerator.generateDepartmentReport(employees);  // Delegates to report generator
    }

    // Main method: Runs the menu-driven interface
    public static void main(String[] args) {
        EmployeeManagementSystem system = new EmployeeManagementSystem();  // Creates system instance
        Scanner sc = new Scanner(System.in);  // For user input
        while (true) {  // Infinite loop for menu
            System.out.println("\n1. Add \n2. View All \n3. View by ID \n4. Update \n5. Delete \n6. Search Name \n7. Search Dept \n8. Salary Report \n9. Dept Report \n10. Save & Exit");
            System.out.print("Choice: ");
            int choice = Integer.parseInt(sc.nextLine());  // Reads user choice
            try {
                switch (choice) {  // Handles menu options
                    case 1:  // Add employee
                        System.out.print("Name: ");
                        String name = sc.nextLine();
                        System.out.print("Department: ");
                        String dept = sc.nextLine();
                        System.out.print("Position: ");
                        String pos = sc.nextLine();
                        System.out.print("Salary: ");
                        double sal = Double.parseDouble(sc.nextLine());
                        System.out.print("Join Date (YYYY-MM-DD): ");
                        LocalDate date = LocalDate.parse(sc.nextLine());
                        system.addEmployee(name, dept, pos, sal, date);// Calls add method
                        System.out.println("Employee Added Successfully!!!!");
                        break;
                    case 2: system.viewAllEmployees(); break;  // View all
                    case 3:  // View by ID
                        System.out.print("ID: ");
                        int id = Integer.parseInt(sc.nextLine());
                        system.viewEmployeeById(id);
                        break;
                    case 4:  // Update
                        System.out.print("ID: ");
                        id = Integer.parseInt(sc.nextLine());
                        System.out.print("New Name (blank to skip): ");
                        name = sc.nextLine();
                        System.out.print("New Department (blank to skip): ");
                        dept = sc.nextLine();
                        System.out.print("New Position (blank to skip): ");
                        pos = sc.nextLine();
                        System.out.print("New Salary (-1 to skip): ");
                        sal = Double.parseDouble(sc.nextLine());
                        System.out.print("New Join Date (blank to skip): ");
                        String dateStr = sc.nextLine();
                        LocalDate newDate = dateStr.isEmpty() ? null : LocalDate.parse(dateStr);
                        system.updateEmployee(id, name, dept, pos, sal, newDate);
                        System.out.println("Employee Updated Successfully!!!!");
                        break;
                    case 5:  // Delete
                        System.out.print("ID: ");
                        id = Integer.parseInt(sc.nextLine());
                        system.deleteEmployee(id);
                        System.out.println("Employee Deleted Successfully!!!!");
                        break;
                    case 6:  // Search by name
                        System.out.print("Name: ");
                        name = sc.nextLine();
                        system.searchByName(name);
                        break;
                    case 7:  // Search by dept
                        System.out.print("Department: ");
                        dept = sc.nextLine();
                        system.searchByDepartment(dept);
                        break;
                    case 8: system.generateSalaryReport(); break;  // Salary report
                    case 9: system.generateDepartmentReport(); break;  // Dept report
                    case 10: system.saveData(); return;  // Save and exit
                    default: System.out.println("Invalid.");  // Invalid choice
                }
            } catch (Exception e) {  // Catches any errors
                System.out.println("Error: " + e.getMessage());  // Prints error
            }
            
        }
    }
}