Feature: Search books by genre

  Background:
    Given the following books are available:
      | title      | author        | genre           | publishDate |
      | Dune       | Frank Herbert | SCIENCE_FICTION | 12-Jan-1987 |
      | The Hobbit | Tolkien       | FANTASY         | 10-Jun-1976 |
      | Hamlet     | Shakespeare   | DRAMA           | 01-Jan-1603 |
      | 1984       | George Orwell | FICTION         | 08-Jun-1949 |

  Scenario Outline: Find books by genre
    When I search for books with genre <genre>
    Then the following titles should be found:
      | <title1> |
      | <title2> |

    Examples:
      | genre           | title1      | title2      |
      | SCIENCE_FICTION | Dune        |             |
      | FANTASY         | The Hobbit  |             |
      | DRAMA           | Hamlet      |             |
      | FICTION         | 1984        |             |