package poo.booksync.validators;

import poo.booksync.validators.utils.Validator;
import java.util.regex.Pattern;

public class PasswordValidator implements Validator {

    private static final int MIN_LENGTH = 8;
    private static final String UPPERCASE_PATTERN = ".*[A-Z].*";
    private static final String DIGIT_PATTERN = ".*\\d.*";
    private static final String SPECIAL_CHAR_PATTERN = ".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*";

    public boolean validate(String password) {
        if (password == null || password.trim().isEmpty()) {
            System.out.println("Le mot de passe ne peut pas être vide");
            return false;
        }

        if (password.length() < MIN_LENGTH) {
            System.out.println("Le mot de passe doit contenir au moins " + MIN_LENGTH + " caractères");
            return false;
        }

        if (!Pattern.matches(UPPERCASE_PATTERN, password)) {
            System.out.println("Le mot de passe doit contenir au moins une majuscule");
            return false;
        }

        if (!Pattern.matches(DIGIT_PATTERN, password)) {
            System.out.println("Le mot de passe doit contenir au moins un chiffre");
            return false;
        }

        if (!Pattern.matches(SPECIAL_CHAR_PATTERN, password)) {
            System.out.println("Le mot de passe doit contenir au moins un caractère spécial");
            return false;
        }

        return true;
    }
}
