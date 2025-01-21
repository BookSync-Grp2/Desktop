package poo.booksync.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginDto extends BaseDto {
    private final String email;
    private final String password;

    // Constructeur
    public LoginDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("password")
    public String getPassword() {
        return password;
    }
}
