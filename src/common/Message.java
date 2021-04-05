package common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {
    // Toutes les variables sont en private final (immuables)
    private final String contenu;
    private final String date;
    private final int expediteurID;

    public Message(String dataString, int expediteurID, String stringDate){
        this.contenu = dataString;
        this.date = stringDate;
        this.expediteurID = expediteurID;
    }

    public Message(String dataString, int expediteurID){
        this(dataString, expediteurID, (new SimpleDateFormat("yyyyMMdd_HHmmss")).format(new Date()));
    }


    public String getContenu() {
        return contenu;
    }

    public String getDate() {
        return date;
    }

    public int getExpediteur() {
        return expediteurID;
    }


    public String toString(){
        String stringMessage = "";
        stringMessage += date + " - ";
        stringMessage += expediteurID + " - ";
        stringMessage += contenu ;
        return stringMessage;
    }
}
