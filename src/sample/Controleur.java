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

public class Controleur {
    @FXML ImageView imgview_logo;
    @FXML TextField txtfield_login;
    @FXML TextField txtfiled_password;
    @FXML Button btn_connexion;
    @FXML Label label_feedback;

    public void try_connexion(){
        String log = txtfield_login.getText();
        String pass = txtfiled_password.getText();
        label_feedback.setText("");

        if(log.equals("")){
            txtfield_login.setPromptText("Saisir un login");
        }
        if(pass.equals("")){
            txtfiled_password.setPromptText("Saisir un mot de passe");
        }
        if(!log.equals("") && !pass.equals("")) {
            System.out.println(log + pass);
        }

        Boolean can_connect = false;
        //ClassLog logger = new ClassLog();
        //can_connect = logger.log(log, pass);
        if(!can_connect){
            label_feedback.setText("Identifiants incorrects");
        } else {

        }
    }
}

