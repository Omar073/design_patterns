// Anti-Pattern Example: Hard Coding
// This demonstrates hard-coded values and how to use configuration

import java.util.Properties;

// ❌ BAD: Hard-coded values
class HardCodingBad {
    // Hard-coded credentials - SECURITY RISK!
    String username = "admin";
    String password = "12345";
    
    // Hard-coded file path - won't work on other machines
    String filePath = "C:/Users/Walaa/Desktop/data.txt";
    
    // Hard-coded business rules - requires recompilation to change
    double calculateBonus(double salary) {
        if (salary > 5000) {
            return salary * 0.10;  // Magic number 0.10
        }
        return 0;
    }
    
    // Hard-coded URL - breaks in different environments
    String apiUrl = "http://192.168.1.10:8080/api";
}

// ✅ GOOD: Using configuration
class HardCodingGood {
    private Properties config;
    
    public HardCodingGood(Properties config) {
        this.config = config;
    }
    
    // Credentials from configuration
    String getUsername() {
        return config.getProperty("USERNAME");
    }
    
    String getPassword() {
        return config.getProperty("PASSWORD");
    }
    
    // File path from configuration
    String getFilePath() {
        return config.getProperty("DATA_PATH");
    }
    
    // Business rules from configuration
    double calculateBonus(double salary) {
        double bonusRate = Double.parseDouble(config.getProperty("BONUS_RATE", "0.10"));
        double minSalary = Double.parseDouble(config.getProperty("MIN_SALARY_FOR_BONUS", "5000"));
        
        if (salary > minSalary) {
            return salary * bonusRate;
        }
        return 0;
    }
    
    // URL from environment variable or configuration
    String getApiUrl() {
        String envUrl = System.getenv("API_URL");
        return envUrl != null ? envUrl : config.getProperty("API_URL");
    }
}

// Demo
public class HardCodingExample {
    public static void main(String[] args) {
        System.out.println("=== Hard Coding Anti-Pattern Example ===\n");
        
        System.out.println("❌ BAD: Hard-coded values");
        HardCodingBad bad = new HardCodingBad();
        System.out.println("Username: " + bad.username);
        System.out.println("File path: " + bad.filePath);
        System.out.println("Bonus for 6000: " + bad.calculateBonus(6000));
        System.out.println("API URL: " + bad.apiUrl);
        
        System.out.println("\n✅ GOOD: Configuration-based");
        Properties config = new Properties();
        config.setProperty("USERNAME", "admin");
        config.setProperty("PASSWORD", "secure_password");
        config.setProperty("DATA_PATH", "/app/data/data.txt");
        config.setProperty("BONUS_RATE", "0.10");
        config.setProperty("MIN_SALARY_FOR_BONUS", "5000");
        config.setProperty("API_URL", "https://api.example.com");
        
        HardCodingGood good = new HardCodingGood(config);
        System.out.println("Username: " + good.getUsername());
        System.out.println("File path: " + good.getFilePath());
        System.out.println("Bonus for 6000: " + good.calculateBonus(6000));
        System.out.println("API URL: " + good.getApiUrl());
        
        System.out.println("\n--- Key Benefits of Using Configuration ---");
        System.out.println("✓ Security - credentials not in code");
        System.out.println("✓ Flexibility - change without recompilation");
        System.out.println("✓ Portability - works across environments");
        System.out.println("✓ Maintainability - easy to update values");
    }
}
