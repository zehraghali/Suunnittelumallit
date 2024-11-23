package Prototype;

import java.util.ArrayList;
import java.util.List;

public class Recommendation implements Cloneable {
    private String targetAudience;
    private List<Book> books;

    public Recommendation(String targetAudience) {
        this.targetAudience = targetAudience;
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public String getTargetAudience() {
        return targetAudience;
    }

    public void setTargetAudience(String targetAudience) {
        this.targetAudience = targetAudience;
    }

    @Override
    public Recommendation clone() {
        try {
            Recommendation clonedRecommendation = (Recommendation) super.clone();
            clonedRecommendation.books = new ArrayList<>();
            for (Book book : this.books) {
                clonedRecommendation.books.add(book.clone()); // Deep clone books
            }
            return clonedRecommendation;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void displayRecommendation() {
        System.out.println("Recommendation for " + targetAudience + ":");
        for (Book book : books) {
            System.out.println(book);
        }
    }
}
