package poo.booksync.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import poo.booksync.model.dto.CreateLoanDto;
import poo.booksync.utils.Request;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Loan {
    private static ArrayList<Loan> loansList = new ArrayList<Loan>();
    private int loanId;
    private int userId;
    private int bookId;
    private Date loanStartDate;
    private Date loanEndDate;
    private boolean isRetrieved;
    private boolean isReturned;

    //Constructeur pr instancier un nouvel emprunt
    public Loan(int loanId, int userId, int bookId, Date loanStartDate, Date loanEndDate){
        this.loanId = loanId;
        this.userId = userId;
        this.bookId = bookId;
        this.loanStartDate = loanStartDate;
        this.loanEndDate = loanEndDate;
        this.isRetrieved = false;
        this.isReturned = false;
    }

    //Constructeur pr charger la BDD
    public Loan(int loanId, int userId, int bookId, Date loanStartDate, Date loanEndDate, boolean isRetrieved, boolean isReturned){
        this.loanId = loanId;
        this.userId = userId;
        this.bookId = bookId;
        this.loanStartDate = loanStartDate;
        this.loanEndDate = loanEndDate;
        this.isRetrieved = isRetrieved;
        this.isReturned = isReturned;
    }

    public static void createLoan(CreateLoanDto createLoanDto) throws IOException, InterruptedException {
        Request.sendPostRequest("https://booksync-back.onrender.com/api/loans/create",createLoanDto.toJson(),true);
        Loan.initializeLoanList();
    }

    //Emprunt pr un USER
    public static ArrayList<Loan> getUserLoans(int idUser) {
        ArrayList<Loan> userLoans = new ArrayList<>();
        for (Loan l : loansList) {
            if (l.getUserId() == idUser) {
                userLoans.add(l);
            }
        }
        return userLoans;
    }

    //Indique que le livre a été retiré par l’emprunteur (après la création du prêt)
    public static void bookRetrieved(int id) throws IOException, InterruptedException {
        Request.sendPutRequest("https://booksync-back.onrender.com/api/loans/"+id+"/retrieve",null,true);
        Loan.initializeLoanList();
    }

    //Indique que le livre a été retiré par l’emprunteur (après la création du prêt)
    public static void bookReturned(int id) throws IOException, InterruptedException {
        Request.sendPutRequest("https://booksync-back.onrender.com/api/loans/"+id+"/return",null,true);
        Loan.initializeLoanList();
    }


    public static void initializeLoanList() throws IOException, InterruptedException {
        // Clear de la list
        loansList.clear();

        String responseBody = Request.sendGetRequest("https://booksync-back.onrender.com/api/loans/all",true);
        // On récupère le JSON sous forme d'arbre
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootArray = mapper.readTree(responseBody);
        // On parcourt le tableau
        if (rootArray.isArray()) {
            for (JsonNode loanNode : rootArray) {
                // Extraction des champs
                int id = loanNode.get("id").asInt();
                int userId = loanNode.get("userId").asInt();
                int bookId = loanNode.get("bookId").asInt();
                boolean retrieved = loanNode.get("retrieved").asBoolean();
                boolean returned = loanNode.get("returned").asBoolean();
                String startDateStr = loanNode.get("loanStartDate").asText();
                String endDateStr = loanNode.get("loanEndDate").asText();

                Date startDate = null;
                Date endDate = null;

                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
                    startDate = sdf.parse(startDateStr);
                    endDate = sdf.parse(endDateStr);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                // Instanciation du prêt (Loans)
                Loan loan = new Loan(
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
    }

    //GETTERS / SETTERS

    public static ArrayList<Loan> getLoanList() {
        return loansList;
    }
    public static void setLoanList(ArrayList<Loan> list) {
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
