package sample;

import client.ControleurConversation;
import common.Conversation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
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
import javafx.scene.input.MouseEvent;

import java.io.*;
import java.net.URL;
import java.util.*;
import common.Message;
import common.User;

public class Connect {


    @FXML
    ListView lstview_users;
    @FXML
    ListView lstview_currentconv;
    @FXML
    ScrollBar scrollbar_left;
    @FXML
    TextArea txtarea_currentconv;
    @FXML
    TextField txtfield_msg;
    @FXML
    Button btn_envoyer;
    @FXML
    Button btn_new_conv;
    @FXML
    Button btn_deconnect;


    Parent root;

    public AutorizedUser autorizedUser = new AutorizedUser();
    public User current_user;


    public void initialize() {
        //print_users_in_list();
        List<User> membres= new ArrayList<User>();
        membres.add(new User(1, "toto", null, null));
        lstview_users.getItems().add(new User(1,"toto",null,null));
        lstview_users.getItems().add(new Conversation("toto",membres, 1));

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
        Message new_message = new Message(buffer_msg, user);
        ControleurConversation control_conv= new ControleurConversation();
        if (control_conv.sendMessage(new_message)=="0"){ //si le message a bien été envoyé, on fait un update
            update(); //
        }
        System.out.println(new_message);

        //afficher dans le text field

//        Stage stage = (Stage) btn_envoyer.getScene().getWindow();
//        root = FXMLLoader.load(getClass().getResource("connectedpage.fxml")); //rafraichit la page
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();

    }

    //a rajouter -> clic sur une conversation= ouvrir un port
    public void print_message(){
        lstview_currentconv.getItems().add("blabla");

    }
    public void open_conv(Conversation aConv){



    }
    public void update(){

    }



    public void deconnect() {
        //se déconnecter en cliquant sur le bouton

    }


    @FXML
    public void handleMouseClick(MouseEvent arg0) {//ouvre une conversation sur le côté
        Conversation conv = (Conversation) lstview_users.getSelectionModel().getSelectedItem(); //à voir avec protocoleConv
        System.out.println("clicked on " + lstview_users.getSelectionModel().getSelectedItem()); //on affiche sur quoi on a cliqué
        //print_message();
        open_conv(conv);


    }
}
