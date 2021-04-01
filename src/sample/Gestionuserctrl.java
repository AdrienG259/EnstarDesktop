package sample;

import client.OtomatAdmin;
import client.OtomatDelete;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.*;
import java.util.HashMap;

import server.UpdateUser;
import server.VerifLogin;
import server.AddUser;
import server.AutorizedUser;
import server.DeleteUser;

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

    public AutorizedUser autorizedUsers = new AutorizedUser();
    private OtomatDelete otomatDelete;
    private OtomatAdmin otomatAdmin;

    public void initialize(){
        refresh();
    }

    public void refresh(){
        lst_users.getItems().setAll();
        otomatAdmin = new OtomatAdmin(10004);
        HashMap<String, String> userMap = otomatAdmin.getuserMap();
        for (String i : userMap.keySet()) {
            //System.out.println("key: " + i + " value: " + autorizedUsers.userMap.get(i));
            String chain = i + " - " + userMap.get(i);
            lst_users.getItems().add(chain);
        }
        feedback.setText("");
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
        otomatDelete = new OtomatDelete(10003);
        otomatDelete.deleteUtilisateur(choix.split(" - ")[0]);
        refresh();
        otomatDelete = null;
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
        DeleteUser del = new DeleteUser(autorizedUsers, pseudo);
        del.SupprimerUser();
        del = null;

        String new_pseudo = txt_name.getText();
        AddUser adduser = new AddUser(autorizedUsers, new_pseudo, pass);
        adduser.ajouterUser();
        adduser = null;

        UpdateUser updateUsers = new UpdateUser(autorizedUsers.userMap);
        updateUsers = null;
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
        DeleteUser del = new DeleteUser(autorizedUsers, pseudo);
        del.SupprimerUser();
        del = null;

        String new_pass = txt_pass.getText();
        AddUser adduser = new AddUser(autorizedUsers, pseudo, new_pass);
        adduser.ajouterUser();
        adduser = null;

        UpdateUser updateUsers = new UpdateUser(autorizedUsers.userMap);
        updateUsers = null;
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

        AddUser adduser = new AddUser(autorizedUsers, pseudo, pass);
        adduser.ajouterUser();
        adduser = null;

        UpdateUser updateUsers = new UpdateUser(autorizedUsers.userMap);
        updateUsers = null;
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