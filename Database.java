package database;

import java.util.ArrayList;
import java.util.List;
import models.Book;
import models.Review;
import models.User;

/**
 * In-memory data store for the console application.
 * Data is cleared when the application exits.
 */
public final class Database {
    public static final List<User> USERS = new ArrayList<>();
    public static final List<Book> BOOKS = new ArrayList<>();
    public static final List<Review> REVIEWS = new ArrayList<>();

    private Database() { }
}
