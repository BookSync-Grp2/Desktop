package poo.booksync.model;

import java.util.Date;

public class Client extends User{
    //Constructeur
    public Client(int userId, String firstName, String lastName, String email, RoleType role) {
        super(userId, firstName, lastName, email, role);
    }

    public Client(int userId, String firstName, String lastName, String email, Date dateJoined, boolean validated, RoleType role) {
        super(userId, firstName, lastName, email, dateJoined, validated, role);
    }
}
