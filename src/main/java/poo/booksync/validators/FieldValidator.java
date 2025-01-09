package poo.booksync.validators;

import javafx.scene.control.TextField;
import poo.booksync.validators.utils.Validator;

public class FieldValidator {

    public static void validate(TextField field, Validator validator, String input) {
        if (input == null || input.trim().isEmpty()) {
            resetFieldStyle(field);
            return;
        }

        boolean isValid = validator.validate(input);
        if (isValid) {
            field.setStyle("-fx-border-color: green;");
        } else {
            field.setStyle("-fx-border-color: red;");
        }
    }

    private static void resetFieldStyle(TextField field) {
        field.setStyle("");
    }
}
