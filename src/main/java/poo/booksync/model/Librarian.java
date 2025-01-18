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

    public boolean initializeUserList() {
        // On vide la liste avant de la recharger
        User.getUserList().clear();

        // Ajouts d’utilisateurs (test)
        //Remplacer par co avec la BDD qd ce sera possible
        User.getUserList().add(new Librarian(
                1, "Alice", "Martin", "alice.martin@biblio.com",
                new Date(), true, RoleType.LIBRARIAN
        ));
        User.getUserList().add(new Librarian(
                2, "Bob", "Dupond", "bob.dupond@biblio.com",
                new Date(), true, RoleType.LIBRARIAN
        ));
        User.getUserList().add(new Client(
                3, "Claire", "Durand", "claire.durand@example.com",
                new Date(), false, RoleType.CLIENT
        ));
        User.getUserList().add(new Client(
                4, "David", "Robert", "david.robert@example.com",
                new Date(), false, RoleType.CLIENT
        ));

        return true;
    }



    public ArrayList<User> getAllUsers() {
        return User.getUserList();
    }

    //Créer un USER manuellement
    public boolean createUser(String firstName, String lastName, String email, RoleType role) {
        //On prend la position de la liste pr nouvelle ID (A garder ?)
        int newUserId = User.getUserList().size() + 1;

        User newUser;
        if (role == RoleType.CLIENT) {
            newUser = new Client(newUserId, firstName, lastName, email, role);
        } else {
            newUser = new Librarian(newUserId, firstName, lastName, email, role);
        }

        //Envoie de nouvel utilisateur à la BDD

        //Refresh de la liste des USER
        this.initializeUserList();
        return true;
    }
}
