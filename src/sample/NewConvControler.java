package sample;
import common.Conversation;
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
import org.w3c.dom.Text;
import server.AutorizedUser;

import java.io.*;
import java.net.URL;
import java.util.*;
import common.Message;
import common.User;

public class NewConvControler {
    @FXML
    TextField txtfield_users;
    @FXML
    Button btn_add;
    @FXML
    ListView listview_users;
    @FXML
    TextField txtfield_groupname;


    public void add_users() {
        String buffer_users = txtfield_users.getText();
        if (verify_user(buffer_users)) {
            listview_users.getItems().add(buffer_users);

        }

    }

    public boolean verify_user(String User) {
        //vérifier que l'utilisateur rentré correspond bien à la base de données
        if (true) {
            return true;//l'utilisateur rentré est bien dans la base de données
        }
        return false;
    }

    public void create_conv() {
        for (int i = 0; i < listview_users.getItems().size(); i++) {
            listview_users.getItems();
            //créer un nouveau port pour la conversation
            // ajouter les utilisateurs à la liste des utilisateurs présents dans la conv
            String buffer_groupname = txtfield_groupname.getText();
            //si rien n'est rentré dans le nom groupe, par défaut prénom des utilisateurs
            Conversation new_conv = new Conversation(buffer_groupname, null);

            //une fois la conversation terminée on rouvre la fenêtre avec toute les conversations
//            Stage stage = (Stage) btn_new_conv.getScene().getWindow();
//            root = FXMLLoader.load(getClass().getResource("createNewConv.fxml"));
//            Scene scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
        }


    }
}
