package poo.booksync.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import poo.booksync.MainApplication;
import poo.booksync.model.User;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void initialize() {}

    @FXML
    private void handleLogin() {
        User.login(emailField.getText(), passwordField.getText());
    }

    @FXML
    private void redirectToRegister(ActionEvent event) throws IOException {
        MainApplication.redirectTo("register",event);
    }

}
