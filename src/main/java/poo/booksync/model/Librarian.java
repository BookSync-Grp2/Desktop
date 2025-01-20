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


    public static User getUserById(int userId) {
        for (User user : User.getUserList()) {
            if (user.getUserId() == userId) {
                return user;
            }
        }
        return null;
    }

    public boolean deleteUser(int userId) {
        User userToRemove = getUserById(userId);
        if (userToRemove != null) {
            User.getUserList().remove(userToRemove);
            return true;
        }
        return false;
    }

    public static boolean initializeUserList() {
        // On vide la liste avant de la recharger
        User.getUserList().clear();

        //A IMPLEMENTER
        return true;
    }



    public ArrayList<User> getAllUsers() {
        return User.getUserList();
    }

    //Créer un USER manuellement
    public boolean createUser(String firstName, String lastName, String email, String password, RoleType role) {
        //Création d'un nouveau user
        User.register(firstName, lastName, email, password, role);

        //Refresh de la liste des USER
        this.initializeUserList();
        return true;
    }
}
