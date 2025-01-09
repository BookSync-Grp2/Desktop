package poo.booksync.model;

import java.util.ArrayList;
import java.util.Date;

public class Book {
    private static ArrayList<Book> bookList = new ArrayList<Book>();
    private int bookId;
    private String title;
    private String author;
    private int ISBN;
    private Date publishedYear;
    private boolean isAvailable;

    //Constructeur pour instancier un nouveau Book
    public Book(int bookId, String title, String author, int ISBN, Date publishedYear){
        this.bookId =  bookId;
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.publishedYear = publishedYear;
        this.isAvailable = true;
    }

    //Constructeur pour charger la BDD
    public Book(int bookId, String title, String author, int ISBN, Date publishedYear, boolean isAvailable){
        this.bookId =  bookId;
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.publishedYear = publishedYear;
        this.isAvailable = isAvailable;
    }
}
