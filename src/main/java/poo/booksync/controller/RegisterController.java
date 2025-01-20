package poo.booksync.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import poo.booksync.MainApplication;
import poo.booksync.model.RoleType;
import poo.booksync.model.User;
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
    private void handleRegister() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = this.emailField.getText();
        String password = this.passwordField.getText();
        User.register(firstName,lastName,email,password, RoleType.ADMIN);
    }

    @FXML
    private void redirectToLogin(ActionEvent event) throws IOException {
        MainApplication.redirectTo("home",event);
    }
}
