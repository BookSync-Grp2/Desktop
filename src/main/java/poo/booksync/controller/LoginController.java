package poo.booksync.controller;

import com.sun.tools.javac.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import poo.booksync.MainApplication;
import poo.booksync.model.User;
import poo.booksync.model.dto.LoginDto;
import poo.booksync.utils.Request;
import poo.booksync.utils.TokenExtractor;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
