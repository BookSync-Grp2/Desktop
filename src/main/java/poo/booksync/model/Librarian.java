package poo.booksync.model;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
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

    public static int deleteUser(int id){
        String url = "http://localhost:8080/api/user/"+id;
        try {
            HttpClient client = HttpClient.newBuilder().build();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + User.getAuthToken()) // si nécessaire
                    .DELETE()
                    .build();

            //Envoi de la requête et récupération de la réponse
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200 || response.statusCode() == 201) {
                // Le USER est supprimée avec succès
                System.out.println("Utilisateur supprimé avec succès : " + response.body());

                // Recharger la liste des livres
                Book.initializeBookList();

                try {
                    ObjectMapper mapper = new ObjectMapper();
                    JsonNode node = mapper.readTree(response.body());
                    if (node.has("id")) {
                        return node.get("id").asInt(); // nouvel ID
                    }
                } catch (Exception e) {
                }
                return 1;
            } else {
                // En cas d'erreur
                System.err.println("Échec de la suppression de l'utilisateur. Code HTTP = " + response.statusCode());
                System.err.println("Corps de la réponse : " + response.body());
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }



    public ArrayList<User> getAllUsers() {
        return User.getUserList();
    }
}
