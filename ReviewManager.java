package manager;

import java.util.Scanner;
import database.Database;
import models.Book;
import models.Review;
import models.User;

public class ReviewManager {
    private final Scanner scanner;
    private final BookManager bookManager;

    public ReviewManager(Scanner scanner, BookManager bookManager) {
        this.scanner = scanner;
        this.bookManager = bookManager;
    }

    public void addReview() {
        if (Database.USERS.isEmpty()) {
            System.out.println("Register a user before adding a review.");
            return;
        }
        if (Database.BOOKS.isEmpty()) {
            System.out.println("Add a book before adding a review.");
            return;
        }

        System.out.println("Users:");
        Database.USERS.forEach(System.out::println);
        System.out.print("Enter user ID: ");
        int userId = readInt();
        User user = findUser(userId);
        if (user == null) {
            System.out.println("User ID not found.");
            return;
        }

        System.out.println("Books:");
        Database.BOOKS.forEach(System.out::println);
        System.out.print("Enter book ID: ");
        int bookId = readInt();
        Book book = bookManager.findBookById(bookId);
        if (book == null) {
            System.out.println("Book ID not found.");
            return;
        }

        System.out.print("Enter rating (1-5): ");
        int rating = readInt();
        if (rating < 1 || rating > 5) {
            System.out.println("Rating must be between 1 and 5.");
            return;
        }
        System.out.print("Enter review comment: ");
        String comment = scanner.nextLine().trim();
        if (comment.isEmpty()) {
            System.out.println("Review comment cannot be empty.");
            return;
        }

        int id = Database.REVIEWS.size() + 1;
        Database.REVIEWS.add(new Review(id, userId, bookId, rating, comment));
        System.out.println("Review added successfully. Review ID: " + id);
    }

    public void displayReviews() {
        if (Database.REVIEWS.isEmpty()) {
            System.out.println("No reviews available yet.");
            return;
        }
        Database.REVIEWS.forEach(System.out::println);
    }

    public void deleteReview() {
        if (Database.REVIEWS.isEmpty()) {
            System.out.println("No reviews available to delete.");
            return;
        }
        displayReviews();
        System.out.print("Enter review ID to delete: ");
        int id = readInt();
        boolean removed = Database.REVIEWS.removeIf(review -> review.getId() == id);
        System.out.println(removed ? "Review deleted successfully." : "Review ID not found.");
    }

    private User findUser(int id) {
        for (User user : Database.USERS) if (user.getId() == id) return user;
        return null;
    }

    private int readInt() {
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a whole number: ");
            scanner.nextLine();
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }
}
