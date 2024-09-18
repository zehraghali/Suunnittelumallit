package State;

// IntermediateState.java
public class IntermediateState implements State {
    private static final int XP_THRESHOLD = 200;

    @Override
    public void train(Character character) {
        System.out.println(character.getName() + " is training...");
        character.addExperiencePoints(20);
        if (character.getExperiencePoints() >= XP_THRESHOLD) {
            System.out.println(character.getName() + " has advanced to Expert level!");
            character.setState(character.getExpertState());
        }
    }

    @Override
    public void meditate(Character character) {
        System.out.println(character.getName() + " is meditating...");
        character.setHealthPoints(character.getHealthPoints() + 10);
    }

    @Override
    public void fight(Character character) {
        System.out.println("You cannot fight at Intermediate level.");
    }

    @Override
    public void displayStatus(Character character) {
        System.out.println("Character: " + character.getName());
        System.out.println("Level: Intermediate");
        System.out.println("XP: " + character.getExperiencePoints());
        System.out.println("HP: " + character.getHealthPoints());
    }
}
