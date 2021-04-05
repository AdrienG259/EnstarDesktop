package client;

import common.Conversation;
import common.Message;
import common.User;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Observable;


public class ControleurCreateConversation extends Controleur {

    private static final int port_GestionConversations = 10005;
    private desktopBusinessRules businessRules;


    public ControleurCreateConversation(){
        super(port_GestionConversations);
//        this.businessRules = businessRules;
    }

    public Conversation creerConversation(String nomConversation) throws Error, IOException {
        System.out.println("Création d'une nouvelle conversation");
        String ret = monClientTCP.transmettreChaineConnexionPonctuelle("creerConversation");
        int newPort = Integer.parseInt(ret);
        if (newPort==-1){
            throw new Error("Can't find a port on which open a new covnersation");
        }
        Conversation newConversation = new Conversation(nomConversation, Arrays.asList(businessRules.getCurrentUser()), newPort);
        businessRules.getListConversations().add(newConversation);
        setChanged();
        notifyObservers();
        return newConversation;
    }
}
