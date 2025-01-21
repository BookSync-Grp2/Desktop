package poo.booksync.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import poo.booksync.MainApplication;
import poo.booksync.model.User;
import poo.booksync.model.dto.UserRegistrationDto;
import poo.booksync.validators.EmailValidator;
import poo.booksync.validators.FieldValidator;
import poo.booksync.validators.PasswordValidator;

import java.io.IOException;

public class RegisterController {

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField passwordField;

    @FXML
    private void initialize() {
        emailField.textProperty().addListener((observable, oldValue, newValue) -> {
            FieldValidator.validate(
                    emailField,
                    new EmailValidator(),
                    newValue
            );
        });

        passwordField.textProperty().addListener((observable, oldValue, newValue) -> {
            FieldValidator.validate(
                    passwordField,
                    new PasswordValidator(),
                    newValue
            );
        });
    }

    @FXML
    private void handleRegister() throws IOException, InterruptedException {
        User.register(
                new UserRegistrationDto(
                    firstNameField.getText(),
                    lastNameField.getText(),
                    emailField.getText(),
                    passwordField.getText()
                )
        );
    }

    @FXML
    private void redirectToLogin(ActionEvent event) throws IOException {
        MainApplication.redirectTo("login", event);
    }
}
