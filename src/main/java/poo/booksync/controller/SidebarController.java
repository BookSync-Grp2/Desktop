package poo.booksync.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import poo.booksync.MainApplication;

import java.io.IOException;

public class SidebarController {

    private String currentView;

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
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        if (event.getSource() == this.homeButton) {
            System.out.println("Home button pressed");
            MainApplication.redirectTo("home",event);
        } else if (event.getSource() == loanButton) {
            System.out.println("Loan button pressed");
            MainApplication.redirectTo("loan",event);
        } else if (event.getSource() == clientButton) {
            navigateTo("ClientView.fxml");
        } else if (event.getSource() == logoutButton) {
            logout();
        }
    }

    /**
     * Méthode pour naviguer vers une vue spécifique.
     *
     * @param fxmlFile le nom du fichier FXML de la vue
     */
    private void navigateTo(String fxmlFile) {
        try {
            // Charger le fichier FXML et effectuer la navigation
            System.out.println("Navigation vers : " + fxmlFile);
            // Ajouter ici le code pour charger la vue souhaitée, comme :
            // FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            // Parent root = loader.load();
            // Scene currentScene = accueil_button.getScene();
            // currentScene.setRoot(root);
        } catch (Exception e) {
            System.err.println("Erreur de navigation : " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Méthode pour gérer la déconnexion.
     */
    private void logout() {
        System.out.println("Déconnexion en cours...");
        // Ajouter ici la logique pour déconnecter l'utilisateur, par exemple :
        // fermer la session, retourner à l'écran de connexion, etc.
    }

    public void setCurrentView(String view) {
        this.currentView = view;
    }
}
