/**
 * Problem 1: Enterprise Reporting System
 * Solution: Simple Factory Pattern
 * 
 * This demo shows how Simple Factory solves the problem of creating
 * database connections and report generators without coupling the client
 * to concrete implementations.
 * 
 * Note: DatabaseConnection and ReportGenerator are independent products,
 * so Abstract Factory is not needed. Simple Factory is sufficient.
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

// Concrete products for Oracle
class OracleConnection implements DatabaseConnection {
    public void connect() {
        System.out.println("Connecting to Oracle database...");
    }

    public void executeQuery(String query) {
        System.out.println("Executing Oracle query: " + query);
    }
}

// Report generators (format-specific, not database-specific)
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

// Simple Factory for database connections
class DatabaseConnectionFactory {
    public DatabaseConnection createConnection(String dbType) {
        if (dbType == null) {
            throw new IllegalArgumentException("Database type cannot be null");
        }
        if (dbType.equalsIgnoreCase("MYSQL")) {
            return new MySQLConnection();
        } else if (dbType.equalsIgnoreCase("ORACLE")) {
            return new OracleConnection();
        }
        throw new IllegalArgumentException("Unsupported database type: " + dbType);
    }
}

// Simple Factory for report generators
class ReportGeneratorFactory {
    public ReportGenerator createReportGenerator(String format) {
        if (format == null) {
            throw new IllegalArgumentException("Report format cannot be null");
        }
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

    public ReportingClient(String dbType, String reportFormat) {
        // Use simple factories to create products independently
        DatabaseConnectionFactory dbFactory = new DatabaseConnectionFactory();
        ReportGeneratorFactory reportFactory = new ReportGeneratorFactory();

        this.dbConnection = dbFactory.createConnection(dbType);
        this.reportGenerator = reportFactory.createReportGenerator(reportFormat);
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

        // MySQL with PDF report
        System.out.println("--- MySQL Database with PDF Report ---");
        ReportingClient mysqlPdfClient = new ReportingClient("MySQL", "PDF");
        mysqlPdfClient.generateReport("SELECT * FROM users");

        // Oracle with HTML report
        System.out.println("\n--- Oracle Database with HTML Report ---");
        ReportingClient oracleHtmlClient = new ReportingClient("Oracle", "HTML");
        oracleHtmlClient.generateReport("SELECT * FROM employees");

        // MySQL with HTML report (shows independence)
        System.out.println("\n--- MySQL Database with HTML Report ---");
        ReportingClient mysqlHtmlClient = new ReportingClient("MySQL", "HTML");
        mysqlHtmlClient.generateReport("SELECT * FROM products");

        System.out.println("\n✓ Simple Factory allows independent creation of database and report format!");
        System.out.println("✓ No need for Abstract Factory since products are independent.");
    }
}
