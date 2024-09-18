package State;

// ExpertState.java
public class ExpertState implements State {
    private static final int XP_THRESHOLD = 300;

    @Override
    public void train(Character character) {
        System.out.println(character.getName() + " is training...");
        character.addExperiencePoints(20);
        if (character.getExperiencePoints() >= XP_THRESHOLD) {
            System.out.println(character.getName() + " has advanced to Master level!");
            character.setState(character.getMasterState());
        }
    }

    @Override
    public void meditate(Character character) {
        System.out.println(character.getName() + " is meditating...");
        character.setHealthPoints(character.getHealthPoints() + 10);
    }

    @Override
    public void fight(Character character) {
        System.out.println(character.getName() + " is fighting...");
        character.addExperiencePoints(50);
        character.setHealthPoints(character.getHealthPoints() - 30);
    }

    @Override
    public void displayStatus(Character character) {
        System.out.println("Character: " + character.getName());
        System.out.println("Level: Expert");
        System.out.println("XP: " + character.getExperiencePoints());
        System.out.println("HP: " + character.getHealthPoints());
    }
}
