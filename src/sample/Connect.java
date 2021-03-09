package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import server.AutorizedUser;

import java.io.*;
import java.net.URL;
import java.util.*;

public class Connect{
    @FXML MenuBar menubar_menu;
    @FXML ChoiceBox choicebox_conversations;
    @FXML ListView lstview_users;
    @FXML ScrollBar scrollbar_left;
    @FXML TextArea txtarea_currentconv;
    @FXML TextField txtfield_msg;
    @FXML Button btn_envoyer;

    public AutorizedUser autorizedUsers = new AutorizedUser();

    public void initialize() {
        System.out.println("msgri");
    }
}
