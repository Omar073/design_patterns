package UMLRelationships;

// ============================================
// UML Relationships - Code Examples
// ============================================

// ============================================
// 1. GENERALIZATION (Inheritance)
// ============================================
// Represents "is-a" relationship
// UML: Solid arrow from child to parent

class Animal {
    protected String name;
    
    public Animal(String name) {
        this.name = name;
    }
    
    public void makeSound() {
        System.out.println(name + " makes a sound");
    }
}

class Dog extends Animal {  // Generalization: Dog is-a Animal
    public Dog(String name) {
        super(name);
    }
    
    @Override
    public void makeSound() {
        System.out.println(name + " barks");
    }
    
    public void fetch() {
        System.out.println(name + " fetches the ball");
    }
}

// ============================================
// 2. ASSOCIATION
// ============================================
// General link between two independent classes
// UML: Simple line connecting classes

class Student {
    private String name;
    private Course course;  // Association: Student uses Course
    
    public Student(String name) {
        this.name = name;
    }
    
    public void enroll(Course course) {
        this.course = course;
        System.out.println(name + " enrolled in " + course.getName());
    }
    
    public Course getCourse() {
        return course;
    }
}

class Course {
    private String name;
    
    public Course(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
}

// ============================================
// 3. AGGREGATION
// ============================================
// "Has-a" relationship (weak ownership)
// UML: Hollow diamond on whole side
// Part can exist independently

class University {
    private String name;
    private java.util.List<Department> departments;  // Aggregation
    
    public University(String name) {
        this.name = name;
        this.departments = new java.util.ArrayList<>();
    }
    
    public void addDepartment(Department department) {
        departments.add(department);
        System.out.println("Added " + department.getName() + " to " + name);
    }
    
    public void removeDepartment(Department department) {
        departments.remove(department);
        // Department still exists independently
    }
    
    public void displayDepartments() {
        System.out.println(name + " has departments:");
        for (Department dept : departments) {
            System.out.println("  - " + dept.getName());
        }
    }
}

class Department {
    private String name;
    
    public Department(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
}

// ============================================
// 4. COMPOSITION
// ============================================
// "Owns" relationship (strong ownership)
// UML: Filled diamond on whole side
// Part cannot exist without whole

class House {
    private String address;
    private java.util.List<Room> rooms;  // Composition
    
    public House(String address) {
        this.address = address;
        this.rooms = new java.util.ArrayList<>();
        // Rooms are created with the house (composition)
        rooms.add(new Room("Living Room"));
        rooms.add(new Room("Bedroom"));
        rooms.add(new Room("Kitchen"));
    }
    
    public void addRoom(String roomName) {
        rooms.add(new Room(roomName));
    }
    
    public void displayRooms() {
        System.out.println("House at " + address + " has rooms:");
        for (Room room : rooms) {
            System.out.println("  - " + room.getName());
        }
    }
    
    // When house is destroyed, rooms are destroyed too
    public void demolish() {
        System.out.println("Demolishing house at " + address);
        rooms.clear();  // Rooms are destroyed with house
    }
}

class Room {
    private String name;
    
    public Room(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
}

// ============================================
// ENCAPSULATION Example
// ============================================

class BankAccount {
    private double balance;  // Private - encapsulated
    
    public BankAccount(double initialBalance) {
        if (initialBalance >= 0) {
            this.balance = initialBalance;
        } else {
            this.balance = 0;
        }
    }
    
    public void deposit(double amount) {  // Public interface
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: $" + amount);
        }
    }
    
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrew: $" + amount);
            return true;
        }
        return false;
    }
    
    public double getBalance() {  // Controlled access
        return balance;
    }
}

// ============================================
// POLYMORPHISM Examples
// ============================================

// Compile-Time Polymorphism (Method Overloading)
class Calculator {
    int add(int a, int b) {
        System.out.println("Adding two integers");
        return a + b;
    }
    
    double add(double a, double b) {
        System.out.println("Adding two doubles");
        return a + b;
    }
    
    int add(int a, int b, int c) {
        System.out.println("Adding three integers");
        return a + b + c;
    }
}

// Run-Time Polymorphism (Method Overriding)
class Vehicle {
    void start() {
        System.out.println("Vehicle is starting...");
    }
}

class Car extends Vehicle {
    @Override
    void start() {
        System.out.println("Car engine is starting...");
    }
}

class Motorcycle extends Vehicle {
    @Override
    void start() {
        System.out.println("Motorcycle engine is starting...");
    }
}

// ============================================
// ABSTRACTION Example
// ============================================

interface Shape {
    double calculateArea();
    void draw();
}

class Circle implements Shape {
    private double radius;
    
    public Circle(double radius) {
        this.radius = radius;
    }
    
    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
    
    @Override
    public void draw() {
        System.out.println("Drawing a circle with radius " + radius);
    }
}

class Rectangle implements Shape {
    private double width, height;
    
    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }
    
    @Override
    public double calculateArea() {
        return width * height;
    }
    
    @Override
    public void draw() {
        System.out.println("Drawing a rectangle " + width + "x" + height);
    }
}

// ============================================
// DEMO
// ============================================

public class RelationshipExamples {
    public static void main(String[] args) {
        System.out.println("=== UML Relationships Examples ===\n");
        
        // 1. Generalization (Inheritance)
        System.out.println("1. GENERALIZATION (Inheritance):");
        Animal animal = new Animal("Generic Animal");
        Dog dog = new Dog("Buddy");
        animal.makeSound();
        dog.makeSound();
        dog.fetch();
        
        System.out.println("\n2. ASSOCIATION:");
        Student student = new Student("Alice");
        Course course = new Course("Java Programming");
        student.enroll(course);
        
        System.out.println("\n3. AGGREGATION:");
        Department csDept = new Department("Computer Science");
        Department mathDept = new Department("Mathematics");
        // Departments can exist independently
        University university = new University("State University");
        university.addDepartment(csDept);
        university.addDepartment(mathDept);
        university.displayDepartments();
        // Departments still exist even if university is removed
        
        System.out.println("\n4. COMPOSITION:");
        House house = new House("123 Main St");
        house.displayRooms();
        // Rooms cannot exist without house
        house.demolish();
        
        System.out.println("\n5. ENCAPSULATION:");
        BankAccount account = new BankAccount(1000);
        account.deposit(500);
        account.withdraw(200);
        System.out.println("Balance: $" + account.getBalance());
        
        System.out.println("\n6. COMPILE-TIME POLYMORPHISM (Overloading):");
        Calculator calc = new Calculator();
        System.out.println("Result: " + calc.add(5, 3));
        System.out.println("Result: " + calc.add(5.5, 3.2));
        System.out.println("Result: " + calc.add(1, 2, 3));
        
        System.out.println("\n7. RUN-TIME POLYMORPHISM (Overriding):");
        Vehicle v1 = new Car();
        Vehicle v2 = new Motorcycle();
        v1.start();  // Calls Car's start()
        v2.start();  // Calls Motorcycle's start()
        
        System.out.println("\n8. ABSTRACTION:");
        Shape circle = new Circle(5.0);
        Shape rectangle = new Rectangle(4.0, 6.0);
        circle.draw();
        System.out.println("Circle area: " + circle.calculateArea());
        rectangle.draw();
        System.out.println("Rectangle area: " + rectangle.calculateArea());
    }
}
