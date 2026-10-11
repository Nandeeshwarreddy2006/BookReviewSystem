# Book Review System

A Java console application for managing users, books, and book reviews. This repository also includes a responsive website demo for showcasing the project.

## Features
- Register and display users
- Add and display books
- Search books by title
- Sort books alphabetically
- Display top-rated books
- Add, display, and delete reviews
- Responsive web demo in `website/`

## Requirements
- Java JDK 17 or later (Java 8+ may work with minor adjustments)
- VS Code, IntelliJ IDEA, Eclipse, or a terminal

## Run the Java application
The source is in `java/src/`. From the repository root, compile the files:

```bash
javac -d out java/src/models/*.java java/src/database/*.java java/src/manager/*.java java/src/Main.java
java -cp out Main
```

The console application stores data in memory for the current run; it does not yet use persistent database storage.

## Run the website demo
Open `website/index.html` in a browser, or enable GitHub Pages:
1. Push this repository to GitHub.
2. Open **Settings → Pages**.
3. Under **Build and deployment**, choose **Deploy from a branch**.
4. Choose branch `main` and folder `/ (root)` if you want the root page, or move/copy the contents of `website/` into the root first.
5. Save and wait for the Pages URL to appear.

The website is a front-end demonstration using browser local storage; it is not connected to the Java console application or a server/database.

## Suggested GitHub description
Java-based Book Review System using OOP and DSA, with book search, alphabetical sorting, ratings, and review management.

## Project structure
```text
BookReviewSystem/
├── java/
│   └── src/
│       ├── Main.java
│       ├── models/
│       ├── manager/
│       └── database/
├── website/
│   ├── index.html
│   ├── style.css
│   └── script.js
└── README.md
```

## Important note
The Java implementation is a cleaned-up, self-contained version based on the features described in the project report. The report mentions a `Database.java` class, but its implementation was not included in the submitted report pages, so this starter uses in-memory collections instead.
