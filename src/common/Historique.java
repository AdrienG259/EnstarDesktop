package common;

import serverFiles.InstantiateSerializable;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Historique implements Serializable {

    private List<Message> listeMessages;

    public Historique(List<Message> messages) {
        listeMessages = messages;
    }

    public Historique() {
        listeMessages = new ArrayList<Message>();
    }

    public List<Message> getListeMessages(){
        return listeMessages;
    }

    public Message getLastMessage(){
        return listeMessages.get(listeMessages.size()-1);
    }

    public void setListeMessages(List<Message> listeMessages) {
        this.listeMessages = listeMessages;
    }

    public void addMessage(Message newMessage){
        // gestion du fichier à faire ici
        listeMessages.add(newMessage);
    }

    public void removeMessage(Message message){
        //gestion du fichier ici
        if(!listeMessages.remove(message)){
            System.err.println("Message from "+message.getExpediteur()+" dated "+message.getDate()+" not found");
        }
    }
}
