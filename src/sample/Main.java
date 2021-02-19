package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.lang.Integer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("acceuil.fxml"));
        FXMLLoader loader = new FXMLLoader();
        loader.setController(new Controleur());
        primaryStage.setTitle("EnstarDesktopFX");
        Scene accueil = new Scene(root, 640, 360);
        primaryStage.setScene(accueil);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
