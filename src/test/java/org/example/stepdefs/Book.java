package org.example.stepdefs;

import java.time.LocalDate;

public class Book {
    private final String title;
    private final String author;
    private final Genre genre;
    private final LocalDate publishDate;

    public Book(String title, String author, Genre genre, LocalDate publishDate) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publishDate = publishDate;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public Genre getGenre() {
        return genre;
    }
    public LocalDate getPublishDate() {
        return publishDate;
    }
    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre=" + genre +
                ", publishDate=" + publishDate +
                '}';
    }
}
