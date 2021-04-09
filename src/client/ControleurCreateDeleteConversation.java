package client;

import common.Conversation;
import common.User;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class ControleurCreateDeleteConversation extends Controleur {

    private static final int port_GestionConversations = 10004;
    private desktopBusinessRules businessRules;


    public ControleurCreateDeleteConversation(){
        super(port_GestionConversations);
//        this.businessRules = businessRules;
    }

    public int creerConversation(String nomConversation, List<User> membres) throws Error, IOException {

        System.out.println("Création d'une nouvelle conversation");
        monClientTCP.connecterAuServeur();

        // On envoie d'abord l'intention au serveur
        String intention = "createConversation";
        String msgServer = monClientTCP.transmettreChaine(intention);

        // Si le serveur a bien reçu l'intention et qu'il n'y a pas eu d'erreur on transmet les infos de la conversation
        if (msgServer.equals("0")) {
            String ret = monClientTCP.transmettreChaine(nomConversation);
            if (ret.equals("0")) {
                String portString = monClientTCP.sendSerializableObject((Serializable) membres);
                int port = Integer.parseInt(portString);
                System.out.println("Demande de création transmise");
                setChanged();
                notifyObservers();
                System.out.println("Création terminée, port : " + port);
                monClientTCP.deconnecterDuServeur();
                return port;
            } else {
                System.err.println("Erreur lors de la demande de création");
                monClientTCP.deconnecterDuServeur();
                return -1;
            }
        } else {
            System.err.println("Erreur lors de la transmission de l'intention");
            monClientTCP.deconnecterDuServeur();
            return -1;
        }
    }

    public int deleteConversation(int idConversation){

        System.out.println("Suppression d'une nouvelle conversation");
        monClientTCP.connecterAuServeur();

        // On envoie d'abord l'intention au serveur
        String intention = "deleteConversation";
        String msgServer = monClientTCP.transmettreChaine(intention);

        // Si le serveur a bien reçu l'intention et qu'il n'y a pas eu d'erreur on transmet les infos de la conversation
        if (msgServer.equals("0")) {
            String ret = monClientTCP.transmettreChaine(String.valueOf(idConversation));
            monClientTCP.deconnecterDuServeur();
            return 0;

        } else {
            System.err.println("Erreur lors de la transmission de l'intention");
            monClientTCP.deconnecterDuServeur();
            return -1;
        }
    }

    public List<Conversation> getConversations(List<Integer> listIDConversation){
        String intention = "getConversations";
        monClientTCP.connecterAuServeur();
        String msgServer = monClientTCP.transmettreChaine(intention);

        List<Conversation> liste = new ArrayList<Conversation>();

        // Si le serveur a bien reçu l'intention et qu'il n'y a pas eu d'erreur on transmet le message
        if (msgServer.equals("0")) {
            String ret = monClientTCP.sendSerializableObject((Serializable) listIDConversation); //trouver ce qu'on doit avoir
            if (ret.equals("0")) {
               // liste = monClientTCP.receiveSerializable();
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