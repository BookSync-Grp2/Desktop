package poo.booksync.validators;

import poo.booksync.validators.utils.Validator;

import java.util.List;
import java.util.regex.Pattern;

public class EmailValidator implements Validator {
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private static final String ERROR_MESSAGE = "Format d'email invalide";

    @Override
    public boolean validate(String email) {
        return email != null && !email.trim().isEmpty() && pattern.matcher(email).matches();
    }
}
