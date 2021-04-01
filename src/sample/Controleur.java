package sample;

import client.Otomat;
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

import server.UpdateUser;
import server.VerifLogin;
import server.AddUser;
import server.AutorizedUser;
import sample.Gestionuserctrl;

public class Controleur {
    @FXML ImageView imgview_logo;
    @FXML TextField txtfield_login;
    @FXML TextField txtfiled_password;
    @FXML Button btn_connexion;
    @FXML Button btn_newaccount;
    @FXML Button btn_gestion;
    @FXML Label label_feedback;

    Parent root;

    private Otomat otomat;

    public AutorizedUser autorizedUsers = new AutorizedUser();

//    public Controleur(Otomat otomat) {
//        this.otomat = otomat;
//    }
//
//    public Otomat getOtomat(){
//        return this.otomat;
//    }

    public void try_connexion() throws IOException {
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

//            try {
//                File myObj = new File("connected_user.txt");
//                myObj.createNewFile();
//            } catch (IOException e) {
//                System.out.println("Err creat fichier");
//                e.printStackTrace();
//            }
//            try {
//                FileWriter myWriter = new FileWriter("connected_user.txt");
//                myWriter.write(txtfield_login.getText());
//                myWriter.close();
//            } catch (IOException e) {
//                System.out.println("Err w fichier");
//                e.printStackTrace();
//            }

            Stage stage = (Stage) btn_connexion.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("connectedpage.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

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
            System.out.println("Apres : ");
            System.out.println(autorizedUsers.userMap);
            UpdateUser updateUsers = new UpdateUser(autorizedUsers.userMap);
            updateUsers = null;
        }
        adduser = null;
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
            Gestionuserctrl tmp = new Gestionuserctrl();
            //tmp.refresh();
            tmp = null;
        } else {
            label_feedback.setText("Compte administrateur requis");
        }
    }
}

