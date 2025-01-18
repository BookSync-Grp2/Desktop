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

        public static int getCount(int categoryId) {
            int nb=0;
            for (Category c : categoryList) {
                if (c.getCategoryId() == categoryId) {
                    nb++;
                }
            }
            return 0;
        }

        public static boolean initializeCategory() {

        //A IMPLEMENTER
            return true;
        }

        //GETTERS / SETTERS

        public static ArrayList<Category> getCategoryList() {
            return categoryList;
        }
        public static void setCategoryList(ArrayList<Category> list) {
            categoryList = list;
        }

        public int getCategoryId() {
            return categoryId;
        }
        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
        }

        public String getTitle() {
            return title;
        }
        public void setTitle(String title) { this.title = title; }

        public String getDescription() {
            return description;
        }
        public void setDescription(String description) { this.description = description; }

        @Override
        public String toString() {
            return "Category{" +
                    "categoryId=" + categoryId +
                    ", title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    '}';
        }
    }

}
