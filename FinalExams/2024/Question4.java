// Final Exam 2024 - Question 4 Solution
// Adapter Pattern Implementation

// Target interface (what SamsungComputer expects)
interface Computer {
    void bufferData(String data);
    void flushData();
    void purgeData();
}

// Adaptee interface (what SeagateDrive provides)
interface SeagateGeneric {
    void read();
    void write(String data);
}

// Adaptee implementation
class SeagateDrive implements SeagateGeneric {
    private String storedData;
    
    public SeagateDrive() {
        this.storedData = "";
    }
    
    @Override
    public void read() {
        System.out.println("Reading from Seagate drive...");
        System.out.println("Data: " + storedData);
    }
    
    @Override
    public void write(String data) {
        System.out.println("Writing to Seagate drive: " + data);
        this.storedData = data;
    }
}

// Adapter: Adapts SeagateGeneric to Computer interface
class SeagateAdapter implements Computer {
    private SeagateGeneric seagateDrive;
    
    public SeagateAdapter(SeagateGeneric seagateDrive) {
        this.seagateDrive = seagateDrive;
    }
    
    @Override
    public void bufferData(String data) {
        // Adapt write() to bufferData()
        System.out.println("Buffering data: " + data);
        seagateDrive.write(data);
    }
    
    @Override
    public void flushData() {
        // Adapt read() to flushData() - flush means read/verify
        System.out.println("Flushing data...");
        seagateDrive.read();
    }
    
    @Override
    public void purgeData() {
        // No direct equivalent in SeagateGeneric - clear data by writing empty string
        System.out.println("Purging data (clearing storage)...");
        seagateDrive.write("");  // Clear data by writing empty string
    }
}

// Client (SamsungComputer)
class SamsungComputer {
    private Computer storage;
    
    public SamsungComputer(Computer storage) {
        this.storage = storage;
    }
    
    public void saveData(String data) {
        storage.bufferData(data);
        storage.flushData();
    }
    
    public void clearData() {
        try {
            storage.purgeData();
        } catch (UnsupportedOperationException e) {
            System.out.println("Purge not supported, using flush instead");
            storage.flushData();
        }
    }
}

// Demo
public class Question4 {
    public static void main(String[] args) {
        System.out.println("=== Final Exam 2024 - Question 4: Adapter Pattern ===\n");
        
        // Create Seagate drive (incompatible with Computer)
        SeagateGeneric seagateDrive = new SeagateDrive();
        
        // Adapt it to Computer interface
        Computer adapter = new SeagateAdapter(seagateDrive);
        
        // Now SamsungComputer can use it
        SamsungComputer computer = new SamsungComputer(adapter);
        
        System.out.println("Saving data to computer:");
        computer.saveData("Important exam data");
        
        System.out.println("\nAttempting to clear data:");
        computer.clearData();
        
        System.out.println("\n--- Pattern Explanation ---");
        System.out.println("Adapter Pattern converts SeagateGeneric interface to Computer interface:");
        System.out.println("- bufferData() → write()");
        System.out.println("- flushData() → read()");
        System.out.println("- purgeData() → write(\"\") (clear data by writing empty string)");
    }
}
