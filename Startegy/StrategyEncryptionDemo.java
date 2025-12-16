// Strategy pattern demo with pluggable encryption algorithms.
// Simulates encryption to keep the demo self-contained.

interface EncryptionStrategy {
    String encrypt(String plainText);
}

class AesEncryption implements EncryptionStrategy {
    @Override
    public String encrypt(String plainText) {
        // Pretend to encrypt with AES
        return "[AES]" + plainText + "[/AES]";
    }
}

class RsaEncryption implements EncryptionStrategy {
    @Override
    public String encrypt(String plainText) {
        // Pretend to encrypt with RSA
        return "[RSA]" + plainText + "[/RSA]";
    }
}

class EccEncryption implements EncryptionStrategy {
    @Override
    public String encrypt(String plainText) {
        // Pretend to encrypt with ECC
        return "[ECC]" + plainText + "[/ECC]";
    }
}

class SecureMessenger {
    private EncryptionStrategy strategy;

    public SecureMessenger(EncryptionStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(EncryptionStrategy strategy) {
        this.strategy = strategy;
    }

    public String send(String recipient, String message) {
        String cipherText = strategy.encrypt(message);
        return "To " + recipient + ": " + cipherText;
    }
}

public class StrategyEncryptionDemo {
    public static void main(String[] args) {
        SecureMessenger messenger = new SecureMessenger(new AesEncryption());
        System.out.println(messenger.send("Alice", "Hi Alice!"));

        System.out.println("Switching to RSA for higher security...");
        messenger.setStrategy(new RsaEncryption());
        System.out.println(messenger.send("Bob", "Confidential meeting at 5."));

        System.out.println("Switching to ECC for military-grade scenario...");
        messenger.setStrategy(new EccEncryption());
        System.out.println(messenger.send("HQ", "Coordinates: 31.2N, 29.9E"));
    }
}

