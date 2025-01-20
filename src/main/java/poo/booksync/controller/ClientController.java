package poo.booksync.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import poo.booksync.model.Client;
import poo.booksync.model.RoleType;

import java.util.Date;

public class ClientController {
    @FXML
    private TableView<Client> tableView;

    @FXML
    private TableColumn<Client, Integer> userIdColumn;
    @FXML
    private TableColumn<Client, String> firstNameColumn;
    @FXML
    private TableColumn<Client, String> lastNameColumn;
    @FXML
    private TableColumn<Client, String> emailColumn;
    @FXML
    private TableColumn<Client, Date> dateJoinedColumn;
    @FXML
    private TableColumn<Client, Boolean> validatedColumn;
    @FXML
    private TableColumn<Client, RoleType> roleColumn;

    private final ObservableList<Client> clientList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Configure les colonnes
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        dateJoinedColumn.setCellValueFactory(new PropertyValueFactory<>("dateJoined"));
        validatedColumn.setCellValueFactory(new PropertyValueFactory<>("validated"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));

        // Remplit la table avec des données factices
        loadTestData();

        // Associe la liste au TableView
        tableView.setItems(clientList);
    }

    private void loadTestData() {
        clientList.add(new Client(1, "John", "Doe", "john.doe@example.com", new Date(), true, RoleType.ADMIN));
        clientList.add(new Client(2, "Jane", "Smith", "jane.smith@example.com", new Date(), false, RoleType.USER));
        clientList.add(new Client(3, "Alice", "Johnson", "alice.johnson@example.com", new Date(), true, RoleType.USER));
    }
}
