package SOLID;

// SOLID Principle: Dependency Inversion Principle (DIP)
// High-level modules should not depend on low-level modules. Both should depend on abstractions.

// ❌ BAD: High-level module depends on low-level module
class MySQLDatabaseBad {
    public void save(String data) {
        System.out.println("Saving to MySQL: " + data);
    }
    
    public String load(int id) {
        System.out.println("Loading from MySQL, ID: " + id);
        return "Data from MySQL";
    }
}

// High-level module directly depends on concrete MySQLDatabase
class UserServiceBad {
    private MySQLDatabaseBad database;  // Direct dependency!
    
    public UserServiceBad() {
        this.database = new MySQLDatabaseBad();  // Tight coupling!
    }
    
    public void saveUser(String userData) {
        database.save(userData);
    }
    
    public String getUser(int id) {
        return database.load(id);
    }
}

// ✅ GOOD: Both depend on abstraction (interface)
interface Database {
    void save(String data);
    String load(int id);
}

// Low-level module implements interface
class MySQLDatabase implements Database {
    @Override
    public void save(String data) {
        System.out.println("Saving to MySQL: " + data);
    }
    
    @Override
    public String load(int id) {
        System.out.println("Loading from MySQL, ID: " + id);
        return "Data from MySQL";
    }
}

class PostgreSQLDatabase implements Database {
    @Override
    public void save(String data) {
        System.out.println("Saving to PostgreSQL: " + data);
    }
    
    @Override
    public String load(int id) {
        System.out.println("Loading from PostgreSQL, ID: " + id);
        return "Data from PostgreSQL";
    }
}

class MongoDBDatabase implements Database {
    @Override
    public void save(String data) {
        System.out.println("Saving to MongoDB: " + data);
    }
    
    @Override
    public String load(int id) {
        System.out.println("Loading from MongoDB, ID: " + id);
        return "Data from MongoDB";
    }
}

// High-level module depends on abstraction (interface)
class UserService {
    private Database database;  // Depends on interface!
    
    // Dependency injection - database passed in constructor
    public UserService(Database database) {
        this.database = database;
    }
    
    public void saveUser(String userData) {
        database.save(userData);
    }
    
    public String getUser(int id) {
        return database.load(id);
    }
}

// Demo
public class DependencyInversionExample {
    public static void main(String[] args) {
        System.out.println("=== Dependency Inversion Principle (DIP) ===\n");
        
        System.out.println("❌ BAD: Direct dependency on concrete class");
        UserServiceBad badService = new UserServiceBad();
        badService.saveUser("User data");
        System.out.println("Problem: Can't easily switch to PostgreSQL or MongoDB!");
        
        System.out.println("\n✅ GOOD: Dependency on abstraction");
        
        // Can easily switch database implementations
        Database mysql = new MySQLDatabase();
        UserService service1 = new UserService(mysql);
        service1.saveUser("User data");
        System.out.println(service1.getUser(1));
        
        Database postgres = new PostgreSQLDatabase();
        UserService service2 = new UserService(postgres);
        service2.saveUser("User data");
        System.out.println(service2.getUser(1));
        
        Database mongo = new MongoDBDatabase();
        UserService service3 = new UserService(mongo);
        service3.saveUser("User data");
        System.out.println(service3.getUser(1));
        
        System.out.println("\n--- Key Benefits ---");
        System.out.println("✓ High-level module depends on abstraction, not concrete class");
        System.out.println("✓ Can easily switch implementations");
        System.out.println("✓ Easy to test (can inject mock database)");
        System.out.println("✓ Follows DIP - depends on abstractions");
    }
}
