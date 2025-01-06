package poo.booksync.booksync;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HomeApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomeApplication.class.getResource("view/home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("img/logo.png")));
        stage.getIcons().add(image);
        stage.setTitle("BookSync!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}