package sample;
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
import server.AutorizedUser;

import java.io.*;
import java.net.URL;
import java.util.*;
import common.Message;

public class NewConvControler {
    @FXML TextField txtfield_users;
    @FXML Button btn_add;

    String buffer_users=txtfield_users.getText();



    public void verify_users(){
        //vérifier que l'utilisateur rentré correspond bien à la base de données
    }

    public void create_conv(){

    }

}
