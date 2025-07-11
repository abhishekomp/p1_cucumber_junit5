package org.example.stepdefs;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LibraryStepDefs {

    private final Map<String, Genre> bookGenres = new HashMap<>();
    private final List<Book> libraryBooks = new ArrayList<>();
    private int bookCount = 0;
    private Book foundBook;
    private Exception searchException;
    private List<Book> foundBooks = new ArrayList<>();

    @When("I add a book with title {string} and genre {genre}")
    public void i_add_a_book_with_title_and_genre_science_fiction(String title, Genre genre) {
        // Write code here that turns the phrase above into concrete actions.
        // For example, you might want to add the book to a library or database
        // This is a placeholder implementation
        //System.out.format("Adding book with title '%s' and genre '%s'.%n", string, genre(string));
        System.out.printf("[LOG] Adding: %s [%s]%n", title, genre);
        bookGenres.put(title, genre);
        // You can implement the actual logic to add the book here
    }

    @Then("the genre of book {string} should be {genre}")
    public void the_genre_of_book_should_be(String title, Genre expectedGenre) {
        System.out.printf("[LOG] Checking: %s ?= %s%n", bookGenres.get(title), expectedGenre);
        // Write code here that turns the phrase above into concrete actions.
        // For example, you might want to check if the book's expectedGenre matches the expected expectedGenre
        assertEquals(expectedGenre, bookGenres.get(title),
                String.format("Expected expectedGenre for book '%s' to be '%s', but found '%s'.", title, expectedGenre, bookGenres.get(title)));
    }

    @Given("the following books are available:")
    public void the_following_books_are_available(List<Book> books) {
        books.forEach(book -> {
            System.out.printf("[LOG] Adding book: %s by %s, Genre: %s, Published on: %s%n",
                    book.getTitle(), book.getAuthor(), book.getGenre(), book.getPublishDate());
            libraryBooks.add(book);
        });
    }
    @When("I count the books")
    public void i_count_the_books() {
        System.out.printf("[LOG] Counting books in the library: %d books available.%n", libraryBooks.size());
        bookCount = libraryBooks.size();

    }
    @Then("the total should be {int}")
    public void the_total_should_be(Integer expectedCount) {
        System.out.printf("[LOG] Expected count: %d, Actual count: %d%n", expectedCount, bookCount);
//        if (expectedCount != bookCount) {
//            throw new AssertionError(String.format("Expected %d books, but found %d.", expectedCount, bookCount));
//        }
        assertEquals(expectedCount, bookCount,
                String.format("Expected %d books, but found %d.", expectedCount, bookCount));
    }

    @When("I search for a book with title {string}")
    public void i_search_for_a_book_with_title(String bookTitle) {
        System.out.printf("[LOG] Searching for book with title: %s%n", bookTitle);
        // You can implement the actual logic to search the book here
//        boolean found = libraryBooks.stream()
//                .anyMatch(book -> book.getTitle().equalsIgnoreCase(bookTitle));
        //System.out.println("Number of books in library: " + libraryBooks.size());
        try {
            foundBook = libraryBooks.stream()
                    .filter(book -> book.getTitle().equalsIgnoreCase(bookTitle))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Book with title " + bookTitle + " not found."));
        } catch (RuntimeException e) {
            searchException = e;
        }
    }

    @Then("the book should be found with title {string} and author {string}")
    public void the_book_should_be_found_with_title_and_author(String expectedTitle, String expectedAuthor) {
        if(foundBook == null) {
            throw new AssertionError("No book was found in the previous step.");
        }
        System.out.printf("[LOG] Found book: %s by %s%n", foundBook.getTitle(), foundBook.getAuthor());
        assertEquals(expectedTitle, foundBook.getTitle(),
                String.format("Expected book title '%s', but found '%s'.", expectedTitle, foundBook.getTitle()));
        assertEquals(expectedAuthor, foundBook.getAuthor(),
                String.format("Expected book author '%s', but found '%s'.", expectedAuthor, foundBook.getAuthor()));
    }

    @Then("a book not found error should be thrown for {string}")
    public void a_book_not_found_error_should_be_thrown_for(String bookTitle) {
        if (searchException == null) {
            throw new AssertionError("Expected a book not found error, but none was thrown.");
        }
        if (!(searchException instanceof RuntimeException)) {
            throw new AssertionError("Expected a RuntimeException, but got: " + searchException.getClass().getSimpleName());
        }
        //String expectedMessage = "Book with title " + bookTitle + " not found.";
        if (!searchException.getMessage().contains(bookTitle)) {
            throw new AssertionError(
                    String.format("Expected exception message to contain '%s', but was '%s'.", bookTitle, searchException.getMessage())
            );
        }
    }

    //@Then("the library should contain the following {int} titles:")
    @Then("the library should contain the following {int} titles:")
    public void the_library_should_contain_the_following_titles(Integer expectedCount, DataTable dataTable) {
        List<String> expectedTitles = dataTable.asList();
        List<String> actualTitles = libraryBooks.stream()
                .map(Book::getTitle)
                .toList();
        assertEquals(expectedCount, expectedTitles.size(),
                String.format("Expected %d titles in the table, but got %d.", expectedCount, expectedTitles.size()));
        assertEquals(expectedTitles, actualTitles,
                String.format("Expected titles %s, but found %s.", expectedTitles, actualTitles));
    }

    @When("I search for books with genre {genre}")
    public void i_search_for_books_with_genre(Genre genre) {
        // Filter books by genre and store the result for assertion
        foundBooks = libraryBooks.stream()
                .filter(book -> book.getGenre() == genre)
                .toList();
    }

    @Then("the following titles should be found:")
    public void the_following_titles_should_be_found(List<String> expectedTitles) {
        List<String> actualTitles = foundBooks.stream()
                .map(Book::getTitle)
                .toList();
        // Remove empty expected titles (from empty cells in the Examples table)
        List<String> filteredExpectedTitles = expectedTitles.stream()
                .filter(title -> title != null && !title.isBlank())
                .toList();
        assertEquals(filteredExpectedTitles, actualTitles,
                String.format("Expected titles %s, but found %s.", filteredExpectedTitles, actualTitles));
    }
}
