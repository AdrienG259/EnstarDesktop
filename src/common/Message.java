package common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {
    // Toutes les variables sont en private final (immuables)
    private final String contenu;
    private final String date;
    private final int expediteurID;
    private final int id;

    public Message(String dataString, int expediteurID, int id, String stringDate){
        this.contenu = dataString;
        this.date = stringDate;
        this.expediteurID = expediteurID;
        this.id = id;
    }

    public Message(String dataString, int expediteurID, int id){
        this(dataString, expediteurID, id, (new SimpleDateFormat("yyyyMMdd_HHmmss")).format(new Date()));
    }

    public Message(String dataString, int expediteurID){
        this.contenu = dataString;
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Date d = new Date();
        this.date = df.format(d);
        this.expediteurID = expediteurID;
        this.id = newID();
    }
    // pas de setter car à la création de message il faut supposer qu'il est immuable

    private int newID(){
        // get new ID for a new Message of the conversation
        /*TO-DO*/
        return 0;
    }

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
    public int getID(){
        return id;
    }

    public int getExpediteur() {
        return expediteurID;
    }

//    public void setExpediteur(int expediteur) {
//        this.expediteurID = expediteur;
//    }

    public String toString(){
        String stringMessage = "";
        stringMessage += Integer.toString(id) + " - ";
        stringMessage += Integer.toString(expediteurID) + " - ";
        stringMessage += date + " - ";
        stringMessage += contenu ;
        return stringMessage;
    }
}
