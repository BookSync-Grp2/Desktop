package poo.booksync.model;
import java.util.ArrayList;
import java.util.Date;

public class Librarian extends User {
    //Constructeur
    public Librarian(int userId, String firstName, String lastName, String email, RoleType role) {
        super(userId, firstName, lastName, email, role);
    }

    public Librarian(int userId, String firstName, String lastName, String email, Date dateJoined, boolean validated, RoleType role) {
        super(userId, firstName, lastName, email, dateJoined, validated, role);
    }
}
