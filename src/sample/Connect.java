package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import server.AutorizedUser;

import java.io.*;
import java.net.URL;
import java.util.*;
import common.Message;

public class Connect{
    @FXML MenuBar menubar_menu;
    @FXML ChoiceBox choicebox_conversations;
    @FXML ListView lstview_users;
    @FXML ListView lstview_currentconv;
    @FXML ScrollBar scrollbar_left;
    @FXML TextArea txtarea_currentconv;
    @FXML TextField txtfield_msg;
    @FXML Button btn_envoyer;
    @FXML Button btn_new_conv;


    Parent root;

    public AutorizedUser autorizedUsers = new AutorizedUser();



    public void initialize() {
        System.out.println("msgri");



    }

    public void create_new_Conversation() throws IOException {


        //ouvrir une nouvelle fenêtre pour les paramètres d'une nouvelle conversation
        Stage stage = (Stage) btn_new_conv.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("createNewConv.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();




    }
    public void envoyer_message() throws IOException {
        int user=1; //voir comment récupérer l'user ID
        String buffer_msg=txtfield_msg.getText();
        Message new_message= new Message(buffer_msg,user);
        //ajouter le message dans la liste des messages de cette conv et push sur le serveur
        System.out.println(new_message);

        lstview_currentconv.getItems().add(buffer_msg); //pas besoin car on récup les messgaes d'un fichier



        //afficher dans le text field

//        Stage stage = (Stage) btn_envoyer.getScene().getWindow();
//        root = FXMLLoader.load(getClass().getResource("connectedpage.fxml")); //rafraichit la page
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();

    }
}
