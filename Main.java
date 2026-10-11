import java.util.Scanner;
import manager.BookManager;
import manager.ReviewManager;
import manager.UserManager;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserManager users = new UserManager(scanner);
        BookManager books = new BookManager(scanner);
        ReviewManager reviews = new ReviewManager(scanner, books);

        int choice;
        do {
            System.out.println("\n=================================");
            System.out.println("        BOOK REVIEW SYSTEM");
            System.out.println("=================================");
            System.out.println("1. Register User");
            System.out.println("2. Display Users");
            System.out.println("3. Add Book");
            System.out.println("4. Display Books");
            System.out.println("5. Search Book");
            System.out.println("6. Sort Books Alphabetically");
            System.out.println("7. Display Top-Rated Books");
            System.out.println("8. Add Review");
            System.out.println("9. Display Reviews");
            System.out.println("10. Delete Review");
            System.out.println("11. Exit");
            System.out.print("Enter your choice: ");

            while (!scanner.hasNextInt()) {
                System.out.print("Please enter a number from 1 to 11: ");
                scanner.nextLine();
            }
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> users.registerUser();
                case 2 -> users.displayUsers();
                case 3 -> books.addBook();
                case 4 -> books.displayBooks();
                case 5 -> books.searchBook();
                case 6 -> books.sortBooksAlphabetically();
                case 7 -> books.displayTopRatedBooks();
                case 8 -> reviews.addReview();
                case 9 -> reviews.displayReviews();
                case 10 -> reviews.deleteReview();
                case 11 -> System.out.println("Thank you for using Book Review System!");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 11);

        scanner.close();
    }
}
