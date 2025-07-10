Feature: @ParameterType, @DataTableType and other demonstrations

  # Demonstrates the use of @ParameterType for enum values in Cucumber
  Scenario: Add a book with a genre
    When I add a book with title "Dune" and genre SCIENCE_FICTION
    Then the genre of book "Dune" should be SCIENCE_FICTION

  # Demonstrates the use of @DataTableType for converting data tables to objects
  Scenario: Add several books with details
    Given the following books are available:
      | title      | author        | genre           | publishDate |
      | Dune       | Frank Herbert | SCIENCE_FICTION | 12-Jan-1987 |
      | The Hobbit | Tolkien       | FANTASY         | 10-Jun-1976 |
    When I count the books
    Then the total should be 2

  Scenario: Search for a book by title
    Given the following books are available:
      | title      | author        | genre           | publishDate |
      | Dune       | Frank Herbert | SCIENCE_FICTION | 12-Jan-1987 |
      | The Hobbit | Tolkien       | FANTASY         | 10-Jun-1976 |
    When I search for a book with title "Dune"
    Then the book should be found with title "Dune" and author "Frank Herbert"

#  Scenario: Search for a book by author
#    When I search for a book by author "Tolkien"
#    Then the book should be found with title "The Hobbit" and genre "FANTASY"

  Scenario: Search for a non-existing book
    Given the following books are available:
      | title | author        | genre           | publishDate |
      | Dune  | Frank Herbert | SCIENCE_FICTION | 12-Jan-1987 |
    When I search for a book with title "Unknown Book"
    Then a book not found error should be thrown for "Unknown Book"

#  Scenario: Add a book with an invalid genre
#    When I add a book with title "Invalid Book" and genre UNKNOWN_GENRE
#    Then an error should be thrown for invalid genre "UNKNOWN_GENRE"

  Scenario: Verify a list of book titles in the library
    Given the following books are available:
      | title      | author        | genre           | publishDate |
      | Dune       | Frank Herbert | SCIENCE_FICTION | 12-Jan-1987 |
      | The Hobbit | Tolkien       | FANTASY         | 10-Jun-1976 |
      | 1984       | George Orwell | FICTION         | 08-Jun-1949 |
      | Hamlet     | Shakespeare   | DRAMA           | 01-Jan-1603 |
      | Moby Dick  | Melville      | ADVENTURE       | 18-Oct-1851 |
    Then the library should contain the following 5 titles:
      | Dune       |
      | The Hobbit |
      | 1984       |
      | Hamlet     |
      | Moby Dick  |