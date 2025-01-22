package poo.booksync.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import poo.booksync.model.Client;
import poo.booksync.model.RoleType;
import poo.booksync.model.User;

import java.io.IOException;
import java.util.Date;

public class TableClientController {
    @FXML
    private TableView<User> tableView;

    @FXML
    private TableColumn<User, Integer> userIdColumn;
    @FXML
    private TableColumn<User, String> firstNameColumn;
    @FXML
    private TableColumn<User, String> lastNameColumn;
    @FXML
    private TableColumn<User, String> emailColumn;
    @FXML
    private TableColumn<User, Date> dateJoinedColumn;
    @FXML
    private TableColumn<User, Boolean> validatedColumn;
    @FXML
    private TableColumn<User, RoleType> roleColumn;

    @FXML
    public void initialize() throws IOException, InterruptedException {
        User.initializeUserList();
        // Configure les colonnes
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        dateJoinedColumn.setCellValueFactory(new PropertyValueFactory<>("dateJoined"));
        validatedColumn.setCellValueFactory(new PropertyValueFactory<>("validated"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));

        ObservableList<User> users = FXCollections.observableArrayList(Client.getUserList());

        this.tableView.setItems(users);

    }
}
