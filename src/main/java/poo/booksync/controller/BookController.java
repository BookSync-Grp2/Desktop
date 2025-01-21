package poo.booksync.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import poo.booksync.model.Book;
import poo.booksync.model.dto.CreateBookDto;

import java.io.IOException;
import java.util.Date;

public class BookController {
    @FXML
    private TableView<Book> tableView;

    @FXML private TextField titleField;
    @FXML private TextField isbnField;
    @FXML private TextField authorField;
    @FXML private DatePicker publishDatePicker;

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

    public void handleCreateBook() throws IOException, InterruptedException {
        // Créer un livre
        Book.createBook(
                new CreateBookDto(
                        titleField.getText(),
                        authorField.getText(),
                        isbnField.getText(),
                        publishDatePicker.getValue().getYear()
                )
        );

        // Effacer les champs
        clearField();

        Book.initializeBookList();  // Assurez-vous que les livres sont mis à jour
        ObservableList<Book> books = FXCollections.observableArrayList(Book.getBookList());
        tableView.setItems(books);  // Mettre à jour les données de la TableView
        tableView.refresh();  // Forcer la table à se rafraîchir pour afficher les nouveaux livres
    }

    public void clearField(){
        titleField.clear();
        authorField.clear();
        isbnField.clear();
        publishDatePicker.setValue(null);
    }

}
