package poo.booksync.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import poo.booksync.MainApplication;
import poo.booksync.model.Loan;

import java.io.IOException;
import java.util.Date;

public class TableLoanController {
    @FXML
    private TableView<Loan> tableView;

    @FXML
    public void initialize() throws IOException, InterruptedException {
        if (tableView != null) {
            this.initLoanTable();
        }
    }

    public void initLoanTable() throws IOException, InterruptedException {
        tableView.getColumns().clear();
        Loan.initializeLoanList();

        TableColumn<Loan, Integer> loanIdColumn = new TableColumn<>("Loan ID");
        TableColumn<Loan, Integer> userIdColumn = new TableColumn<>("User ID");
        TableColumn<Loan, Integer> bookIdColumn = new TableColumn<>("Book ID");
        TableColumn<Loan, Date> startLoanDate = new TableColumn<>("Start Date");
        TableColumn<Loan, Date> endLoanDate = new TableColumn<>("End Date");
        TableColumn<Loan, Boolean> retrievedColumn = new TableColumn<>("Retrieved");
        TableColumn<Loan, Boolean> returnedColumn = new TableColumn<>("Returned");
        TableColumn<Loan, Void> actionColumn = new TableColumn<>("Actions");

        loanIdColumn.setCellValueFactory(new PropertyValueFactory<>("loanId"));
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        bookIdColumn.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        startLoanDate.setCellValueFactory(new PropertyValueFactory<>("loanStartDate"));
        endLoanDate.setCellValueFactory(new PropertyValueFactory<>("loanEndDate"));
        retrievedColumn.setCellValueFactory(new PropertyValueFactory<>("retrieved"));
        returnedColumn.setCellValueFactory(new PropertyValueFactory<>("returned"));

        actionColumn.setCellFactory(param -> new TableCell<>() {
            private final Button retrieveButton = new Button("Retrieve");
            private final Button returnButton = new Button("Return");
            private final HBox buttonBox = new HBox(5, retrieveButton, returnButton);

            {
                retrieveButton.setOnAction(event -> {
                    Loan loan = getTableView().getItems().get(getIndex());
                    try {
                        handleRetrieve(loan,event);
                    } catch (IOException | InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });

                returnButton.setOnAction(event -> {
                    Loan loan = getTableView().getItems().get(getIndex());
                    try {
                        handleReturn(loan,event);
                    } catch (IOException | InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(buttonBox);
                }
            }
        });

        tableView.getColumns().addAll(
                loanIdColumn, userIdColumn, bookIdColumn, startLoanDate,
                endLoanDate, retrievedColumn, returnedColumn, actionColumn
        );

        ObservableList<Loan> loans = FXCollections.observableArrayList(Loan.getLoanList());
        this.tableView.setItems(loans);
    }

    private void handleRetrieve(Loan loan, ActionEvent event) throws IOException, InterruptedException {
        Loan.bookRetrieved(loan.getLoanId());
        MainApplication.redirectTo("main",event);
    }

    private void handleReturn(Loan loan,ActionEvent event) throws IOException, InterruptedException {
        Loan.bookReturned(loan.getLoanId());
        MainApplication.redirectTo("main",event);
    }


}