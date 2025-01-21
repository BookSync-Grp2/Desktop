package poo.booksync.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import poo.booksync.model.Book;

import java.io.IOException;
import java.util.Date;

public class TableBookController {

    @FXML
    private TableView<Book> tableView;

    @FXML
    public void initialize() throws IOException, InterruptedException {
        if (tableView != null) {
            initBookTable();
        }
    }

    public void  initBookTable() throws IOException, InterruptedException {
        Book.initializeBookList();
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

        ObservableList<Book> books = FXCollections.observableArrayList(Book.getBookList());

        this.tableView.setItems(books);
    }
}
