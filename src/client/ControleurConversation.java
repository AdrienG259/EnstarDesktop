package client;

import common.Conversation;
import common.Historique;
import common.Message;

import java.io.Serializable;
import java.util.*;

public class ControleurConversation extends Controleur {

    private static final int port_GestionConversations = 10004;

    public ControleurConversation() {
        super(port_GestionConversations);
    }

    public String sendMessage(Message message, Conversation conversation){

        System.out.println("Envoi d'un message");
        monClientTCP.connecterAuServeur();

        // On envoie d'abord l'intention au serveur
        String intention = "sendMessage";
        String msgServer = monClientTCP.transmettreChaine(intention);

        // Si le serveur a bien reçu l'intention et qu'il n'y a pas eu d'erreur on transmet le message
        if (msgServer == "0") {
            String ret = monClientTCP.sendSerializableObject((Serializable) message);
            if (ret == "0") {
                System.out.println("Message transmis");
//                conversation.getHistorique().addMessage(message);
                Historique newHistorique = getHistorique(conversation);
                conversation.setHistorique(newHistorique);
                monClientTCP.deconnecterDuServeur();
                setChanged();
                notifyObservers();
                System.out.println("Envoi terminé");
                return ret;
            } else {
                System.out.println("Erreur lors de la transmission du message");
            }
        } else {
            System.out.println("Erreur lors de la transmission de l'intention");
        }

        // On se déconnecte et on informe les observateurs qu'un message a été transmis
        monClientTCP.deconnecterDuServeur();
        return "Le message n'a pas pu être envoyé";
    }

    public Message receiveMessage(){
        System.out.println("Reception d'un message");
        monClientTCP.connecterAuServeur();
        Message message = (Message) monClientTCP.receiveSerializable("Please send me a message");
        monClientTCP.deconnecterDuServeur();
        setChanged();
        notifyObservers();
        return message;
    }

    public Historique getHistorique(Conversation uneConversation){

        //je suis pas sûre duuuuuuu tout
        System.out.println("demande affichage historique");
        monClientTCP.connecterAuServeur();

        String intention = "getHistorique";
        String msgServer = monClientTCP.transmettreChaine(intention);
        Historique ret;

        // Si le serveur a bien reçu l'intention et qu'il n'y a pas eu d'erreur on transmet le message
        if (msgServer.equals("0")) {
            ret = (Historique)monClientTCP.receiveSerializable("");
        } else {
            System.out.println("Erreur lors de la transmission de l'intention");
            ret = null;
        }

        // On se déconnecte et on informe les observateurs qu'un message a été transmis
        monClientTCP.deconnecterDuServeur();
        setChanged();
        notifyObservers();
        System.out.println("Envoi terminé");

        return ret;
    }
    public List<Conversation> getConversation(List<Integer> listIDConversation){
        String intention = "getConversation";
        String msgServer = monClientTCP.transmettreChaine(intention);

        List<Conversation> liste=new ArrayList<Conversation>();

        // Si le serveur a bien reçu l'intention et qu'il n'y a pas eu d'erreur on transmet le message
        if (msgServer.equals("0")) {
            String ret = monClientTCP.sendSerializableObject((Serializable) listIDConversation); //trouver ce qu'on doit avoir
            if (ret.equals("0")) {
                System.out.println("Message transmis");
            } else {
                System.out.println("Erreur lors de la transmission du message");
            }
        } else {
            System.out.println("Erreur lors de la transmission de l'intention");
        }

        // On se déconnecte et on informe les observateurs qu'un message a été transmis
        monClientTCP.deconnecterDuServeur();
        setChanged();
        notifyObservers();
        System.out.println("Envoi terminé");

        return liste;
    }
}
