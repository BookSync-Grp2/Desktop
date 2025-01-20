package poo.booksync.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Loans {
    private static ArrayList<Loans> loansList = new ArrayList<Loans>();
    private int loanId;
    private int userId;
    private int bookId;
    private Date loanStartDate;
    private Date loanEndDate;
    private boolean isRetrieved;
    private boolean isReturned;

    //Constructeur pr instancier un nouvel emprunt
    public Loans(int loanId, int userId, int bookId, Date loanStartDate, Date loanEndDate){
        this.loanId = loanId;
        this.userId = userId;
        this.bookId = bookId;
        this.loanStartDate = loanStartDate;
        this.loanEndDate = loanEndDate;
        this.isRetrieved = false;
        this.isReturned = false;
    }

    //Constructeur pr charger la BDD
    public Loans(int loanId, int userId, int bookId, Date loanStartDate, Date loanEndDate, boolean isRetrieved, boolean isReturned){
        this.loanId = loanId;
        this.userId = userId;
        this.bookId = bookId;
        this.loanStartDate = loanStartDate;
        this.loanEndDate = loanEndDate;
        this.isRetrieved = isRetrieved;
        this.isReturned = isReturned;
    }

    public static int createLoan(int idBook, int idUser) {

        // Construction du JSON à envoyer
        String jsonBody = String.format(
                "{" +
                        "\"userId\": %d," +
                        "\"bookId\": %d" +
                        "}",
                idUser,
                idBook
        );

        try {
            // Création du client et de la requête POST
            HttpClient client = HttpClient.newBuilder().build();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/api/loans/create"))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + User.getAuthToken())
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            // Envoi de la requête et récupération de la réponse
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                //Refresh de la liste
                Loans.initializeLoanList();
                return 1;
            } else {
                System.err.println("Échec de la création du prêt. Code HTTP = " + response.statusCode());
                System.err.println("Corps de la réponse : " + response.body());
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //Emprunt pr un USER
    public static ArrayList<Loans> getUserLoans(int idUser) {
        ArrayList<Loans> userLoans = new ArrayList<>();
        for (Loans l : loansList) {
            if (l.getUserId() == idUser) {
                userLoans.add(l);
            }
        }
        return userLoans;
    }

    //Indique que le livre a été retiré par l’emprunteur (après la création du prêt)
    public static void bookRetrieved(int id) {
        String url = "http://localhost:8080/api/loans/"+id+"/retrieve";
        try {
            // Création du client et de la requête POST
            HttpClient client = HttpClient.newBuilder().build();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + User.getAuthToken())
                    .PUT(HttpRequest.BodyPublishers.ofString(""))
                    .build();

            // Envoi de la requête et récupération de la réponse
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                //Refresh de la liste
                Loans.initializeLoanList();
            } else {
                System.err.println("Échec de la création du prêt. Code HTTP = " + response.statusCode());
                System.err.println("Corps de la réponse : " + response.body());
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    //Indique que le livre a été rendu par l’emprunteur
    public static void bookReturned(int id) {
        String url = "http://localhost:8080/api/loans/"+id+"/return";
        try {
            // Création du client et de la requête POST
            HttpClient client = HttpClient.newBuilder().build();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + User.getAuthToken())
                    .PUT(HttpRequest.BodyPublishers.ofString(""))
                    .build();

            // Envoi de la requête et récupération de la réponse
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                //Refresh de la liste
                Loans.initializeLoanList();
            } else {
                System.err.println("Échec de la création du prêt. Code HTTP = " + response.statusCode());
                System.err.println("Corps de la réponse : " + response.body());
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static boolean initializeLoanList() {
        // Clear de la list
        loansList.clear();

        try {
            // 2) Création du client et de la requête GET
            HttpClient client = HttpClient.newBuilder().build();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/api/loans/all"))
                    .header("Content-Type", "application/json")
                    // Si besoin, ajoutez le token si nécessaire
                    .header("Authorization", "Bearer " + User.getAuthToken())
                    .GET()
                    .build();

            // Envoi de la requête et récupération de la réponse
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {

                // On récupère le JSON sous forme d'arbre
                ObjectMapper mapper = new ObjectMapper();
                JsonNode rootArray = mapper.readTree(response.body());

                // On parcourt le tableau
                if (rootArray.isArray()) {
                    for (JsonNode loanNode : rootArray) {
                        // Extraction des champs
                        int id            = loanNode.get("id").asInt();
                        int userId        = loanNode.get("userId").asInt();
                        int bookId        = loanNode.get("bookId").asInt();
                        boolean retrieved = loanNode.get("retrieved").asBoolean();
                        boolean returned  = loanNode.get("returned").asBoolean();

                        String startDateStr = loanNode.get("loanStartDate").asText();
                        String endDateStr   = loanNode.get("loanEndDate").asText();

                        Date startDate = null;
                        Date endDate   = null;

                        try {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
                            startDate = sdf.parse(startDateStr);
                            endDate   = sdf.parse(endDateStr);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        // Instanciation du prêt (Loans)
                        Loans loan = new Loans(
                                id,
                                userId,
                                bookId,
                                startDate,
                                endDate,
                                retrieved,
                                returned
                        );

                        // Ajout à la liste statique
                        loansList.add(loan);
                    }
                }
            } else {
                System.err.println("Erreur API : " + response.statusCode()
                        + " - " + response.body());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    //GETTERS / SETTERS

    public static ArrayList<Loans> getLoanList() {
        return loansList;
    }
    public static void setLoanList(ArrayList<Loans> list) {
        loansList = list;
    }

    public int getLoanId() {
        return loanId;
    }
    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public Date getLoanStartDate() {
        return loanStartDate;
    }
    public void setLoanStartDate(Date loanStartDate) {
        this.loanStartDate = loanStartDate;
    }

    public Date getLoanEndDate() {
        return loanEndDate;
    }
    public void setLoanEndDate(Date loanEndDate) {
        this.loanEndDate = loanEndDate;
    }

    public boolean isRetrieved() {
        return isRetrieved;
    }
    public void setRetrieved(boolean retrieved) {
        isRetrieved = retrieved;
    }

    public boolean isReturned() {
        return isReturned;
    }
    public void setReturned(boolean returned) {
        isReturned = returned;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "loanId=" + loanId +
                ", userId=" + userId +
                ", bookId=" + bookId +
                ", loanStartDate=" + loanStartDate +
                ", loanEndDate=" + loanEndDate +
                ", isRetrieved=" + isRetrieved +
                ", isReturned=" + isReturned +
                '}';
    }
}
