package State;

// MasterState.java
public class MasterState implements State {
    @Override
    public void train(Character character) {
        System.out.println("You have reached the Master level! You don't need to train anymore.");
    }

    @Override
    public void meditate(Character character) {
        System.out.println("You have reached the Master level! You don't need to meditate anymore.");
    }

    @Override
    public void fight(Character character) {
        System.out.println("You have reached the Master level! You don't need to fight anymore.");
    }

    @Override
    public void displayStatus(Character character) {
        System.out.println("Character: " + character.getName());
        System.out.println("Level: Master");
        System.out.println("XP: " + character.getExperiencePoints());
        System.out.println("HP: " + character.getHealthPoints());
    }
}
