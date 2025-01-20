package poo.booksync.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import poo.booksync.model.Book;

import java.util.Date;

public class BookController {
    @FXML
    private TableView<Book> tableView;

    @FXML private TextField titleField;
    @FXML private TextField isbnField;
    @FXML private TextField authorField;
    @FXML private DatePicker publishDatePicker;

    @FXML
    public void initialize() {
        if (tableView != null) {
            initBookTable();
        }
    }

    public void  initBookTable(){
        tableView.getColumns().clear();

        TableColumn<Book, Integer> idColumn = new TableColumn<>("ID");
        TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
        TableColumn<Book, String> authorColumn = new TableColumn<>("Author");
        TableColumn<Book, Date> publishedYearColumn = new TableColumn<>("Published Year");

        idColumn.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        publishedYearColumn.setCellValueFactory(new PropertyValueFactory<>("publishedYear"));

        tableView.getColumns().addAll(idColumn, titleColumn, authorColumn, publishedYearColumn);

        ObservableList<Book> books = FXCollections.observableArrayList(
                new Book(1, "The Great Gatsby", "F. Scott Fitzgerald", "978-0743273565", 1925, true),
                new Book(2, "To Kill a Mockingbird", "Harper Lee", "978-0446310789", 1945, false)
        );

        this.tableView.setItems(books);
    }

    public void handleCreateBook(){
        Book.createBook(
                titleField.getText(),
                authorField.getText(),
                isbnField.getText(),
                publishDatePicker.getValue().getYear()
        );
        clearField();
    }

    public void clearField(){
        titleField.clear();
        authorField.clear();
        isbnField.clear();
        publishDatePicker.setValue(null);
    }
}
