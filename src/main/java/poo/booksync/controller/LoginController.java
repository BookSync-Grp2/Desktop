package poo.booksync.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import poo.booksync.validators.EmailValidator;
import poo.booksync.validators.FieldValidator;
import poo.booksync.validators.PasswordValidator;

public class LoginController {
    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void initialize() {}

    @FXML
    private void handleLogin() {
        String email = this.emailField.getText();
        String password = this.passwordField.getText();
        //TODO: Implémenter la logique de connexion avec l'API
    }
}
