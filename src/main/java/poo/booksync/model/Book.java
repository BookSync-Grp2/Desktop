package poo.booksync.model;

import java.util.ArrayList;
import java.util.Date;

public class Book {
    private static ArrayList<Book> bookList = new ArrayList<Book>();
    private int bookId;
    private String title;
    private String author;
    private String ISBN;
    private Date publishedYear;
    private boolean isAvailable;

    //Constructeur pour instancier un nouveau Book
    public Book(int bookId, String title, String author, String ISBN, Date publishedYear){
        this.bookId =  bookId;
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.publishedYear = publishedYear;
        this.isAvailable = true;
    }

    //Constructeur pour charger la BDD
    public Book(int bookId, String title, String author, String ISBN, Date publishedYear, boolean isAvailable){
        this.bookId =  bookId;
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.publishedYear = publishedYear;
        this.isAvailable = isAvailable;
    }

    public static ArrayList<Book> getAllBooks() {
        return bookList;
    }

    public static int getCount(String isbn) {
        int count = 0;
        for (Book b : bookList) {
            if (b.getISBN().equalsIgnoreCase(isbn)) {
                count++;
            }
        }
        return count;
    }

    public static Book getBookById(int id) {
        for (Book b : bookList) {
            if (b.getBookId() == id) {
                return b;
            }
        }
        return null;
    }

    public static int createBook(String title, String author, String isbn, Date publishedYear, String category) {

        //A IMPLEMENTER

        //Recharge de la liste de livre
        Book.initializeBookList();

        return 0;
    }

    public static ArrayList<String> getCategories(int id) {
        //A corriger selon besoins
        return null;
    }


    public static boolean initializeBookList() {
        bookList.clear();
        // A IMPLEMENTER
        return true;
    }

    //GETTERS / SETTERS

    public static ArrayList<Book> getBookList() {
        return bookList;
    }
    public static void setBookList(ArrayList<Book> list) {
        bookList = list;
    }

    public int getBookId() {
        return bookId;
    }
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) { this.author = author; }

    public String getISBN() {
        return ISBN;
    }
    public void setISBN(String ISBN) { this.ISBN = ISBN; }

    public Date getPublishedYear() {
        return publishedYear;
    }
    public void setPublishedYear(Date publishedYear) { this.publishedYear = publishedYear; }

    public boolean isAvailable() {
        return isAvailable;
    }
    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
