package State;

// Character.java
public class Character {
    private String name;
    private int experiencePoints;
    private int healthPoints;
    private State currentState;

    // Character levels (states)
    private State noviceState;
    private State intermediateState;
    private State expertState;
    private State masterState;

    public Character(String name) {
        this.name = name;
        this.experiencePoints = 0;
        this.healthPoints = 100;

        // Initialize states
        noviceState = new NoviceState();
        intermediateState = new IntermediateState();
        expertState = new ExpertState();
        masterState = new MasterState();

        // Start at Novice
        currentState = noviceState;
    }

    // Delegate actions to the current state
    public void train() {
        currentState.train(this);
    }

    public void meditate() {
        currentState.meditate(this);
    }

    public void fight() {
        currentState.fight(this);
    }

    public void displayStatus() {
        currentState.displayStatus(this);
    }

    // State transitions
    public void setState(State newState) {
        this.currentState = newState;
    }

    public State getNoviceState() { return noviceState; }
    public State getIntermediateState() { return intermediateState; }
    public State getExpertState() { return expertState; }
    public State getMasterState() { return masterState; }

    // Getters and setters for character attributes
    public int getExperiencePoints() {
        return experiencePoints;
    }

    public void addExperiencePoints(int points) {
        this.experiencePoints += points;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public String getName() {
        return name;
    }

    public void compareTo(int i) {
    }
}
