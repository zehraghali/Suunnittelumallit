package printer;

// PrinterDecorator.java
public abstract class PrinterDecorator implements Printer {
    protected Printer printer;

    public PrinterDecorator(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void print(String message) {
        printer.print(message); // Calls the wrapped printer's print method
    }
}
