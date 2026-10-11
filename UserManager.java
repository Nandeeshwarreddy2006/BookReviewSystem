package manager;

import java.util.Scanner;
import database.Database;
import models.User;

public class UserManager {
    private final Scanner scanner;

    public UserManager(Scanner scanner) { this.scanner = scanner; }

    public void registerUser() {
        System.out.print("Enter user name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter email: ");
        String email = scanner.nextLine().trim();

        if (name.isEmpty() || email.isEmpty()) {
            System.out.println("Name and email cannot be empty.");
            return;
        }
        int id = Database.USERS.size() + 1;
        Database.USERS.add(new User(id, name, email));
        System.out.println("User registered successfully. User ID: " + id);
    }

    public void displayUsers() {
        if (Database.USERS.isEmpty()) {
            System.out.println("No users registered yet.");
            return;
        }
        Database.USERS.forEach(System.out::println);
    }
}
