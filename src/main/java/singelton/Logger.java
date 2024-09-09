package singelton;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {
    private static Logger instance;
    private BufferedWriter writer;
    private String fileName;

    // Private constructor to prevent instantiation
    private Logger() {
        this.fileName = "default_log.txt"; // Default log file
        openFile();
    }

    // Method to get the single instance of Logger
    public static Logger getInstance() {
        if (instance == null) {
            synchronized (Logger.class) {
                if (instance == null) {
                    instance = new Logger();
                }
            }
        }
        return instance;
    }

    // Open the log file for writing
    private void openFile() {
        try {
            if (writer != null) {
                writer.close(); // Close any existing writer if a new file is opened
            }
            writer = new BufferedWriter(new FileWriter(fileName, true)); // Append mode
        } catch (IOException e) {
            System.out.println("Failed to open log file: " + e.getMessage());
        }
    }


    public void setFileName(String newFileName) {
        this.fileName = newFileName;
        openFile();
    }

    public void write(String message) {
        try {
            writer.write(message);
            writer.newLine();
            writer.flush(); // Ensure message is written immediately
        } catch (IOException e) {
            System.out.println("Error writing to log file: " + e.getMessage());
        }
    }

    // Close the writer safely
    public void close() {
        try {
            if (writer != null) {
                writer.close();
            }
        } catch (IOException e) {
            System.out.println("Error closing the log file: " + e.getMessage());
        }
    }
}
