package poo.booksync.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import poo.booksync.model.Book;
import poo.booksync.model.Loan;
import poo.booksync.model.User;
import poo.booksync.model.dto.CreateLoanDto;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import java.io.IOException;

public class CreateLoanController {

    @FXML
    private ComboBox<User> userComboBox;

    @FXML
    private ComboBox<Book> bookComboBox;

    @FXML
    public void initialize() throws IOException, InterruptedException {
        loadUsers();
        loadBooks();
        initUserComboBox();
        initBookCombobox();
    }


    private  void initUserComboBox(){
        this.userComboBox.setCellFactory(new Callback<>() {
            @Override
            public ListCell<User> call(ListView<User> param) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(User user, boolean empty) {
                        super.updateItem(user, empty);
                        if (user == null || empty) {
                            setText(null);
                        } else {
                            setText(user.getEmail());
                        }
                    }
                };
            }
        });


        userComboBox.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(User user, boolean empty) {
                super.updateItem(user, empty);
                if (user == null || empty) {
                    setText(null);
                } else {
                    setText(user.getEmail());
                }
            }
        });
    }

    private void initBookCombobox(){
        bookComboBox.setCellFactory(new Callback<>() {
            @Override
            public ListCell<Book> call(ListView<Book> param) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(Book book, boolean empty) {
                        super.updateItem(book, empty);
                        if (book == null || empty) {
                            setText(null);
                        } else {
                            setText(book.getTitle());
                        }
                    }
                };
            }
        });

        bookComboBox.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Book book, boolean empty) {
                super.updateItem(book, empty);
                if (book == null || empty) {
                    setText(null);
                } else {
                    setText(book.getTitle());
                }
            }
        });
    }

    /**
     * Charge les utilisateurs dans la ComboBox
     */
    private void loadUsers() throws IOException, InterruptedException {
        User.initializeUserList();
        ObservableList<User> users = FXCollections.observableArrayList(User.getUserList());
        userComboBox.setItems(users);
    }

    /**
     * Charge les livres dans la ComboBox
     */
    private void loadBooks() {
        ObservableList<Book> books = FXCollections.observableArrayList(Book.getBookList());
        bookComboBox.setItems(books);
    }

    /**
     * Gère l'enregistrement du prêt
     */
    @FXML
    private void handleSaveLoan() throws IOException, InterruptedException {
        User selectedUser = userComboBox.getSelectionModel().getSelectedItem();
        Book selectedBook = bookComboBox.getSelectionModel().getSelectedItem();

        int userId = selectedUser.getUserId();
        int bookId = selectedBook.getBookId();

        saveLoan(
                new CreateLoanDto(
                        userId,
                        bookId
                )
        );
    }

    private void saveLoan(CreateLoanDto createLoanDto) throws IOException, InterruptedException {
        Loan.createLoan(createLoanDto);
    }

}
