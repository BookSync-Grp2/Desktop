package poo.booksync.model;

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
        //A IMPLEMENTER

        //Rechargement de la liste
        Loans.initializeLoanList();
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
    public void bookRetrieved() {
        //this.isRetrieved = true;
        //A IMPLEMENTER

        //Rechargement de la liste
        Loans.initializeLoanList();
    }


    //Indique que le livre a été rendu par l’emprunteur
    public void bookReturned() {
        //this.isReturned = true;
        //A IMPLEMENTER

        //Rechargement de la liste
        Loans.initializeLoanList();
    }

    /**
     * initializeLoanList() : boolean
     * Ex. on charge quelques prêts en dur
     */
    public static boolean initializeLoanList() {
        //A IMPLEMENTER AVC le sevreur
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
