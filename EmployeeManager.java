//File Name EmployeeManager.java

import java.io.*;
import java.util.*;

public class EmployeeManager {

    // Read data from employees.txt
    static String readFile() throws IOException {

        try (BufferedReader reader = new BufferedReader(
                new FileReader("employees.txt"))) {

            return reader.readLine();
        }
    }

    // Write data to employees.txt
    static void writeFile(String data) throws IOException {

        try (BufferedWriter writer = new BufferedWriter(
               new FileReader(Constants.FILE_NAME))){

            writer.write(data);
        }
    }

    public static void main(String[] args) {

        // Check whether an argument is provided
        if (args.length == 0) {
            System.out.println("Please provide an argument.");
            return;
        }

        // List all employees
        if (args[0].equals("l")) {

            System.out.println("Loading data ...");

            try {
                String line = readFile();

                if (line != null && !line.isEmpty()) {
                    String[] employees = line.split(",");

                    for (String employee : employees) {
                        System.out.println(employee.trim());
                    }
                }

            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            }

            System.out.println("Data Loaded.");
        }

        // Select a random employee
        else if (args[0].equals("s")) {

            System.out.println("Loading data ...");

            try {
                String line = readFile();

                if (line != null && !line.isEmpty()) {
                    String[] employees = line.split(",");

                    Random random = new Random();
                    int randomIndex = random.nextInt(employees.length);

                    System.out.println(employees[randomIndex].trim());
                }

            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            }

            System.out.println("Data Loaded.");
        }

        // Add a new employee
        else if (args[0].startsWith("+")) {

            System.out.println("Loading data ...");

            try {
                String line = readFile();
                String employeeName = args[0].substring(1).trim();

                if (!employeeName.isEmpty()) {
                    String data = (line == null || line.isEmpty())
                            ? employeeName
                            : line + ", " + employeeName;

                    writeFile(data);
                    System.out.println("Employee Added!");
                }

            } catch (IOException e) {
                System.out.println("Error writing file: " + e.getMessage());
            }

            System.out.println("Data Loaded.");
        }

        // Search for an employee
        else if (args[0].startsWith("?")) {

            System.out.println("Loading data ...");

            try {
                String line = readFile();

                if (line != null && !line.isEmpty()) {
                    String[] employees = line.split(",");
                    String searchName = args[0].substring(1).trim();

                    boolean found = false;

                    for (String employee : employees) {

                        if (employee.trim().equalsIgnoreCase(searchName)) {
                            System.out.println("Employee found!");
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println("Employee not found!");
                    }
                }

            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            }

            System.out.println("Data Loaded.");
        }

        // Count employees
        else if (args[0].equals("c")) {

            System.out.println("Loading data ...");

            try {
                String line = readFile();

                if (line != null && !line.trim().isEmpty()) {
                    String[] employees = line.split(",");

                    System.out.println(
                            employees.length + " employee(s) found."
                    );
                } else {
                    System.out.println("0 employee found.");
                }

            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            }

            System.out.println("Data Loaded.");
        }

        // Update an employee
        else if (args[0].startsWith("u")) {

            System.out.println("Loading data ...");

            try {
                String line = readFile();

                if (line != null && !line.isEmpty()) {
                    String[] employees = line.split(",");
                    String employeeName = args[0].substring(1).trim();

                    boolean found = false;

                    for (int i = 0; i < employees.length; i++) {

                        if (employees[i].trim().equalsIgnoreCase(employeeName)) {
                            employees[i] = "Updated";
                            found = true;
                            break;
                        }
                    }

                    if (found) {
                        writeFile(String.join(",", employees));
                        System.out.println("Employee Updated!");
                    } else {
                        System.out.println("Employee not found!");
                    }
                }

            } catch (IOException e) {
                System.out.println("Error updating file: " + e.getMessage());
            }

            System.out.println("Data Updated.");
        }

        // Delete an employee
        else if (args[0].startsWith("d")) {

            System.out.println("Loading data ...");

            try {
                String line = readFile();

                if (line != null && !line.isEmpty()) {
                    String[] employees = line.split(",");
                    String employeeName = args[0].substring(1).trim();

                    List<String> employeeList =
                            new ArrayList<>(Arrays.asList(employees));

                    boolean removed = employeeList.removeIf(
                            employee -> employee.trim().equalsIgnoreCase(employeeName)
                    );

                    if (removed) {
                        writeFile(String.join(",", employeeList));
                        System.out.println("Employee Deleted!");
                    } else {
                        System.out.println("Employee not found!");
                    }
                }

            } catch (IOException e) {
                System.out.println("Error deleting employee: " + e.getMessage());
            }

            System.out.println("Data Deleted.");
        }

        // Handle invalid commands
        else {
            System.out.println("Invalid argument!");
        }
    }
}
