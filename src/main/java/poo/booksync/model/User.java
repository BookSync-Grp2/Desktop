package poo.booksync.model;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import poo.booksync.model.dto.LoginDto;
import poo.booksync.model.dto.UserRegistrationDto;
import poo.booksync.utils.Request;
import poo.booksync.utils.TokenExtractor;

import java.io.BufferedReader;
import java.io.FileReader;
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
    private String password;
    private Date dateJoined;
    private boolean validated;
    private RoleType role;

    public User(String firstName, String lastName, String email,String password){
        this.firstName = firstName;
        this.password = password;
        this.lastName = lastName;
        this.email = email;
        this.dateJoined = new Date();
        this.validated = false;
        this.role = RoleType.ADMIN;
    }

    public User(int userId, String firstName, String lastName, String email, RoleType role) {
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

    public static void register(UserRegistrationDto userRegistration) throws IOException, InterruptedException {
        String responseBody = Request.sendPostRequest("https://booksync-back.onrender.com/auth/register",userRegistration.toJson());
        TokenExtractor.extractToken(responseBody);
    }

    public static void login(LoginDto loginDto) throws IOException, InterruptedException {
        String responseBody = Request.sendPostRequest("https://booksync-back.onrender.com/auth/login",loginDto.toJson());
        TokenExtractor.extractToken(responseBody);
    }

    public static void initializeUserList() throws IOException, InterruptedException {
        clearUserList();
        String responseBody = Request.sendGetRequest("https://booksync-back.onrender.com/api/user/all",true);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootArray = mapper.readTree(responseBody);
        if (rootArray.isArray()) {
            for (JsonNode userNode : rootArray) {
                // Extraction des champs
                int userId = userNode.get("id").asInt();
                String firstName = userNode.get("firstName").asText();
                String lastName = userNode.get("lastName").asText();
                String email = userNode.get("email").asText();
                String roleString = userNode.get("role").asText();
                boolean validated = userNode.get("validated").asBoolean();

                // DateJoined : on peut parser en java.util.Date
                // Ici on suppose un format "yyyy-MM-dd'T'HH:mm:ss.SSSZ" ou similaire
                String dateJoinedStr = userNode.get("dateJoined").asText();
                Date dateJoined = null;

                try {

                    // À adapter selon le format exact renvoyé par votre API
                    dateJoined = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").parse(dateJoinedStr);

                } catch (Exception e) {
                    e.printStackTrace();
                }

                // Conversion de roleString en RoleType (si vous avez un enum)
                RoleType roleType;
                try {
                    roleType = RoleType.valueOf(roleString);
                } catch (IllegalArgumentException e) {

                    // Si le role n'est pas dans l'enum, on met par défaut
                    roleType = RoleType.USER;
                }

                // Instanciation en fonction du champ role
                User user;
                if ("USER".equalsIgnoreCase(roleString)) {

                    // Instancier un "Client" (sous-classe de User)
                    user = new Client(userId, firstName, lastName, email, dateJoined, validated, roleType);
                } else {

                    // Instancier un "Librarian" (sous-classe de User)
                    user = new Librarian(userId, firstName, lastName, email, dateJoined, validated, roleType);
                }

                // Ajout à la liste statique
                userList.add(user);
            }
        }
    }


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
