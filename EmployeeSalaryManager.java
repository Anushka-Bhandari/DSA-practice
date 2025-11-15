import java.util.*;

class Employee {
    private int id;
    private String name;
    private double basicSalary;
    private double tax;
    private double deductions;
    private double netSalary;

    public Employee(int id, String name, double basicSalary) {
        this.id = id;
        this.name = name;
        this.basicSalary = basicSalary;
        calculateSalary();
    }

    public void calculateSalary() {
        tax = 0.1 * basicSalary;
        deductions = 0.05 * basicSalary;
        netSalary = basicSalary - (tax + deductions);
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getBasicSalary() { return basicSalary; }
    public double getNetSalary() { return netSalary; }

    public void updateDetails(String name, double basicSalary) {
        this.name = name;
        this.basicSalary = basicSalary;
        calculateSalary();
    }

    public void generatePayslip() {
        System.out.println("\n--------- PAYSLIP ---------");
        System.out.println("Employee ID   : " + id);
        System.out.println("Employee Name : " + name);
        System.out.println("Basic Salary  : " + basicSalary);
        System.out.println("Tax (10%)     : " + tax);
        System.out.println("Deductions(5%): " + deductions);
        System.out.println("Net Salary    : " + netSalary);
        System.out.println("---------------------------\n");
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Basic Salary: " + basicSalary + ", Net Salary: " + netSalary;
    }
}

public class EmployeeSalaryManager {
    private static Scanner sc = new Scanner(System.in);
    private static ArrayList<Employee> employees = new ArrayList<>();

    public static void addEmployee() {
        System.out.print("Enter Employee ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Employee Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Basic Salary: ");
        double salary = sc.nextDouble();
        employees.add(new Employee(id, name, salary));
        System.out.println("Employee added successfully!\n");
    }

    public static Employee searchEmployee(int id) {
        for (Employee e : employees) {
            if (e.getId() == id)
                return e;
        }
        return null;
    }

    public static void updateEmployee() {
        System.out.print("Enter Employee ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();
        Employee e = searchEmployee(id);
        if (e != null) {
            System.out.print("Enter new name: ");
            String name = sc.nextLine();
            System.out.print("Enter new basic salary: ");
            double salary = sc.nextDouble();
            e.updateDetails(name, salary);
            System.out.println("Employee details updated successfully!\n");
        } else {
            System.out.println("Employee not found!\n");
        }
    }

    public static void generatePayslip() {
        System.out.print("Enter Employee ID: ");
        int id = sc.nextInt();
        Employee e = searchEmployee(id);
        if (e != null)
            e.generatePayslip();
        else
            System.out.println("Employee not found!\n");
    }

    public static void displayAll() {
        if (employees.isEmpty()) {
            System.out.println("No employee records available.\n");
            return;
        }
        System.out.println("\n------ Employee Records ------");
        for (Employee e : employees)
            System.out.println(e);
        System.out.println("------------------------------\n");
    }

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("==== Employee Salary Management System ====");
            System.out.println("1. Add Employee");
            System.out.println("2. Update Employee Details");
            System.out.println("3. Generate Payslip");
            System.out.println("4. View All Employees");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> addEmployee();
                case 2 -> updateEmployee();
                case 3 -> generatePayslip();
                case 4 -> displayAll();
                case 5 -> System.out.println("Exiting program. Thank you!");
                default -> System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 5);
    }
}