package printer;

// EncryptedPrinter.java
public class EncryptedPrinter extends PrinterDecorator {

    public EncryptedPrinter(Printer printer) {
        super(printer);
    }

    @Override
    public void print(String message) {
        String encryptedMessage = encrypt(message);  // Encrypt the message
        super.print(encryptedMessage);  // Print the encrypted message
    }

    // Simple Caesar cipher for encryption (shift by 3)
    private String encrypt(String message) {
        StringBuilder encrypted = new StringBuilder();
        int shift = 3;  // Caesar cipher with a shift of 3
        for (char c : message.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                c = (char) ((c - base + shift) % 26 + base);  // Shift the character
            }
            encrypted.append(c);
        }
        return encrypted.toString();
    }
}
