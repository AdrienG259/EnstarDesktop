package sample;

import common.Conversation;
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
import common.User;

public class Connect{


    @FXML ListView lstview_users;
    @FXML ListView lstview_currentconv;
    @FXML ScrollBar scrollbar_left;
    @FXML TextArea txtarea_currentconv;
    @FXML TextField txtfield_msg;
    @FXML Button btn_envoyer;
    @FXML Button btn_new_conv;
    @FXML Button btn_deconnect;


    Parent root;

    public AutorizedUser autorizedUser = new AutorizedUser();
    public User current_user;


    public void initialize() {

        //print_users_in_list();
//        try {
//            File connected_user = new File("connected_user.txt");
//            Scanner myReader = new Scanner(connected_user);
//            current_user = myReader.nextLine();
//            myReader.close();
//            connected_user.delete();
//        } catch (FileNotFoundException e) {
//            System.out.println("Err lect fichier");
//            e.printStackTrace();
//        }
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
        int user = 1; //voir comment récupérer l'user ID
        String buffer_msg = txtfield_msg.getText();
        Message new_message = new Message(buffer_msg,user);
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

    //a rajouter -> clic sur une conversation= ouvrir un port
    public void open_conv(){
        // dans la listView sur le côté, dès qu'on clic sur un utilisateur on ouvre un port de connexion correspondant à la conversation
        List<Conversation> lst_conversation= current_user.getConversation();
        Object conv_selected= lstview_users.getSelectionModel().getSelectedItem();
        for (int i=0;i<lst_conversation.size(); i++){
            Conversation conv_i=lst_conversation.get(i);


    }
//    public void deconnect(){
//        //se déconnecter en cliquant sur le bouton
//
//    }
//    public void print_users_in_list(){
//        List<Conversation> lst_conversation= current_user.getConversation();
//        for (int i=0;i<lst_conversation.size(); i++){
//            Conversation conv_i=lst_conversation.get(i);
//            lstview_users.getItems().add(conv_i.getNomGroupe());
//        }

    }
}
