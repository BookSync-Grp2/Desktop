package poo.booksync.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import poo.booksync.model.Loans;

import java.util.Date;

public class LoansController {
    @FXML
    private TableView<Loans> tableView;

    @FXML
    public void initialize() {
        this.initLoanTable();
    }

    public void initLoanTable() {
        tableView.getColumns().clear();

        TableColumn<Loans, Integer> loanIdColumn = new TableColumn<>("Loan ID");
        TableColumn<Loans, Integer> userIdColumn = new TableColumn<>("User ID");
        TableColumn<Loans, Integer> bookIdColumn = new TableColumn<>("Book ID");
        TableColumn<Loans, Date> startLoanDate = new TableColumn<>("Start Date");
        TableColumn<Loans, Date> endLoanDate = new TableColumn<>("End Date");
        TableColumn<Loans, Boolean> retrievedColumn = new TableColumn<>("Retrieved");
        TableColumn<Loans, Boolean> returnedColumn = new TableColumn<>("Returned");

        loanIdColumn.setCellValueFactory(new PropertyValueFactory<>("loanId"));
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        bookIdColumn.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        startLoanDate.setCellValueFactory(new PropertyValueFactory<>("loanStartDate"));
        endLoanDate.setCellValueFactory(new PropertyValueFactory<>("loanEndDate"));
        retrievedColumn.setCellValueFactory(new PropertyValueFactory<>("retrieved"));
        returnedColumn.setCellValueFactory(new PropertyValueFactory<>("returned"));

        tableView.getColumns().addAll(
                loanIdColumn, userIdColumn, bookIdColumn, startLoanDate, endLoanDate, retrievedColumn, returnedColumn
        );

        // Exemple de données pour les tests
        ObservableList<Loans> loans = FXCollections.observableArrayList(
                new Loans(1, 101, 201, new Date(), new Date(), false, false),
                new Loans(2, 102, 202, new Date(), new Date(), true, false)
        );

        this.tableView.setItems(loans);
    }
}
