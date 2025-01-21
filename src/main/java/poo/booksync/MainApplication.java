package poo.booksync;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("view/main.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("img/logo.png")));
        stage.getIcons().add(image);
        stage.setTitle("BookSync!");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    @FXML
    public static void redirectTo(String view, ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("view/" + view + ".fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            if (stage != null) {
                double currentWidth = stage.getWidth();
                double currentHeight = stage.getHeight();
                stage.setScene(new Scene(root, currentWidth, currentHeight));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
