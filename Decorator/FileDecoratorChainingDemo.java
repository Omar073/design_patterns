// Decorator Pattern – File Encryption and Compression with Chaining
// Demonstrates proper Decorator pattern using composition (wrapping)
// Shows how decorators can be chained to add multiple behaviors
// Roles:
//   - Component: File
//   - Concrete component: NormalFile
//   - Decorator base: FileDecorator delegates to wrapped File
//   - Concrete decorators: EncryptionDecorator, CompressionDecorator
//   - Client: main() wraps in sequence to compose behaviors

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
        this.wrappedFile = wrappedFile; // store component being wrapped
    }
    
    @Override
    public String readData() {
        return wrappedFile.readData();
    }
    
    @Override
    public void writeData(String data) {
        wrappedFile.writeData(data);
    }
    
    protected File getWrappedFile() {
        return wrappedFile;
    }
}

// Concrete decorator - Encryption
class EncryptionDecorator extends FileDecorator {
    public EncryptionDecorator(File wrappedFile) {
        super(wrappedFile);
    }
    
    private String encrypt(String data) {
        // Simple encryption simulation
        System.out.println("Encrypted data");
        return "[ENCRYPTED]" + data + "[/ENCRYPTED]";
    }
    
    private String decrypt(String data) {
        // Simple decryption simulation
        System.out.println("decrypted data");
        if (data.startsWith("[ENCRYPTED]") && data.endsWith("[/ENCRYPTED]")) {
            return data.substring(11, data.length() - 12);
        }
        return data;
    }
    
    @Override
    public String readData() {
        String data = super.readData(); // delegate then decrypt
        return decrypt(data);
    }
    
    @Override
    public void writeData(String data) {
        data = encrypt(data); // encrypt before writing downstream
        super.writeData(data);
    }
}

// Concrete decorator - Compression
class CompressionDecorator extends FileDecorator {
    public CompressionDecorator(File wrappedFile) {
        super(wrappedFile);
    }
    
    private String compress(String data) {
        // Simple compression simulation
        System.out.println("Compressed data");
        return "[COMPRESSED]" + data + "[/COMPRESSED]";
    }
    
    private String uncompress(String data) {
        // Simple decompression simulation
        System.out.println("Uncompressed data");
        if (data.startsWith("[COMPRESSED]") && data.endsWith("[/COMPRESSED]")) {
            return data.substring(12, data.length() - 13);
        }
        return data;
    }
    
    @Override
    public String readData() {
        String data = super.readData(); // delegate then uncompress
        return uncompress(data);
    }
    
    @Override
    public void writeData(String data) {
        data = compress(data); // compress before writing downstream
        super.writeData(data);
    }
}

// Usage demonstration
public class FileDecoratorChainingDemo {
    public static void main(String[] args) {
        System.out.println("== Decorator Pattern with Chaining ==\n");
        
        // Start with base file
        File file = new NormalFile();
        
        // Chain decorators: NormalFile -> EncryptionDecorator -> CompressionDecorator
        file = new EncryptionDecorator(file);
        file = new CompressionDecorator(file);
        
        System.out.println("--- Writing Data ---");
        file.writeData("Ok");
        
        System.out.println("\n--- Reading Data ---");
        String data = file.readData();
        System.out.println("Final data: " + data);
        
        System.out.println("\n✓ Decorators are chained (wrapped)");
        System.out.println("✓ Write: Compress -> Encrypt -> Write");
        System.out.println("✓ Read: Read -> Decrypt -> Uncompress");
        System.out.println("✓ Order matters - decorators process in reverse order when reading");
    }
}

