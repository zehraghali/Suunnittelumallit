package Template_Method;

import java.util.Scanner;
import java.util.Random;

public class RockPaperScissorsGame extends Game {
    private int[] scores;
    private int roundsToWin;
    private int numberOfPlayers;
    private Scanner scanner;

    public RockPaperScissorsGame(int roundsToWin) {
        this.roundsToWin = roundsToWin;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void initializeGame(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
        this.scores = new int[numberOfPlayers];
        System.out.println("Starting Rock-Paper-Scissors Game with " + numberOfPlayers + " players!");
        System.out.println("First to " + roundsToWin + " wins!");
    }

    @Override
    public boolean endOfGame() {
        for (int score : scores) {
            if (score >= roundsToWin) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void playSingleTurn(int player) {
        System.out.println("Player " + (player + 1) + ", enter your choice (Rock, Paper, Scissors): ");
        String playerChoice = scanner.nextLine().toLowerCase();

        String opponentChoice = player == 0 ? "rock" : randomChoice();

        System.out.println("Player " + (player + 1) + " chose: " + playerChoice);
        System.out.println("Player " + ((player + 1) % numberOfPlayers + 1) + " chose: " + opponentChoice);

        int result = determineWinner(playerChoice, opponentChoice);
        if (result == 0) {
            System.out.println("It's a draw!");
        } else if (result == 1) {
            System.out.println("Player " + (player + 1) + " wins this round!");
            scores[player]++;
        } else {
            System.out.println("Player " + ((player + 1) % numberOfPlayers + 1) + " wins this round!");
            scores[(player + 1) % numberOfPlayers]++;
        }

        System.out.println("Current scores: Player 1: " + scores[0] + ", Player 2: " + scores[1]);
    }

    @Override
    public void displayWinner() {
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] >= roundsToWin) {
                System.out.println("Player " + (i + 1) + " is the overall winner!");
                return;
            }
        }
    }

    private int determineWinner(String playerChoice, String opponentChoice) {
        if (playerChoice.equals(opponentChoice)) {
            return 0; // Draw
        }
        switch (playerChoice) {
            case "rock":
                return opponentChoice.equals("scissors") ? 1 : 2;
            case "paper":
                return opponentChoice.equals("rock") ? 1 : 2;
            case "scissors":
                return opponentChoice.equals("paper") ? 1 : 2;
            default:
                System.out.println("Invalid choice! Try again.");
                return -1; // Invalid choice
        }
    }

        private String randomChoice() {
        String[] choices = {"rock", "paper", "scissors"};
        Random random = new Random();
        return choices[random.nextInt(choices.length)];
    }

    public static void main(String[] args) {
        RockPaperScissorsGame game = new RockPaperScissorsGame(3); // First to 3 wins
        game.play(2); // Start the game with 2 players
    }
}
