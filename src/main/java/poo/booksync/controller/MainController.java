package poo.booksync.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import poo.booksync.MainApplication;
import poo.booksync.model.User;
import poo.booksync.model.View;

import java.io.IOException;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MainController {

    private View currentView = View.HOME;
    private final Map<View, List<Tab>> viewTabs = new EnumMap<>(View.class);

    @FXML private TabPane tabPane;
    @FXML private Tab bookTab;
    @FXML private Tab createBookTab;
    @FXML private Tab loansTab;
    @FXML private Tab clientsTab;
    @FXML private Tab createLoansTab;
    @FXML private Button homeButton;
    @FXML private Button loanButton;
    @FXML private Button clientButton;
    @FXML private Button logoutButton;

    /**
     * Initialise le contrôleur et configure les associations entre vues et onglets
     */
    @FXML
    public void initialize() {
        initializeViewTabs();
        switchToView(View.HOME);
    }

    /**
     * Initialise la map des onglets associés à chaque vue
     */
    private void initializeViewTabs() {
        viewTabs.put(View.HOME, List.of(bookTab, createBookTab));
        viewTabs.put(View.LOANS, List.of(loansTab, createLoansTab));
        viewTabs.put(View.CLIENT, List.of(clientsTab));
    }

    /**
     * Gère les actions des boutons de navigation
     * @param event L'événement de clic
     * @throws IOException Si une erreur survient lors de la redirection
     */
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        Button source = (Button) event.getSource();

        if (source == homeButton && currentView != View.HOME) {
            switchToView(View.HOME);
        } else if (source == loanButton && currentView != View.LOANS) {
            switchToView(View.LOANS);
        } else if (source == clientButton && currentView != View.CLIENT) {
            switchToView(View.CLIENT);
        } else if (source == logoutButton) {
            handleLogout(event);
        }
    }

    /**
     * Gère la déconnexion de l'utilisateur
     * @param event L'événement de clic
     * @throws IOException Si une erreur survient lors de la redirection
     */
    private void handleLogout(ActionEvent event) throws IOException {
        User.logout();
        MainApplication.redirectTo("login", event);
    }

    /**
     * Change la vue courante et met à jour les onglets affichés
     * @param newView La nouvelle vue à afficher
     */
    public void switchToView(View newView) {
        Objects.requireNonNull(newView, "La vue ne peut pas être null");

        removeAllTabs();
        List<Tab> tabs = viewTabs.get(newView);
        if (tabs != null) {
            tabPane.getTabs().addAll(tabs);
        }
        currentView = newView;
    }

    /**
     * Supprime tous les onglets du TabPane
     */
    private void removeAllTabs() {
        tabPane.getTabs().clear();
    }
}