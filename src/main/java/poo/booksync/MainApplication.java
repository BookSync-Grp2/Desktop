package poo.booksync;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBase;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainApplication extends Application {

    private static Stage primaryStage;  // Stockage du stage global

    @Override
    public void start(Stage stage) throws IOException {
        // Stockage de la référence du stage principal
        primaryStage = stage;

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("view/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("img/logo.png")));
        stage.getIcons().add(image);
        stage.setTitle("BookSync!");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    @FXML
    public static void redirectTo(String view) {
        try {
            // Charge le fichier FXML
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("view/" + view + ".fxml"));
            Parent root = fxmlLoader.load();
            Scene newScene = new Scene(root);
            if (primaryStage != null) {
                double currentWidth = primaryStage.getWidth();
                double currentHeight = primaryStage.getHeight();
                primaryStage.setScene(newScene);
                primaryStage.setWidth(currentWidth);
                primaryStage.setHeight(currentHeight);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
