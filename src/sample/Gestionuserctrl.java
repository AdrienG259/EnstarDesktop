package sample;

import client.ControleurAdmin;
import client.ControleurUser;
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

    private ControleurUser controleurUser;
    private ControleurAdmin controleurAdmin;

    public void initialize(){
        refresh();
    }

    public void refresh(){
        lst_users.getItems().setAll();
        controleurAdmin = new ControleurAdmin();
        HashMap<String, Integer> loginUserIDMap = controleurAdmin.getLoginUserIDMap();
        HashMap<Integer, String> userIDPasswordMap = controleurAdmin.getUserIDPasswordMap();
        for (String login : loginUserIDMap.keySet()) {
            String pass = userIDPasswordMap.get(loginUserIDMap.get(login));
            String chain = login + " - " + pass;
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
        controleurUser = new ControleurUser();
        controleurUser.deleteUser(choix.split(" - ")[0]);
        controleurUser = null;
        refresh();
    }

    public void modif_pseudo(){
        controleurUser = new ControleurUser();
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

        controleurUser = new ControleurUser();
        controleurUser.deleteUser(pseudo);

        String new_pseudo = txt_name.getText();
        controleurUser.createUser(new_pseudo, pass);
        controleurUser = null;
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

        controleurUser = new ControleurUser();
        controleurUser.deleteUser(pseudo);

        String new_pass = txt_pass.getText();
        controleurUser.createUser(pseudo, new_pass);
        controleurUser = null;
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

        controleurUser = new ControleurUser();
        controleurUser.createUser(pseudo, pass);
        controleurUser = null;
        refresh();
    }

    public void quitter() throws IOException {
        Stage stage = (Stage) btn_quit.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("accueil.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}