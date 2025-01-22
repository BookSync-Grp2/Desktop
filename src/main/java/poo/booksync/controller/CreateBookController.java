package poo.booksync.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import poo.booksync.MainApplication;
import poo.booksync.model.Book;
import poo.booksync.model.dto.CreateBookDto;

public class CreateBookController {

    @FXML private TextField titleField;
    @FXML private TextField isbnField;
    @FXML private TextField authorField;
    @FXML private DatePicker publishDatePicker;


    public void handleCreateBook(ActionEvent event) throws IOException, InterruptedException {
        // Créer un livre
        Book.createBook(
                new CreateBookDto(
                        titleField.getText(),
                        authorField.getText(),
                        isbnField.getText(),
                        publishDatePicker.getValue().getYear()
                )
        );

        clearField();
        
        MainApplication.redirectTo("main",event);
    }

    public void clearField(){
        titleField.clear();
        authorField.clear();
        isbnField.clear();
        publishDatePicker.setValue(null);
    }
}
