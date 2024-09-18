package printer;// FilePrinter.java
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FilePrinter extends PrinterDecorator {

    public FilePrinter(Printer printer) {
        super(printer);
    }

    @Override
    public void print(String message) {
        super.print(message); // Optionally prints to the console
        writeToFile(message);  // Writes the message to a file
    }

    private void writeToFile(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt", true))) {
            writer.write(message);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
