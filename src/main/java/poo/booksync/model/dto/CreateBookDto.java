package poo.booksync.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateBookDto extends BaseDto {

    private final String title;

    private final String author;

    private final String isbn;

    private final int publishedYear;

    // Constructeur
    public CreateBookDto(String title, String author, String isbn, int publishedYear) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publishedYear = publishedYear;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("author")
    public String getAuthor() {
        return author;
    }

    @JsonProperty("isbn")
    public String getIsbn() {
        return isbn;
    }

    @JsonProperty("publishedYear")
    public int getPublishedYear() {
        return publishedYear;
    }
}
