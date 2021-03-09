package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import server.VerifLogin;
import server.AddUser;
import server.AutorizedUser;

public class Controleur {
    @FXML ImageView imgview_logo;
    @FXML TextField txtfield_login;
    @FXML TextField txtfiled_password;
    @FXML Button btn_connexion;
    @FXML Button btn_newaccount;
    @FXML Label label_feedback;

    public AutorizedUser autorizedUsers = new AutorizedUser();

    public void try_connexion(){
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

        int can_connect = 0;
        VerifLogin logger = new VerifLogin(autorizedUsers, log, pass);
        can_connect = logger.comparaison();
        if(can_connect == 0){
            label_feedback.setText("Identifiants incorrects");
        } else if (can_connect == 1){
            label_feedback.setText("Ok");
        } else if (can_connect == -1){
            label_feedback.setText("Identifiant non trouve");
        }
        logger = null;
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
        AddUser adduser = new AddUser(autorizedUsers, log, pass);
        can_add = adduser.ajouterUser();
        System.out.println("Avant : ");
        System.out.println(autorizedUsers.userMap);
        if (can_add == -1){
            label_feedback.setText("Utilisateur deja existant");
        } else if(can_add == 1) {
            label_feedback.setText("Utilisateur ajoute");
            System.out.println("Après : ");
            System.out.println(autorizedUsers.userMap);
        }
        adduser = null;
    }
}

