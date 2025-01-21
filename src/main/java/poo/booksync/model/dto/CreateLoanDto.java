package poo.booksync.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateLoanDto extends BaseDto {

    private final int userId;

    private final int bookId;

    // Constructeur
    public CreateLoanDto(int userId, int bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }

    @JsonProperty("userId")
    public int getUserId() {
        return this.userId;
    }

    @JsonProperty("bookId")
    public int getBookId() {
        return this.bookId;
    }
}
