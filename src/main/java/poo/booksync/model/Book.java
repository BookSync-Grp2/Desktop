package poo.booksync.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import poo.booksync.model.dto.CreateBookDto;
import poo.booksync.utils.Request;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class Book {
    private static ArrayList<Book> bookList = new ArrayList<Book>();
    private int bookId;
    private String title;
    private String author;
    private String ISBN;
    private int publishedYear;
    private boolean isAvailable;

    //Constructeur pour instancier un nouveau Book
    public Book(int bookId, String title, String author, String ISBN, int publishedYear){
        this.bookId =  bookId;
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.publishedYear = publishedYear;
        this.isAvailable = true;
    }

    //Constructeur pour charger la BDD
    public Book(int bookId, String title, String author, String ISBN, int publishedYear, boolean isAvailable){
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




    public static int deleteBook(int id){
        String url = "http://localhost:8080/api/books/"+id;
        try {
            //Création du client et de la requête POST
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
                // Le livre est supprimée avec succès
                System.out.println("Livre supprimé avec succès : " + response.body());

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
                System.err.println("Échec de la suppression du livre. Code HTTP = " + response.statusCode());
                System.err.println("Corps de la réponse : " + response.body());
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }


        return 0;
    }


    public static void initializeBookList() throws IOException, InterruptedException {
        bookList.clear();
        String responseBody = Request.sendGetRequest("https://booksync-back.onrender.com/api/books/all",true);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootArray = mapper.readTree(responseBody);

        // On parcourt le tableau
        if (rootArray.isArray()) {
            for (JsonNode bookNode : rootArray) {
                // Extraction des champs
                int bookId = bookNode.get("id").asInt();
                String title = bookNode.get("title").asText();
                String author = bookNode.get("author").asText();
                String isbn = bookNode.get("isbn").asText();
                int publishedYear = bookNode.get("publishedYear").asInt();
                boolean isAvailable = bookNode.get("available").asBoolean();
                // Instanciation du Book
                Book book = new Book(
                        bookId,
                        title,
                        author,
                        isbn,
                        publishedYear,
                        isAvailable
                );
                // Ajout à la liste statique
                bookList.add(book);
            }
        }
    }

    public static void createBook(CreateBookDto createBookDto) throws IOException, InterruptedException {
        Request.sendPostRequest("https://booksync-back.onrender.com/api/books/create",createBookDto.toJson(),true);
        initializeBookList();
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

    public int getPublishedYear() {
        return publishedYear;
    }
    public void setPublishedYear(int publishedYear) { this.publishedYear = publishedYear; }

    public boolean isAvailable() {
        return isAvailable;
    }
    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
