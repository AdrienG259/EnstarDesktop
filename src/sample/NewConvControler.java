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
import serverFiles.SharedVariableCannotAccess;
import serverFiles.SharedVariables;

public class NewConvControler {
    Parent root;
    @FXML TextField txtfield_users;
    @FXML Button btn_add;
    @FXML ListView listview_users;
    @FXML TextField txtfield_groupname;
    @FXML Button btn_create;
    @FXML Button btn_annuler;
    @FXML Label label_feedback;

    private ControleurCreateDeleteConversation controleurCreateDeleteConversation;
    private ControleurUser ctrl_user;
    private String current_user_log;
    private User current_user;
    private List<User> membres;



    public void initialize() throws IOException {
        SharedVariables sharedVariables = new SharedVariables("clientFiles/sharedVariables");
        ctrl_user = new ControleurUser();
        try{
            current_user_log = sharedVariables.accessVariable("current_user_log");
            current_user = ctrl_user.matchUser(current_user_log);
            membres = new ArrayList<User>();
            membres.add(current_user);
            listview_users.getItems().add(current_user);

        } catch (SharedVariableCannotAccess sharedVariableCannotAccess) {
            sharedVariableCannotAccess.printStackTrace();
        }
    }

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

        for (int i = 0; i < listview_users.getItems().size(); i++) {
            User new_user = (User) listview_users.getItems().get(i); //on récupère tous les users
            //créer un nouveau port pour la conversation
            // ajouter les utilisateurs à la liste des utilisateurs présents dans la conv
            membres.add(new_user);
        }
        //si rien n'est rentré dans le nom groupe, par défaut prénom des utilisateurs
        if (membres.size() == 2 || buffer_gpname == "") { //
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

        public void annuler() throws IOException {
            Stage stage = (Stage) btn_create.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("connectedpage.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

        public void des_chiffres_et_delete(){
        User to_del = (User) listview_users.getSelectionModel().getSelectedItem();
            for (int i = 0; i < listview_users.getItems().size(); i++) {
                if (listview_users.getItems().get(i).equals(current_user)){
                    label_feedback.setText("Tu ne peux pas tu supprimer toi\nmeme, chien de la case");
                    break;
                }
                if (listview_users.getItems().get(i).equals(to_del)){
                    listview_users.getItems().remove(i);
                    break;
                }
            }
        }
    }

