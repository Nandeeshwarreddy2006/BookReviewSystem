# Book Review System

A Book Review System developed using Java and web technologies. It allows users to manage books, search for titles, and submit ratings and reviews.

## Features

- Register users
- Add and display books
- Search books by title
- Sort books alphabetically
- Display top-rated books
- Add, display, and delete reviews
- Calculate average book ratings
- Responsive BookNest website

## Technologies Used

- Java
- Object-Oriented Programming (OOP)
- HTML
- CSS
- JavaScript
- GitHub Pages

## Java Source Code

The Java application is organized in `java/src/`:

- `Main.java` — Main program and menu
- `models/` — Book, User, and Review classes
- `database/` — Database class
- `manager/` — Book, User, and Review management

## How to Run the Java Application

Install JDK 17 or a compatible version.

Open Command Prompt in the project root and run:

```bash
javac -d out java/src/models/*.java java/src/database/*.java java/src/manager/*.java java/src/Main.java
java -cp out Main
```

## Website

Visit the live website:

https://nandeeshwarreddy2006.github.io/BookReviewSystem/

## Author

G Nandeeshwar Reddy
