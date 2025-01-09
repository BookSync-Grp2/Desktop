package poo.booksync.model;
import java.util.ArrayList;
import java.util.Date;

public abstract class User {
    private static ArrayList<User> userList = new ArrayList<User>();
    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private Date dateJoined;
    private boolean validated;
    private RoleType role;

    //Constructeur pour instancier un nouvel objet
    public User(int userId, String firstName, String lastName, String email, RoleType role){
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateJoined = new Date();
        this.validated = false;
        this.role = role;
    }

    //Constructeur pour charger la BDD
    public User(int userId, String firstName, String lastName, String email,Date dateJoined, boolean validated, RoleType role){
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateJoined = dateJoined;
        this.validated = validated;
        this.role = role;
    }
}
