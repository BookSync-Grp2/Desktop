package poo.booksync.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import poo.booksync.MainApplication;
import poo.booksync.validators.EmailValidator;
import poo.booksync.validators.FieldValidator;
import poo.booksync.validators.PasswordValidator;

import java.io.IOException;

public class LoginController {
    public Hyperlink registerhyperLink;
    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Hyperlink registerHyperLink;

    @FXML
    private void initialize() {}

    @FXML
    private void handleLogin() {
        String email = this.emailField.getText();
        String password = this.passwordField.getText();
        //TODO: Implémenter la logique de connexion avec l'API
    }

    @FXML
    private void redirectToRegister() {
        MainApplication.redirectTo("register");
    }

}
