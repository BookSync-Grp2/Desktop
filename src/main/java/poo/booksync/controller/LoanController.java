package poo.booksync.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import poo.booksync.model.Loan;

import java.util.Date;

public class LoanController {
    @FXML
    private TableView<Loan> tableView;

    @FXML
    private TextField userIdField; // Champ pour saisir l'ID de l'utilisateur
    @FXML
    private TextField bookIdField; // Champ pour saisir l'ID du livre

    private ObservableList<Loan> loans = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        if (tableView != null) {
            this.initLoanTable();
        }
    }

    public void initLoanTable() {
        tableView.getColumns().clear();

        TableColumn<Loan, Integer> loanIdColumn = new TableColumn<>("Loan ID");
        TableColumn<Loan, Integer> userIdColumn = new TableColumn<>("User ID");
        TableColumn<Loan, Integer> bookIdColumn = new TableColumn<>("Book ID");
        TableColumn<Loan, Date> startLoanDate = new TableColumn<>("Start Date");
        TableColumn<Loan, Date> endLoanDate = new TableColumn<>("End Date");
        TableColumn<Loan, Boolean> retrievedColumn = new TableColumn<>("Retrieved");
        TableColumn<Loan, Boolean> returnedColumn = new TableColumn<>("Returned");

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
        loans.addAll(
                new Loan(1, 101, 201, new Date(), new Date(), false, false),
                new Loan(2, 102, 202, new Date(), new Date(), true, false)
        );

        this.tableView.setItems(loans);
    }

    @FXML
    public void handleCreateLoan() {
        // Validation des champs
        if (userIdField.getText().isEmpty() || bookIdField.getText().isEmpty()) {
            return;
        }

        try {
            int userId = Integer.parseInt(userIdField.getText());
            int bookId = Integer.parseInt(bookIdField.getText());

            int newLoanId = Loan.createLoan(bookId, userId);

            loans.add(new Loan(newLoanId, userId, bookId, new Date(), null, false, false));

            userIdField.clear();
            bookIdField.clear();

        }catch (NumberFormatException _){

        }
    }
}
