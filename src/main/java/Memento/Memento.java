package Memento;

import java.awt.Color;
import java.time.LocalDateTime;

public class Memento {
    private final Color color1;
    private final Color color2;
    private final Color color3;
    private final boolean checkboxStatus;
    private final LocalDateTime timestamp;

    public Memento(Color color1, Color color2, Color color3, boolean checkboxStatus) {
        this.color1 = color1;
        this.color2 = color2;
        this.color3 = color3;
        this.checkboxStatus = checkboxStatus;
        this.timestamp = LocalDateTime.now();
    }

    public Color getColor1() { return color1; }
    public Color getColor2() { return color2; }
    public Color getColor3() { return color3; }
    public boolean getCheckboxStatus() { return checkboxStatus; }
    public LocalDateTime getTimestamp() { return timestamp; }
}
