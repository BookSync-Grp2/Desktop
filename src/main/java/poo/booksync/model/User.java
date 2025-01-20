package poo.booksync.model;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Date;
import java.io.IOException;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;

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


    public static void setCurrentUser() {
        try {
            HttpClient client = HttpClient.newBuilder().build();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/api/user/me"))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + getAuthToken())
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode userNode = mapper.readTree(response.body());

                // Si l’API renvoie un seul objet JSON, on ne fait pas de boucle.
                int userId = userNode.get("id").asInt();
                String firstName = userNode.get("firstName").asText();
                String lastName = userNode.get("lastName").asText();
                String email = userNode.get("email").asText();
                String roleString = userNode.get("role").asText();
                boolean validated = userNode.get("validated").asBoolean();

                // DateJoined : on peut parser en java.util.Date
                String dateJoinedStr = userNode.get("dateJoined").asText();
                Date dateJoined = null;
                try {
                    dateJoined = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").parse(dateJoinedStr);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // Conversion en RoleType
                RoleType roleType;
                try {
                    roleType = RoleType.valueOf(roleString);
                } catch (IllegalArgumentException e) {
                    // Si le rôle n'existe pas dans l'enum, on met USER par défaut
                    roleType = RoleType.USER;
                }

                // Instanciation selon role
                User user;
                if ("USER".equalsIgnoreCase(roleString)) {
                    user = new Client(userId, firstName, lastName, email, dateJoined, validated, roleType);
                } else {
                    user = new Librarian(userId, firstName, lastName, email, dateJoined, validated, roleType);
                }

                currentUser = user;


            } else {
                System.err.println("Erreur API : " + response.statusCode() + " - " + response.body());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Mise à jour de la liste
    public static void setUserList(ArrayList<User> list){
        for(User u : list){
            userList.add(u);
        }
    }

    public static void clearUserList(){
        userList.clear();
    }

    public static String getAuthToken(){
        String ligne = null;
        try (BufferedReader reader = new BufferedReader(new FileReader("auth_token.txt"))) {
            ligne = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ligne;
    }

    //Pour le logout
    public static void clearCurrentUser() {
        currentUser = null;
    }

    //Methode pr se register
    public static boolean register(String firstName,
                                   String lastName,
                                   String email,
                                   String password,
                                   RoleType roleType) {
        try {
            // Préparation du JSON à envoyer
            String jsonBody = String.format(
                    "{\"firstName\":\"%s\",\"lastName\":\"%s\",\"email\":\"%s\",\"password\":\"%s\",\"isValidated\":\"false\",\"roleType\":\"%s\"}",
                    firstName, lastName, email, password, roleType
            );

            // Création du client et de la requête POST
            HttpClient client = HttpClient.newBuilder().build();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/auth/register"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            // Envoi de la requête et récupération de la réponse
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                String responseBody = response.body();

                return true;  // Enregistrement (register) OK
            } else {
                System.err.println("Échec de register. Code HTTP = " + response.statusCode());
                return false;
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean login(String email, String password) {
        try {
            //JSON à envoyer pr se login
            String jsonBody = String.format("{\"email\":\"%s\",\"password\":\"%s\"}", email, password);

            //Création du client et de la requête POST
            HttpClient client = HttpClient.newBuilder().build();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/auth/login"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            //Envoie de la requête et récupération de la rép
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                String responseBody = response.body();

                //Extraction du token du JSON & Ecriture dans un fichier
                String token = extraireToken(responseBody);

                //Si le fichier existe auparavant on le clear pour éviter l'écriture de plusieurs tokens
                try {
                    Files.write(Paths.get("auth_token.txt"), new byte[0]);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                Files.writeString(Paths.get("auth_token.txt"), token);

                //Initialisation de la liste d'USER :
                Librarian.initializeUserList();

                //Set currentUser
                setCurrentUser();

                return true;  // Connexion OK
            } else {
                System.err.println("Échec de connexion. Code HTTP = " + response.statusCode());
                return false;
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Extraction du champ token dans la JSON de réponse à un register / un login
    private static String extraireToken(String json) {

        // On cherche la chaine "token" pr la parser
        String cle = "\"token\":\"";
        int startIndex = json.indexOf(cle);
        if (startIndex == -1) return "";
        startIndex += cle.length();
        int endIndex = json.indexOf("\"", startIndex);
        if (endIndex == -1) return "";
        return json.substring(startIndex, endIndex);
    }

    //Lougout du currentUser
    public static boolean logout(){
        //On supprime le currentUser
        clearCurrentUser();

        //On clear le fichier avec le token
        try {
            Files.write(Paths.get("auth_token.txt"), new byte[0]);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return true;
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
