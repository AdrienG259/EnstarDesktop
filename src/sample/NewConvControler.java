package sample;
import client.ControleurCreateDeleteConversation;
import client.ControleurUser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;


import java.io.*;
import java.util.*;

import common.User;

public class NewConvControler {
    Parent root;
    @FXML TextField txtfield_users;
    @FXML Button btn_add;
    @FXML ListView listview_users;
    @FXML TextField txtfield_groupname;
    @FXML Button btn_create;
    @FXML Label label_feedback;

    private ControleurCreateDeleteConversation controleurCreateDeleteConversation;

    public void add_users() { //on ajoute tous les utilisateurs à une liste qui est retournée par la méthode add_users
        String buffer_users = txtfield_users.getText();
        //trouver un moyen de matcher le texte rentré avec une liste des utilisateurs
        ControleurUser control_user = new ControleurUser();
        User to_add = control_user.matchUser(buffer_users);
        if (to_add==null){
            label_feedback.setText("L'utilisateur rentré n'est pas valide");
        }
        else{
            listview_users.getItems().add(to_add);
            label_feedback.setText("");
        }


    }

    public void create_conv() throws IOException {
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
        controleurCreateDeleteConversation = new ControleurCreateDeleteConversation();
        try {
            controleurCreateDeleteConversation.creerConversation(buffer_gpname, membres);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }


        //une fois la conversation terminée on rouvre la fenêtre avec toute les conversations à jour avec la nouvelle
        Stage stage = (Stage) btn_create.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("connectedpage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        }
    }

