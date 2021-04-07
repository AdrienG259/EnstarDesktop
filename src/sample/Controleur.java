package sample;

import client.ControleurCreationUser;
import client.ControleurConnexion;
import client.ControleurUser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.*;

public class Controleur {
    @FXML ImageView imgview_logo;
    @FXML TextField txtfield_login;
    @FXML TextField txtfiled_password;
    @FXML Button btn_connexion;
    @FXML Button btn_newaccount;
    @FXML Button btn_gestion;
    @FXML Label label_feedback;

    Parent root;

    private ControleurConnexion controleurConnexion;
    private ControleurUser controleurUser;

    public void try_connexion() throws IOException {
        controleurConnexion = new ControleurConnexion();
        String log = txtfield_login.getText();
        String pass = txtfiled_password.getText();
        label_feedback.setText("");

        if(log.equals("")){
            txtfield_login.setPromptText("Saisir un login");
            return;
        }
        if(pass.equals("")){
            txtfiled_password.setPromptText("Saisir un mot de passe");
            return;
        }

        int can_connect = controleurConnexion.connexionUtilisateur(log, pass);
        System.out.println(can_connect);
        if(can_connect == 0){
            label_feedback.setText("Identifiants incorrects");

        } else if (can_connect == 1){
            label_feedback.setText("Ok");

            Stage stage = (Stage) btn_connexion.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("connectedpage.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } else if (can_connect == -1){
            label_feedback.setText("Identifiant non trouve");
        }
        controleurConnexion = null;
    }

    public void create_account(){
        String log = txtfield_login.getText();
        String pass = txtfiled_password.getText();
        label_feedback.setText("");

        if(log.equals("")){
            txtfield_login.setPromptText("Saisir un login");
            return;
        }
        if(pass.equals("")){
            txtfiled_password.setPromptText("Saisir un mot de passe");
            return;
        }
        int can_add = 0;
        controleurUser = new ControleurUser();
        can_add = controleurUser.createUser(log, pass);
        if (can_add == -1){
            label_feedback.setText("Utilisateur deja existant");
        } else if(can_add == 1) {
            label_feedback.setText("Utilisateur ajoute");
        }
        controleurUser = null;
    }

    public void gestion() throws IOException {
        String log = txtfield_login.getText();
        label_feedback.setText("");

        if(log.equals("admin")){
            Stage stage = (Stage) btn_connexion.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("Gestionuser.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            label_feedback.setText("Compte administrateur requis");
        }
    }
}

