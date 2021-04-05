package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{


        Parent root = FXMLLoader.load(getClass().getResource("accueil.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("connectedpage.fxml"));
        FXMLLoader loader = new FXMLLoader();
        loader.setController(new Connect());
        //loader.setController(new Controleur());
        primaryStage.setTitle("EnstarDesktopFX");
        Scene accueil = new Scene(root, 1000, 600);
        primaryStage.setScene(accueil);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}



