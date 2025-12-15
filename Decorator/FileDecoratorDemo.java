// Decorator Pattern – File Encryption and Compression Example
// Demonstrates adding encryption and compression functionality to File operations
// Note: This example uses inheritance-based approach (as shown in diagram)
// True Decorator pattern uses composition, but this shows the concept
// Read in parallel with FileDecoratorPatternDemo.java to see composition-based decorators.

// Base class
class File {
    String readData() {
        return "Data";
    }
    
    void writeData(String data) {
        // Base implementation
    }
}

// EncryptedFile extends File to add encryption/decryption
class EncryptedFile extends File {
    String encrypt(String data) {
        // Simple encryption simulation
        return "[ENCRYPTED]" + data + "[/ENCRYPTED]";
    }
    
    String decrypt(String data) {
        // Simple decryption simulation
        if (data.startsWith("[ENCRYPTED]") && data.endsWith("[/ENCRYPTED]")) {
            return data.substring(11, data.length() - 12);
        }
        return data;
    }
    
    @Override
    String readData() {
        String encryptedData = super.readData();
        return decrypt(encryptedData);
    }
    
    @Override
    void writeData(String data) {
        String encryptedData = encrypt(data);
        super.writeData(encryptedData);
    }
}

// CompressedEncryptedFile extends File to add both compression and encryption
class CompressedEncryptedFile extends File {
    String encrypt(String data) {
        // Simple encryption simulation
        return "[ENCRYPTED]" + data + "[/ENCRYPTED]";
    }
    
    String decrypt(String data) {
        // Simple decryption simulation
        if (data.startsWith("[ENCRYPTED]") && data.endsWith("[/ENCRYPTED]")) {
            return data.substring(11, data.length() - 12);
        }
        return data;
    }
    
    String compress(String data) {
        // Simple compression simulation
        return "[COMPRESSED]" + data + "[/COMPRESSED]";
    }
    
    String uncompress(String data) {
        // Simple decompression simulation
        if (data.startsWith("[COMPRESSED]") && data.endsWith("[/COMPRESSED]")) {
            return data.substring(12, data.length() - 13);
        }
        return data;
    }
    
    @Override
    String readData() {
        String rawData = super.readData();
        String decryptedData = decrypt(rawData);
        return uncompress(decryptedData);
    }
    
    @Override
    void writeData(String data) {
        String compressedData = compress(data);
        String encryptedData = encrypt(compressedData);
        super.writeData(encryptedData);
    }
}

// Usage demonstration
public class FileDecoratorDemo {
    public static void main(String[] args) {
        System.out.println("== File Decorator Pattern (Inheritance-based) ==\n");
        
        // Base file
        System.out.println("--- Base File ---");
        File baseFile = new File();
        System.out.println("Read: " + baseFile.readData());
        baseFile.writeData("Hello World");
        
        // Encrypted file
        System.out.println("\n--- Encrypted File ---");
        EncryptedFile encryptedFile = new EncryptedFile();
        encryptedFile.writeData("Hello World");
        System.out.println("Read: " + encryptedFile.readData());
        
        // Compressed and Encrypted file
        System.out.println("\n--- Compressed and Encrypted File ---");
        CompressedEncryptedFile compressedEncryptedFile = new CompressedEncryptedFile();
        compressedEncryptedFile.writeData("Hello World");
        System.out.println("Read: " + compressedEncryptedFile.readData());
        
        System.out.println("\n✓ Inheritance-based approach adds functionality");
        System.out.println("✓ Note: True Decorator pattern uses composition (wrapping)");
        System.out.println("✓ See DecoratorDemo.java for composition-based example");
    }
}
