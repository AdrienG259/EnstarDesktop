package sample;

import client.ControleurAdmin;
import client.ControleurCreationUser;
import client.ControleurDeleteUser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.*;
import java.util.HashMap;

public class Gestionuserctrl {
    @FXML ListView lst_users;
    @FXML Button btn_rename;
    @FXML Button btn_delete;
    @FXML Button btn_pass;
    @FXML Button btn_quit;
    @FXML Button btn_create;
    @FXML TextField txt_name;
    @FXML TextField txt_pass;
    @FXML Label feedback;

    Parent root;

    private ControleurDeleteUser controleurDeleteUser;
    private ControleurAdmin controleurAdmin;
    private ControleurCreationUser controleurCreationUser;

    public void initialize(){
        refresh();
    }

    public void refresh(){
        lst_users.getItems().setAll();
        controleurAdmin = new ControleurAdmin();
        HashMap<String, String> userMap = controleurAdmin.getuserMap();
        for (String i : userMap.keySet()) {
            String chain = i + " - " + userMap.get(i);
            lst_users.getItems().add(chain);
        }
        feedback.setText("");
        controleurAdmin = null;
    }

    public void fill_fields(){
        String choix = (String) lst_users.getSelectionModel().getSelectedItem();
        if(choix == null){
            return;
        }
        String[] splitted = choix.split(" - ");
        String pseudo = splitted[0];
        String pass = splitted[1];
        txt_name.setPromptText(pseudo);
        txt_pass.setPromptText(pass);
    }

    public void delete_usr(){
        String choix = (String) lst_users.getSelectionModel().getSelectedItem();
        if(choix == null){
            feedback.setText("Selectionner un utilisateur.");
            return;
        }
        controleurDeleteUser = new ControleurDeleteUser();
        controleurDeleteUser.deleteUtilisateur(choix.split(" - ")[0]);
        refresh();
        controleurDeleteUser = null;
    }

    public void modif_pseudo(){
        String choix = (String) lst_users.getSelectionModel().getSelectedItem();
        if(choix == null){
            feedback.setText("Selectionner un utilisateur.");
            return;
        }
        if(txt_name.getText() == null){
            feedback.setText("Renseigner un pseudo");
            return;
        }
        String[] splitted = choix.split(" - ");
        String pseudo = splitted[0];
        String pass = splitted[1];

        controleurDeleteUser = new ControleurDeleteUser();
        controleurDeleteUser.deleteUtilisateur(pseudo);
        controleurDeleteUser = null;

        String new_pseudo = txt_name.getText();
        controleurCreationUser = new ControleurCreationUser();
        controleurCreationUser.creationUtilisateur(new_pseudo, pass);
        controleurCreationUser = null;

        refresh();
    }

    public void modif_pass(){
        String choix = (String) lst_users.getSelectionModel().getSelectedItem();
        if(choix == null){
            feedback.setText("Selectionner un utilisateur.");
            return;
        }
        if(txt_pass.getText() == null){
            feedback.setText("Renseigner un mot de passe");
            return;
        }

        String[] splitted = choix.split(" - ");
        String pseudo = splitted[0];

        controleurDeleteUser = new ControleurDeleteUser();
        controleurDeleteUser.deleteUtilisateur(pseudo);
        controleurDeleteUser = null;

        String new_pass = txt_pass.getText();
        controleurCreationUser = new ControleurCreationUser();
        controleurCreationUser.creationUtilisateur(pseudo, new_pass);
        controleurCreationUser = null;

        refresh();
    }

    public void create(){
        if(txt_name.getText() == null){
            feedback.setText("Renseigner un pseudo");
            return;
        }
        if(txt_pass.getText() == null){
            feedback.setText("Renseigner un mot de passe");
            return;
        }
        String pseudo = txt_name.getText();
        String pass = txt_pass.getText();

        controleurCreationUser = new ControleurCreationUser();
        controleurCreationUser.creationUtilisateur(pseudo, pass);
        controleurCreationUser = null;

        refresh();
    }

    public void quitter() throws IOException {
        Stage stage = (Stage) btn_quit.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("acceuil.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}