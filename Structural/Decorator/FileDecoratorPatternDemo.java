// Decorator Pattern – File Encryption and Compression
// Demonstrates proper Decorator pattern with abstract decorator and concrete decorators
// Shows decorator chaining: NormalFile -> EncryptionDecorator -> CompressionDecorator
// Roles:
//   - Component: File
//   - Concrete component: NormalFile
//   - Decorator base: FileDecorator
//   - Concrete decorators: EncryptionDecorator, CompressionDecorator
//   - Client: main() builds a chain to add behaviors dynamically

// Component interface
interface File {
    String readData();
    void writeData(String data);
}

// Concrete component
class NormalFile implements File {
    private String data = "Data";
    
    public String readData() {
        return data;
    }
    
    public void writeData(String data) {
        this.data = data;
    }
}

// Abstract decorator
abstract class FileDecorator implements File {
    private File wrappedFile;
    
    public FileDecorator(File wrappedFile) {
        this.wrappedFile = wrappedFile;
    }
    
    @Override
    public String readData() {
        return wrappedFile.readData();
    }
    
    @Override
    public void writeData(String data) {
        wrappedFile.writeData(data);
    }
}

// Concrete decorator - Encryption
class EncryptionDecorator extends FileDecorator {
    public EncryptionDecorator(File wrappedFile) {
        super(wrappedFile);
    }
    
    private String encrypt(String data) {
        // Encryption logic
        return "[ENCRYPTED]" + data + "[/ENCRYPTED]";
    }
    
    private String decrypt(String data) {
        // Decryption logic
        if (data.startsWith("[ENCRYPTED]") && data.endsWith("[/ENCRYPTED]")) {
            return data.substring(11, data.length() - 12);
        }
        return data;
    }
    
    @Override
    public String readData() {
        String data = super.readData();
        return decrypt(data);
    }
    
    @Override
    public void writeData(String data) {
        data = encrypt(data);
        super.writeData(data);
    }
}

// Concrete decorator - Compression
class CompressionDecorator extends FileDecorator {
    public CompressionDecorator(File wrappedFile) {
        super(wrappedFile);
    }
    
    private String compress(String data) {
        // Compression logic
        return "[COMPRESSED]" + data + "[/COMPRESSED]";
    }
    
    private String uncompress(String data) {
        // Decompression logic
        if (data.startsWith("[COMPRESSED]") && data.endsWith("[/COMPRESSED]")) {
            return data.substring(12, data.length() - 13);
        }
        return data;
    }
    
    @Override
    public String readData() {
        String data = super.readData();
        return uncompress(data);
    }
    
    @Override
    public void writeData(String data) {
        data = compress(data);
        super.writeData(data);
    }
}

// Usage demonstration
public class FileDecoratorPatternDemo {
    public static void main(String[] args) {
        System.out.println("== Decorator Pattern ==\n");
        
        // Start with base file
        File file = new NormalFile();
        
        // Chain decorators
        file = new EncryptionDecorator(file);
        file = new CompressionDecorator(file);
        
        // Write data through decorator chain
        System.out.println("--- Writing Data ---");
        file.writeData("Ok");
        
        // Read data through decorator chain
        System.out.println("\n--- Reading Data ---");
        String data = file.readData();
        System.out.println("Final data: " + data);
        
        System.out.println("\n✓ Decorators wrap components (composition)");
        System.out.println("✓ Decorators can be chained in any order");
        System.out.println("✓ Each decorator adds its own behavior");
    }
}

