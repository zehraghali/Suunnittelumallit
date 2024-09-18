package printer;

// PrinterDemo.java
public class PrinterDemo {
    public static void main(String[] args) {
        // Basic printer: Prints "Hello World!" to the console
        Printer basicPrinter = new BasicPrinter();
        basicPrinter.print("Hello World!");

        // File printer: Writes "Hello World!" to a file
        Printer filePrinter = new FilePrinter(new BasicPrinter());
        filePrinter.print("Hello World!");

        // Encrypted printer: Encrypts and prints the message
        Printer encryptedPrinter = new EncryptedPrinter(new BasicPrinter());
        encryptedPrinter.print("Hello World!");

        // EncryptedFilePrinter: Encrypts and writes the message to a file
        Printer encryptedFilePrinter = new EncryptedPrinter(new FilePrinter(new BasicPrinter()));
        encryptedFilePrinter.print("Hello World!");
    }
}
