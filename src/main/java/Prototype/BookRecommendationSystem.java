package Prototype;

import java.util.Scanner;

public class BookRecommendationSystem {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Recommendation actionBooksRecommendation = new Recommendation("Action Lovers");
        actionBooksRecommendation.addBook(new Book("The Bourne Identity", "Robert Ludlum", "Thriller", 1980));
        actionBooksRecommendation.addBook(new Book("The Hunt for Red October", "Tom Clancy", "Thriller", 1984));

        Recommendation romanceBooksRecommendation = new Recommendation("Romance Enthusiasts");
        romanceBooksRecommendation.addBook(new Book("Pride and Prejudice", "Jane Austen", "Romance", 1813));
        romanceBooksRecommendation.addBook(new Book("The Notebook", "Nicholas Sparks", "Romance", 1996));

        System.out.println("Welcome to the Book Recommendation System!");
        boolean running = true;

        while (running) {
            System.out.println("\nSelect an option:");
            System.out.println("1. View recommendations");
            System.out.println("2. Clone a recommendation");
            System.out.println("3. Modify a recommendation");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewRecommendations(actionBooksRecommendation, romanceBooksRecommendation);
                    break;
                case 2:
                    cloneRecommendation(actionBooksRecommendation, romanceBooksRecommendation);
                    break;
                case 3:
                    modifyRecommendation(actionBooksRecommendation, romanceBooksRecommendation);
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option, try again.");
            }
        }

        System.out.println("Thank you for using the Book Recommendation System!");
    }

    private static void viewRecommendations(Recommendation actionBooksRecommendation, Recommendation romanceBooksRecommendation) {
        actionBooksRecommendation.displayRecommendation();
        romanceBooksRecommendation.displayRecommendation();
    }

    private static void cloneRecommendation(Recommendation actionBooksRecommendation, Recommendation romanceBooksRecommendation) {
        System.out.println("Enter the recommendation you want to clone:");
        System.out.println("1. Action Lovers");
        System.out.println("2. Romance Enthusiasts");
        int choice = scanner.nextInt();
        scanner.nextLine();

        Recommendation clonedRecommendation;
        if (choice == 1) {
            clonedRecommendation = actionBooksRecommendation.clone();
            System.out.println("Cloned Action Lovers Recommendation:");
        } else if (choice == 2) {
            clonedRecommendation = romanceBooksRecommendation.clone();
            System.out.println("Cloned Romance Enthusiasts Recommendation:");
        } else {
            System.out.println("Invalid choice.");
            return;
        }
        clonedRecommendation.displayRecommendation();
    }

    private static void modifyRecommendation(Recommendation actionBooksRecommendation, Recommendation romanceBooksRecommendation) {
        System.out.println("Enter the recommendation you want to modify:");
        System.out.println("1. Action Lovers");
        System.out.println("2. Romance Enthusiasts");
        int choice = scanner.nextInt();
        scanner.nextLine();

        Recommendation recommendationToModify;
        if (choice == 1) {
            recommendationToModify = actionBooksRecommendation;
        } else if (choice == 2) {
            recommendationToModify = romanceBooksRecommendation;
        } else {
            System.out.println("Invalid choice.");
            return;
        }

        System.out.println("Enter the new target audience:");
        String newAudience = scanner.nextLine();
        recommendationToModify.setTargetAudience(newAudience);

        System.out.println("Enter book title to add:");
        String title = scanner.nextLine();
        System.out.println("Enter author:");
        String author = scanner.nextLine();
        System.out.println("Enter genre:");
        String genre = scanner.nextLine();
        System.out.println("Enter publication year:");
        int year = scanner.nextInt();
        scanner.nextLine();

        Book newBook = new Book(title, author, genre, year);
        recommendationToModify.addBook(newBook);

        System.out.println("Updated recommendation:");
        recommendationToModify.displayRecommendation();
    }
}
