package sample;

import client.ControleurConversation;
import client.ControleurUser;
import common.Conversation;
import common.Historique;
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
import serverFiles.SharedVariableCannotAccess;
import serverFiles.SharedVariables;

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
    public String current_user_log;
    public Conversation current_conversation;
    private ControleurConversation control_conv;
    private ControleurUser ctrl_user;




    public void initialize() throws IOException {
        //print_conversations();
        //pour tester si ça marche correctement
        List<User> membres= new ArrayList<User>();
        membres.add(new User(1, "toto", null, null));
        Conversation aConv= new Conversation("toto",membres, 1);
        aConv.getHistorique();
        lstview_users.getItems().add(new Conversation("toto",membres, 1));
        SharedVariables sharedVariables = new SharedVariables("clientFiles/sharedVariables");
        ctrl_user=new ControleurUser();
        try{
            current_user_log = sharedVariables.accessVariable("current_user_log");
            current_user= ctrl_user.matchUser(current_user_log); // Récuperer l'objet User associé au login
        } catch (SharedVariableCannotAccess sharedVariableCannotAccess) {
            sharedVariableCannotAccess.printStackTrace();
        }

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
        control_conv= new ControleurConversation();
        if (control_conv.sendMessage(new_message,current_conversation)=="0"){ //si le message a bien été envoyé, on fait un update
            update(); //
        }
        System.out.println("message envoyé"+new_message);
    }

    public void print_message(){
        lstview_currentconv.getItems().add("blabla");

    }
    public void open_conv(Conversation aConv){
        control_conv= new ControleurConversation();
        Historique historique_conv= control_conv.getHistorique(aConv);
        List<Message> list_message=historique_conv.getListeMessages();
        for (Message msg:list_message){
            lstview_currentconv.getItems().add(msg);
        }
    }
    public void update(){

    }
    public void print_conversations(){
        //récupérer du serveur toutes les conversations dans lesquelles l'user est impliqué
        List<Integer> listIDconv= current_user.getIDConversations();
        control_conv= new ControleurConversation();
        List<Conversation> conv= control_conv.getConversation(listIDconv);
        if(conv==null){
            lstview_users.getItems().add("rien à pas de conversation en cours ");
        }
        else{
            lstview_users.getItems().removeAll();
            for (Conversation uneconv:conv){
                lstview_users.getItems().add(0,uneconv); //on met tout dans l'ordre décroissant
            }
        }
    }



    public void deconnect() throws IOException {
        //se déconnecter en cliquant sur le bouton
        // envoyer la demande de déconnexion au serveur //
        // /!\ //
        Stage stage = (Stage) btn_envoyer.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("accueil.fxml")); //rafraichit la page
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }


    @FXML
    public void handleMouseClick(MouseEvent arg0) {//ouvre une conversation sur le côté
        Conversation conv = (Conversation) lstview_users.getSelectionModel().getSelectedItem(); //à voir avec protocoleConv
        System.out.println("clicked on " + lstview_users.getSelectionModel().getSelectedItem()); //on affiche sur quoi on a cliqué
        //print_message();
        open_conv(conv);
        current_conversation=conv;


    }
}
