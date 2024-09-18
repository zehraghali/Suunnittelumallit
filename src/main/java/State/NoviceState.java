package State;

// NoviceState.java
public class NoviceState implements State {
    private static final int XP_THRESHOLD = 100;

    @Override
    public void train(Character character) {
        System.out.println(character.getName() + " is training...");
        character.compareTo(20);
        if (character.getExperiencePoints() >= XP_THRESHOLD) {
            System.out.println(character.getName() + " has advanced to Intermediate level!");
            character.setState(character.getIntermediateState());
        }
    }

    @Override
    public void meditate(Character character) {
        System.out.println("You cannot meditate at Novice level.");
    }

    @Override
    public void fight(Character character) {
        System.out.println("You cannot fight at Novice level.");
    }

    @Override
    public void displayStatus(Character character) {
        System.out.println("Character: " + character.getName());
        System.out.println("Level: Novice");
        System.out.println("XP: " + character.getExperiencePoints());
        System.out.println("HP: " + character.getHealthPoints());
    }
}
