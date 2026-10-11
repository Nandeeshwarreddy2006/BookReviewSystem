package manager;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import database.Database;
import models.Book;
import models.Review;

public class BookManager {
    private final Scanner scanner;

    public BookManager(Scanner scanner) { this.scanner = scanner; }

    public void addBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine().trim();
        System.out.print("Enter author: ");
        String author = scanner.nextLine().trim();
        if (title.isEmpty() || author.isEmpty()) {
            System.out.println("Title and author cannot be empty.");
            return;
        }
        int id = Database.BOOKS.size() + 1;
        Database.BOOKS.add(new Book(id, title, author));
        System.out.println("Book added successfully. Book ID: " + id);
    }

    public void displayBooks() {
        if (Database.BOOKS.isEmpty()) {
            System.out.println("No books available yet.");
            return;
        }
        for (Book book : Database.BOOKS) {
            System.out.println(book + " | Average rating: " + String.format("%.1f", averageRating(book.getId())) + "/5");
        }
    }

    public void searchBook() {
        System.out.print("Enter all or part of the book title: ");
        String query = scanner.nextLine().trim().toLowerCase();
        boolean found = false;
        for (Book book : Database.BOOKS) {
            if (book.getTitle().toLowerCase().contains(query)) {
                System.out.println(book + " | Average rating: " + String.format("%.1f", averageRating(book.getId())) + "/5");
                found = true;
            }
        }
        if (!found) System.out.println("No matching book found.");
    }

    public void sortBooksAlphabetically() {
        if (Database.BOOKS.isEmpty()) {
            System.out.println("No books available to sort.");
            return;
        }
        List<Book> sorted = new ArrayList<>(Database.BOOKS);
        sorted.sort(Comparator.comparing(Book::getTitle, String.CASE_INSENSITIVE_ORDER));
        System.out.println("Books sorted alphabetically:");
        sorted.forEach(System.out::println);
    }

    public void displayTopRatedBooks() {
        if (Database.BOOKS.isEmpty()) {
            System.out.println("No books available yet.");
            return;
        }
        List<Book> ranked = new ArrayList<>(Database.BOOKS);
        ranked.sort((a, b) -> Double.compare(averageRating(b.getId()), averageRating(a.getId())));
        System.out.println("Top-rated books:");
        ranked.stream().limit(5).forEach(book ->
                System.out.println(book + " | Average rating: " + String.format("%.1f", averageRating(book.getId())) + "/5"));
    }

    public double averageRating(int bookId) {
        List<Review> matching = new ArrayList<>();
        for (Review review : Database.REVIEWS) {
            if (review.getBookId() == bookId) matching.add(review);
        }
        if (matching.isEmpty()) return 0.0;
        int total = matching.stream().mapToInt(Review::getRating).sum();
        return (double) total / matching.size();
    }

    public Book findBookById(int id) {
        for (Book book : Database.BOOKS) if (book.getId() == id) return book;
        return null;
    }
}
