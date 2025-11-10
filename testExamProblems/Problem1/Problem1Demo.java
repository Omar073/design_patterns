/**
 * Problem 1: Enterprise Reporting System
 * Solution: Abstract Factory Pattern
 * 
 * This demo shows how Abstract Factory solves the problem of creating
 * families of related objects (database connections + report generators)
 * without coupling the client to concrete implementations.
 */

// Abstract products
interface DatabaseConnection {
    void connect();
    void executeQuery(String query);
}

interface ReportGenerator {
    void generateReport(String data);
}

// Concrete products for MySQL
class MySQLConnection implements DatabaseConnection {
    public void connect() {
        System.out.println("Connecting to MySQL database...");
    }
    
    public void executeQuery(String query) {
        System.out.println("Executing MySQL query: " + query);
    }
}

class PDFReportGenerator implements ReportGenerator {
    public void generateReport(String data) {
        System.out.println("Generating PDF report with data: " + data);
    }
}

class HTMLReportGenerator implements ReportGenerator {
    public void generateReport(String data) {
        System.out.println("Generating HTML report with data: " + data);
    }
}

// Concrete products for Oracle
class OracleConnection implements DatabaseConnection {
    public void connect() {
        System.out.println("Connecting to Oracle database...");
    }
    
    public void executeQuery(String query) {
        System.out.println("Executing Oracle query: " + query);
    }
}

// Abstract Factory
interface ReportFactory {
    DatabaseConnection createDatabaseConnection();
    ReportGenerator createReportGenerator(String format);
}

// Concrete Factory for MySQL
class MySQLReportFactory implements ReportFactory {
    public DatabaseConnection createDatabaseConnection() {
        return new MySQLConnection();
    }
    
    public ReportGenerator createReportGenerator(String format) {
        if (format.equalsIgnoreCase("PDF")) {
            return new PDFReportGenerator();
        } else if (format.equalsIgnoreCase("HTML")) {
            return new HTMLReportGenerator();
        }
        throw new IllegalArgumentException("Unsupported format: " + format);
    }
}

// Concrete Factory for Oracle
class OracleReportFactory implements ReportFactory {
    public DatabaseConnection createDatabaseConnection() {
        return new OracleConnection();
    }
    
    public ReportGenerator createReportGenerator(String format) {
        if (format.equalsIgnoreCase("PDF")) {
            return new PDFReportGenerator();
        } else if (format.equalsIgnoreCase("HTML")) {
            return new HTMLReportGenerator();
        }
        throw new IllegalArgumentException("Unsupported format: " + format);
    }
}

// Client code - works with abstract interfaces only
class ReportingClient {
    private DatabaseConnection dbConnection;
    private ReportGenerator reportGenerator;
    
    public ReportingClient(ReportFactory factory, String reportFormat) {
        // Client doesn't know about concrete implementations
        this.dbConnection = factory.createDatabaseConnection();
        this.reportGenerator = factory.createReportGenerator(reportFormat);
    }
    
    public void generateReport(String query) {
        dbConnection.connect();
        dbConnection.executeQuery(query);
        String data = "Report data from query";
        reportGenerator.generateReport(data);
    }
}

// Demo
public class Problem1Demo {
    public static void main(String[] args) {
        System.out.println("=== Problem 1: Enterprise Reporting System ===\n");
        
        // Use MySQL factory
        System.out.println("--- Using MySQL Factory ---");
        ReportFactory mysqlFactory = new MySQLReportFactory();
        ReportingClient mysqlClient = new ReportingClient(mysqlFactory, "PDF");
        mysqlClient.generateReport("SELECT * FROM users");
        
        System.out.println("\n--- Using Oracle Factory ---");
        ReportFactory oracleFactory = new OracleReportFactory();
        ReportingClient oracleClient = new ReportingClient(oracleFactory, "HTML");
        oracleClient.generateReport("SELECT * FROM employees");
        
        System.out.println("\n✓ Client code works with any factory without modification!");
    }
}

