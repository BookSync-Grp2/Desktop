package poo.booksync.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private void initialize() {
        System.out.println("Initialisation du contrôleur de login");
    }

    @FXML
    private void handleLogin() {
        String email = this.emailField.getText();
        String password = this.passwordField.getText();
        System.out.println("Email saisi : " + email);
        System.out.println("Mot de passe saisi : " + password);
    }
}
