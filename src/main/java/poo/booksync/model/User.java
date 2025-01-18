package poo.booksync.model;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

public abstract class User {

    private static ArrayList<User> userList = new ArrayList<User>();
    private static User currentUser = null;
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

    public User(){}

    //Setter pr l'User courant
    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    //Methode static pr login
    public static boolean login(String email, String password) {
        //Verif en BDD si les paramètres sont bons.
        //Si c'est ok on met le currentUser à jour.
        //setCurrentUser(u);
                //return true;
        return false;
    }

    //Retourner les informations sur l'User courant
    public User getCurrentUser(){
        return currentUser;
    }

    //GETTER & SETTER
    public static ArrayList<User> getUserList() {
        return userList;
    }

    public int getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateJoined() {
        return dateJoined;
    }
    public void setDateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }

    public boolean isValidated() {
        return validated;
    }
    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    public RoleType getRole() {
        return role;
    }
    public void setRole(RoleType role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", dateJoined=" + dateJoined +
                ", validated=" + validated +
                ", role=" + role +
                '}';
    }
}
