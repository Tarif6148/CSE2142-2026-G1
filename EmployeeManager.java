//File Name EmployeeManager.java

import java.io.*;
import java.util.*;

public class EmployeeManager {

    // Read data from employees.txt
    static String readFile() throws IOException {

        try (BufferedReader reader = new BufferedReader(
                new FileReader(Constants.FILE_NAME))) {

            return reader.readLine();
        }
    }

    // Write data to employees.txt
    static void writeFile(String data) throws IOException {

        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(Constants.FILE_NAME))) {

            writer.write(data);
        }
    }

    public static void main(String[] args) {

        // Check arguments
        if (args.length == 0) {
            System.out.println("Please provide an argument.");
            return;
        }

        // List all employees
        if (args[0].equals("l")) {

            System.out.println("Loading data ...");

            try {
                String[] employees = readFile().split(",");

                for (String employee : employees) {
                    System.out.println(employee.trim());
                }

            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }

            System.out.println("Data Loaded.");
        }

        // Select a random employee
        else if (args[0].equals("s")) {

            System.out.println("Loading data ...");

            try {
                String[] employees = readFile().split(",");

                Random random = new Random();
                int randomIndex = random.nextInt(employees.length);

                System.out.println(employees[randomIndex].trim());

            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }

            System.out.println("Data Loaded.");
        }

        // Add a new employee
        else if (args[0].startsWith("+")) {

            System.out.println("Loading data ...");
            else {

    System.out.println("Invalid argument!");
    System.out.println("Please enter a valid command.");

}

            try {
                String employeeName = args[0].substring(1).trim();

                if (!employeeName.isEmpty()) {

                    String line = readFile();

                    String data = (line == null || line.isEmpty())
                            ? employeeName
                            : line + ", " + employeeName;

                    writeFile(data);

                    System.out.println("Employee Added!");
                }

            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }

            System.out.println("Data Loaded.");
        }

        // Search for an employee
        else if (args[0].startsWith("?")) {

            System.out.println("Loading data ...");

            try {
                String searchName = args[0].substring(1).trim();

                String[] employees = readFile().split(",");

                if (Arrays.stream(employees).anyMatch(
                        employee -> employee.trim()
                                .equalsIgnoreCase(searchName))) {

                    System.out.println("Employee found!");

                } else {
                    System.out.println("Employee not found!");
                }

            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }

            System.out.println("Data Loaded.");
        }

        // Count words
        else if (args[0].equals("c")) {

            System.out.println("Loading data ...");

            try {
                String line = readFile();

                int wordCount = (line == null || line.trim().isEmpty())
                        ? 0
                        : line.trim().split("\\s+").length;

                System.out.println(
                        wordCount + " word(s) found."
                );

            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }

            System.out.println("Data Loaded.");
        }

        // Update an employee
        else if (args[0].startsWith("u")) {

            System.out.println("Loading data ...");

            try {
                String[] employees = readFile().split(",");

                String employeeName = args[0].substring(1).trim();

                boolean employeeFound = false;

                for (int i = 0; i < employees.length; i++) {

                    if (employees[i].trim()
                            .equalsIgnoreCase(employeeName)) {

                        employees[i] = "Updated";
                        employeeFound = true;
                        break;
                    }
                }

                if (employeeFound) {

                    writeFile(String.join(",", employees));

                    System.out.println("Employee Updated!");

                } else {
                    System.out.println("Employee not found!");
                }

            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }

            System.out.println("Data Updated.");
        }

        // Delete an employee
        else if (args[0].startsWith("d")) {

            System.out.println("Loading data ...");

            try {
                String employeeName = args[0].substring(1).trim();

                List<String> employeeList = new ArrayList<>(
                        Arrays.asList(readFile().split(","))
                );

                boolean employeeRemoved = employeeList.removeIf(
                        employee -> employee.trim()
                                .equalsIgnoreCase(employeeName)
                );

                if (employeeRemoved) {

                    writeFile(String.join(",", employeeList));

                    System.out.println("Employee Deleted!");

                } else {
                    System.out.println("Employee not found!");
                }

            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }

            System.out.println("Data Deleted.");
        }

        else {
            System.out.println("Invalid argument!");
        }
    }
}
