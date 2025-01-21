package poo.booksync.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import poo.booksync.model.Loan;

import java.util.Date;

public class TableLoanController {
    @FXML
    private TableView<Loan> tableView;

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

        ObservableList<Loan> loans = FXCollections.observableArrayList(Loan.getLoanList());

        this.tableView.setItems(loans);
    }
}
