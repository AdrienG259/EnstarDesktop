package sample;
import client.ControleurCreateConversation;
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


    public String get_groupname(){
        String buffer_gpname = txtfield_groupname.getText();
        return buffer_gpname;
    }
    public void add_users() {
        String buffer_users = txtfield_users.getText();
        listview_users.getItems().add(buffer_users);
        /*if (verify_user(buffer_users)) {

        }
*/
    }

    public boolean verify_user(String User) {
        //vérifier que l'utilisateur rentré correspond bien à la base de données
        if (true) { //condition à changer
            return true;//l'utilisateur rentré est bien dans la base de données
        }
        return false;
    }

    public void create_conv() {
        for (int i = 0; i < listview_users.getItems().size(); i++) {
            Object new_user= listview_users.getItems().get(i); //on récupère tous les
            //créer un nouveau port pour la conversation
            // ajouter les utilisateurs à la liste des utilisateurs présents dans la conv
            String groupname = get_groupname();
            //récupérer la liste des utilisateurs dans listView
            //à faire
            //si rien n'est rentré dans le nom groupe, par défaut prénom des utilisateurs
            if (groupname==""){
            }
            ControleurCreateConversation controleurCreateConversation = new ControleurCreateConversation();
            try {
                Conversation new_conv = controleurCreateConversation.creerConversation(groupname);
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
}
