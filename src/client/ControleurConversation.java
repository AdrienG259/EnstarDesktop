package client;

import common.Conversation;
import common.Historique;
import common.Message;
import common.User;
import server.ActionConversation;
import serverFiles.SharedVariableCannotAccess;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ControleurConversation extends Controleur implements Runnable{

    private final int portConversation;

    public ControleurConversation(int portConversation) {
        super(portConversation);
        this.portConversation = portConversation;
    }

    public String sendMessage(Message message){

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
                Historique newHistorique = getHistorique();
                ActionConversationClient actionConversationClient = null;
                try {
                    actionConversationClient = new ActionConversationClient();
                    Conversation conversation = actionConversationClient.getConversationID(portConversation);
                    conversation.setHistorique(newHistorique);
                    actionConversationClient.saveHistorique(conversation);
                } catch (IOException | SharedVariableCannotAccess | ClassNotFoundException e) {
                    e.printStackTrace();
                }

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

    public Historique getHistorique(){

        //je suis pas sûre duuuuuuu tout
        System.out.println("demande affichage historique");
        monClientTCP.connecterAuServeur();

        String intention = "getHistorique";
        String retIntention = monClientTCP.transmettreChaine(intention);
        int entierRetIntention = Integer.parseInt(retIntention);
        Historique retHist;

        // Si le serveur a bien reçu l'intention et qu'il n'y a pas eu d'erreur on transmet le message
        if (entierRetIntention == 0) {
            retHist = (Historique)monClientTCP.receiveSerializable("");
        } else {
            System.out.println("Erreur lors de la transmission de l'intention");
            retHist = null;
        }

        // On se déconnecte et on informe les observateurs qu'un message a été transmis
        monClientTCP.deconnecterDuServeur();
        setChanged();
        notifyObservers();
        System.out.println("Envoi terminé");

        return retHist;
    }

    private List<Integer> getListIDMembres(){
        /* TODO */
        /* Faire le côté protocole */
        String intention = "getListMembres";
        String retIntention = monClientTCP.transmettreChaine(intention);

        List<Integer> listIDMembres;

        // Si le serveur a bien reçu l'intention et qu'il n'y a pas eu d'erreur on transmet le message
        if (retIntention.equals("0")) {
            String parametres = "";
            listIDMembres = (List<Integer>) monClientTCP.receiveSerializable(parametres);
            return listIDMembres;
        } else {
            System.err.println("Erreur lors de la transmission de l'intention "+intention);
            listIDMembres = null;
        }

        // On se déconnecte et on informe les observateurs qu'un message a été transmis
        monClientTCP.deconnecterDuServeur();
        setChanged();
        notifyObservers();
        System.out.println("Envoi terminé");

        return listIDMembres;

    }

    private List<String> getListDatesLastChanges(){
        /* TODO */
        /* Faire le côté protocole */
        String intention = "getListDatesLastChanges";
        String retIntention = monClientTCP.transmettreChaine(intention);

        List<String> listDatesLastChanges;

        // Si le serveur a bien reçu l'intention et qu'il n'y a pas eu d'erreur on transmet le message
        if (retIntention.equals("0")) {
            String parametres = "";
            listDatesLastChanges = (List<String>) monClientTCP.receiveSerializable(parametres);
            return listDatesLastChanges;
        } else {
            System.err.println("Erreur lors de la transmission de l'intention "+intention);
            listDatesLastChanges = null;
        }

        // On se déconnecte et on informe les observateurs qu'un message a été transmis
        monClientTCP.deconnecterDuServeur();
        setChanged();
        notifyObservers();
        System.out.println("Envoi terminé");

        return listDatesLastChanges;

    }

    private String getNomConversation(){
        /* TODO */
        /* Faire le côté protocole */
        String intention = "getNomConversation";
        String retIntention = monClientTCP.transmettreChaine(intention);

        String nomConversation;

        // Si le serveur a bien reçu l'intention et qu'il n'y a pas eu d'erreur on transmet le message
        if (retIntention.equals("0")) {
            String parametres = "";
//            listDatesLastChanges = (List<String>) monClientTCP.receiveSerializable(parametres);
            nomConversation = monClientTCP.transmettreChaine("");
            return nomConversation;

        } else {
            System.err.println("Erreur lors de la transmission de l'intention "+intention);
            nomConversation = null;
        }

        // On se déconnecte et on informe les observateurs qu'un message a été transmis
        monClientTCP.deconnecterDuServeur();
        setChanged();
        notifyObservers();
        System.out.println("Envoi terminé");

        return nomConversation;

    }


//    public void sendListDates(){
//
//        monClientTCP.connecterAuServeur();
//
//        String intention = "sendListDatesLastChanges";
//        String retIntention = monClientTCP.transmettreChaine(intention);
//        int entierRetIntention = Integer.parseInt(retIntention);
//        Historique retHist;
//
//        // Si le serveur a bien reçu l'intention et qu'il n'y a pas eu d'erreur on transmet le message
//        if (entierRetIntention == 0) {
//            retHist = (Historique)monClientTCP.receiveSerializable("");
//        } else {
//            System.out.println("Erreur lors de la transmission de l'intention");
//            retHist = null;
//        }
//
//        // On se déconnecte et on informe les observateurs qu'un message a été transmis
//        monClientTCP.deconnecterDuServeur();
//        setChanged();
//        notifyObservers();
//        System.out.println("Envoi terminé");
//
//        return retHist;
//    }

    @Override
    public void run() {
        Timer timer = new Timer();
        TimerTask update = new TimerTask() {
            @Override
            public void run() {
                notificationMethod();
            }
        };
        timer.scheduleAtFixedRate(update, 500, 1000);
    }
    
    public void notificationMethod(){
        List<String> listDatesLastChangesServer = getListDatesLastChanges();
        ActionConversationClient actionConversationClient = null;
        System.out.println("[" + portConversation + "] - Task Timer on Fixed Rate");
        try {
            actionConversationClient = new ActionConversationClient();
            List<String> listDatesLastChangesClient = actionConversationClient.getListDatesLastChanges(portConversation);
            assert listDatesLastChangesServer != null;
            List<Boolean> datesBoolean = compareLists(listDatesLastChangesServer, listDatesLastChangesClient);

            Conversation conversation= actionConversationClient.getConversationID(portConversation);
            if (datesBoolean.get(0)){ // si la date de l'historique locale est inférieure à celle du serveur
                Historique newHistorique = getHistorique();
                conversation.setHistorique(newHistorique);
                actionConversationClient.saveHistorique(conversation);
            }
            else if (datesBoolean.get(1)){ // si la date de la liste des membres locale est inférieure à celle du serveur
                List<Integer> listMembres = getListIDMembres();
                List<User> membres = new ArrayList<User>();
                ControleurUser controleurUser = new ControleurUser();
                for (int id : listMembres){
                    membres.add(controleurUser.getUser(id));
                }
                conversation.setMembres(membres);
                actionConversationClient.saveMembres(conversation);
            }
            else if (datesBoolean.get(2)){ // si la date du nom de groupe locale est inférieure à celle du serveur
                String nomConversation = getNomConversation();

            }

        } catch (IOException | ParseException | SharedVariableCannotAccess | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private List<Boolean> compareLists(List<String> listDates1, List<String> listDates2) throws ParseException {
        List<Boolean> booleanList = new ArrayList<>(3);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        for (int i = 0; i < listDates1.size() - 1; i++) {
            String dateString1 = listDates1.get(i);
            String dateString2 = listDates2.get(i);
            Date date1 = simpleDateFormat.parse(dateString1);
            Date date2 = simpleDateFormat.parse(dateString2);
            // si la date est inférieure à 0 la date1 est antérieure à la date2
            booleanList.set(i, date1.compareTo(date2) < 0);
        }
        return booleanList;
    }
}
