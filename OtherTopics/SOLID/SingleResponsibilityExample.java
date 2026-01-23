package SOLID;

// SOLID Principle: Single Responsibility Principle (SRP)
// A class should have only one reason to change

// ❌ BAD: Employee class has multiple responsibilities
class EmployeeBad {
    private String name;
    private String position;
    
    // Responsibility 1: Employee data management
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    // Responsibility 2: Database operations
    public void saveToDatabase() {
        System.out.println("Saving " + name + " to database...");
    }
    
    // Responsibility 3: Report generation
    public void generateReport() {
        System.out.println("Generating report for " + name + "...");
    }
    
    // Responsibility 4: Email sending
    public void sendEmail(String message) {
        System.out.println("Sending email to " + name + "...");
    }
}

// ✅ GOOD: Each class has a single responsibility
class Employee {
    private String name;
    private String position;
    
    public Employee(String name, String position) {
        this.name = name;
        this.position = position;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public String getPosition() {
        return position;
    }
}

// Responsibility: Database operations only
class EmployeeRepository {
    public void save(Employee employee) {
        System.out.println("Saving " + employee.getName() + " to database...");
    }
    
    public Employee findById(int id) {
        System.out.println("Finding employee with ID: " + id);
        return new Employee("John Doe", "Developer");
    }
}

// Responsibility: Report generation only
class EmployeeReportGenerator {
    public void generateReport(Employee employee) {
        System.out.println("Generating report for " + employee.getName() + "...");
        System.out.println("Position: " + employee.getPosition());
    }
}

// Responsibility: Email operations only
class EmailService {
    public void sendEmail(Employee employee, String subject, String message) {
        System.out.println("Sending email to " + employee.getName() + "...");
        System.out.println("Subject: " + subject);
        System.out.println("Message: " + message);
    }
}

// Demo
public class SingleResponsibilityExample {
    public static void main(String[] args) {
        System.out.println("=== Single Responsibility Principle (SRP) ===\n");
        
        System.out.println("❌ BAD: One class with multiple responsibilities");
        EmployeeBad badEmployee = new EmployeeBad();
        badEmployee.setName("John Doe");
        badEmployee.saveToDatabase();
        badEmployee.generateReport();
        badEmployee.sendEmail("Hello");
        
        System.out.println("\n✅ GOOD: Separated responsibilities");
        Employee employee = new Employee("Jane Smith", "Manager");
        EmployeeRepository repository = new EmployeeRepository();
        EmployeeReportGenerator reportGenerator = new EmployeeReportGenerator();
        EmailService emailService = new EmailService();
        
        repository.save(employee);
        reportGenerator.generateReport(employee);
        emailService.sendEmail(employee, "Welcome", "Welcome to the company!");
        
        System.out.println("\n--- Key Benefits ---");
        System.out.println("✓ Each class has one reason to change");
        System.out.println("✓ Easier to test each responsibility independently");
        System.out.println("✓ Changes to database logic don't affect Employee class");
        System.out.println("✓ Changes to email logic don't affect Employee class");
    }
}
