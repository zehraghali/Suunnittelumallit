package State;

// State.java
public interface State {
    void train(Character character);
    void meditate(Character character);
    void fight(Character character);
    void displayStatus(Character character);
}
