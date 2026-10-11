package models;

public class Review {
    private final int id;
    private final int userId;
    private final int bookId;
    private final int rating;
    private final String comment;

    public Review(int id, int userId, int bookId, int rating, String comment) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.rating = rating;
        this.comment = comment;
    }

    public int getId() { return id; }
    public int getUserId() { return userId; }
    public int getBookId() { return bookId; }
    public int getRating() { return rating; }
    public String getComment() { return comment; }

    @Override
    public String toString() {
        return "Review #" + id + " | User ID: " + userId + " | Book ID: " + bookId
                + " | Rating: " + rating + "/5 | " + comment;
    }
}
