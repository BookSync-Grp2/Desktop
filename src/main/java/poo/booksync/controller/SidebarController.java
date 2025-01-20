package poo.booksync.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import poo.booksync.MainApplication;
import poo.booksync.model.User;

import java.io.IOException;

public class SidebarController {

    @FXML
    private Button homeButton;

    @FXML
    private Button loanButton;

    @FXML
    private Button clientButton;

    @FXML
    private Button logoutButton;

    /**
     * Méthode appelée lorsqu'un bouton est cliqué.
     *
     * @param event l'événement de clic du bouton
     */
    /**
     * Méthode appelée lorsqu'un bouton est cliqué.
     *
     * @param event l'événement de clic du bouton
     */
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        resetButtonStyles();
        if (event.getSource() == homeButton) {
            MainApplication.redirectTo("home", event);
        } else if (event.getSource() == loanButton) {
            MainApplication.redirectTo("loan", event);
        } else if (event.getSource() == clientButton) {
            MainApplication.redirectTo("client",event);
        } else if (event.getSource() == logoutButton) {
            logout(event);
        }
    }

    /**
     * Réinitialise les styles des boutons à leur état par défaut.
     */
    private void resetButtonStyles() {
        homeButton.getStyleClass().remove("selected-button");
        loanButton.getStyleClass().remove("selected-button");
        clientButton.getStyleClass().remove("selected-button");
        logoutButton.getStyleClass().remove("selected-button");
    }

    /**
     * Méthode pour gérer la déconnexion.
     */
    private void logout(ActionEvent event) throws IOException {
        User.logout();
        MainApplication.redirectTo("login",event);
    }
}
