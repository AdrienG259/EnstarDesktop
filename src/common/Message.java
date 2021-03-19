package common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {
    // Toutes les variables sont en private final (immuables)
    private final String contenu;
    private final String date;
    private final int expediteurID;

    public Message(String dataString, int expediteurID){
        this.contenu = dataString;
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Date d = new Date();
        this.date = df.format(d);
        this.expediteurID = expediteurID;
    }

    // pas de setter car à la création de message il faut supposer qu'il est immuable

    public String getContenu() {
        return contenu;
    }

//    public void setContenu(String contenu) {
//        this.contenu = contenu;
//    }

    public String getDate() {
        return date;
    }

//    public void setDate(String date) {
//        this.date = date;
//    }

    public int getExpediteur() {
        return expediteurID;
    }

//    public void setExpediteur(int expediteur) {
//        this.expediteurID = expediteur;
//    }
}
