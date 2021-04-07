package sample;
import client.ControleurCreateConversation;
import client.ControleurUser;
import client.desktopBusinessRules;
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
import org.w3c.dom.Text;
import server.AutorizedUser;


import java.io.*;
import java.net.URL;
import java.util.*;
import common.Message;
import common.User;
import serverFiles.InstantiateSerializable;

public class NewConvControler {
    @FXML
    TextField txtfield_users;
    @FXML
    Button btn_add;
    @FXML
    ListView listview_users;
    @FXML
    TextField txtfield_groupname;
    @FXML Button btn_create;



    public void add_users() { //on ajoute tous les utilisateurs à une liste qui est retournée par la méthode add_users
        String buffer_users = txtfield_users.getText();
        //trouver un moyen de matcher le texte rentré avec une liste des utilisateurs
        ControleurUser control_user= new ControleurUser();
        User to_add = control_user.matchUser(buffer_users);
        if (to_add==null){
            final Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Le pseudo rentré ne correspond à aucun utilisateur");
            alert.show(); //raise message d'erreur pour dire qu'on n'a pas rentré un User valid
        }
        else{
            listview_users.getItems().add(to_add);
        }


    }

    public void create_conv() {
        String buffer_gpname = txtfield_groupname.getText();
        List<User> membres= new ArrayList<User>();
        membres.add(null);//ajouter l'utilisateur courant à la liste des membres
        for (int i = 0; i < listview_users.getItems().size(); i++) {
            User new_user= (User)listview_users.getItems().get(i); //on récupère tous les users
            //créer un nouveau port pour la conversation
            // ajouter les utilisateurs à la liste des utilisateurs présents dans la conv
            membres.add(new_user);
        }
        //si rien n'est rentré dans le nom groupe, par défaut prénom des utilisateurs
        if (membres.size()==2||buffer_gpname=="") { //
            buffer_gpname = membres.get(1).getPseudo();
        }
        ControleurCreateConversation controleurCreateConversation = new ControleurCreateConversation();
        try {
            Conversation new_conv = controleurCreateConversation.creerConversation(buffer_gpname, membres);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

//             = new Conversation(groupname, Arrays.asList(businessRules.getCurrentUser()),0); //members à changer
            // demander la création d'un nouveau port pour créer la conv 

            //une fois la conversation terminée on rouvre la fenêtre avec toute les conversations à jour avec la nouvelle
//            Stage stage = (Stage) btn_new_conv.getScene().getWindow();
//            root = FXMLLoader.load(getClass().getResource("createNewConv.fxml"));
//            Scene scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
        }


    }

