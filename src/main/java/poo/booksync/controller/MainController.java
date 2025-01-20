package poo.booksync.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class MainController {

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab bookTab;

    @FXML
    private Tab createBookTab;

    @FXML
    private Tab loansTab;


    @FXML
    private Tab clientsTab;

    @FXML
    private Tab createLoansTab;


    @FXML
    public void initialize() {
        tabPane.getTabs().remove(loansTab);
        tabPane.getTabs().remove(clientsTab);
        tabPane.getTabs().remove(createLoansTab);
    }

    public void initLoansTab(){
        tabPane.getTabs().add(loansTab);
        tabPane.getTabs().add(createLoansTab);
        tabPane.getTabs().remove(clientsTab);
    }
}
