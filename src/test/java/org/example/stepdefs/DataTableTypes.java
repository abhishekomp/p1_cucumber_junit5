package org.example.stepdefs;

import io.cucumber.java.DataTableType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class DataTableTypes {
/*    @DataTableType
    public Book bookEntry(Map<String, String> entry) {
        System.out.printf("[LOG] @DataTableType called with: %s%n", entry);
        String title = entry.get("title");
        String author = entry.get("author");
        Genre genre = Genre.valueOf(entry.get("genre").toUpperCase());
        String publishDate = entry.get("publishDate");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        return new Book(title, author, genre, LocalDate.parse(publishDate, formatter));
    }*/

    @DataTableType
    public Book bookEntry(Map<String, String> entry) {
        System.out.printf("[LOG] @DataTableType called with: %s%n", entry);

        String title = entry.get("title");
        String author = entry.get("author");
        String genreStr = entry.get("genre");
        String publishDateStr = entry.get("publishDate");

        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Book entry is missing a 'title'.");
        }
        if (author == null || author.isBlank()) {
            throw new IllegalArgumentException("Book entry is missing an 'author'.");
        }
        if (genreStr == null || genreStr.isBlank()) {
            throw new IllegalArgumentException("Book entry is missing a 'genre'.");
        }
        Genre genre;
        try {
            genre = Genre.valueOf(genreStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid genre: " + genreStr, e);
        }
        if (publishDateStr == null || publishDateStr.isBlank()) {
            throw new IllegalArgumentException("Book entry is missing a 'publishDate'.");
        }
        LocalDate publishDate;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
            publishDate = LocalDate.parse(publishDateStr, formatter);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid publishDate: " + publishDateStr, e);
        }

        return new Book(title, author, genre, publishDate);
    }
}
