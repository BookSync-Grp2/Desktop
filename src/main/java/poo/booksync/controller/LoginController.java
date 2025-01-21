package poo.booksync.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import poo.booksync.MainApplication;
import poo.booksync.model.User;
import poo.booksync.model.dto.LoginDto;
import java.io.IOException;

public class LoginController {
    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void initialize() {}

    @FXML
    private void handleLogin(ActionEvent event) throws IOException, InterruptedException {
        User.login(
                new LoginDto(
                        emailField.getText(),
                        passwordField.getText()
                )
        );
        MainApplication.redirectTo("main",event);
    }

    @FXML
    private void redirectToRegister(ActionEvent event) throws IOException {
        MainApplication.redirectTo("register",event);
    }

}
