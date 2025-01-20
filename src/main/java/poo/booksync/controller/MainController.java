package poo.booksync.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import poo.booksync.model.Book;

import java.util.Date;

public class MainController {
    @FXML
    private TableView<Book> tableView;

    @FXML
    public void initialize() {
        this.initBookTable();
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
                new Book(1, "The Great Gatsby", "F. Scott Fitzgerald", "978-0743273565", new Date(-1420070400000L), false),
                new Book(2, "To Kill a Mockingbird", "Harper Lee", "978-0446310789", new Date(-1420070400000L), false)
        );

        this.tableView.setItems(books);
    }
}