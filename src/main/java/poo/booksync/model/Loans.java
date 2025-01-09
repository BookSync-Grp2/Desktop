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
}
