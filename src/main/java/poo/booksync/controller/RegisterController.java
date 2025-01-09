package poo.booksync.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

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
        System.out.println("Initialisation du contrôleur d'enregistrement");
    }

    @FXML
    private void handleRegister() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = this.emailField.getText();
        String password = this.passwordField.getText();
        System.out.println("Valeurs saisie dans le formulaire d'enregistrement"+" "+firstName + " " + lastName + " " + email + " " + password);
    }
}
