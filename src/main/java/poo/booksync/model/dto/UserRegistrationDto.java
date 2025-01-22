package poo.booksync.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserRegistrationDto extends BaseDto {

    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;

    // Constructeur
    public UserRegistrationDto(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    // Getters et setters

    // Constructeur, Getters et Setters
    @JsonProperty("firstName")
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("lastName")
    public String getLastName() {
        return lastName;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("password")
    public String getPassword() {
        return password;
    }

    @JsonProperty("isValidated")
    public boolean getIsValidated() {
        return false;
    }

    @JsonProperty("role")
    public String getRole() {
        return "USER";
    }
}
