package poo.booksync.model;

import java.util.ArrayList;
import java.util.Date;

public class Category {
    private static ArrayList<Category> categoryList = new ArrayList<Category>();
    private int categoryId;
    private String title;
    private String description;

    //Constructeur pour les nouveaux objets et pour le chargement de la BDD
    public Category(int categoryId, String title, String description){
        this.categoryId = categoryId;
        this.title = title;
        this.description = description;
    }
}
