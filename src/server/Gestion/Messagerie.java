package server.Gestion;

import common.Conversation;
import common.User;
import server.*;
import server.Gestion.ProtocoleGestionConversations;

import java.util.List;
import java.util.Observable;

public class Messagerie extends Observable implements IContext, IMessagerie {

    private static final int port_connexion = 10001;
    private static final int port_GestionUser = 10002;
    private static final int port_admin = 10003;
    private static final int port_CreationConversation = 10004;
    private static final int nombrePorts = 20000;

    public ServeurTCP[] serveurs = new ServeurTCP[nombrePorts];

    public ServeurTCP[] getServeurs() {
        return serveurs;
    }

    public Messagerie(){}

    public void ouvrirMessagerie(){

        // On initialise les serveurs basiques sur les ports correspondants
        serveurs[port_connexion] = new ServeurTCP(this, new ProtocoleConnexion(), port_connexion);
        serveurs[port_GestionUser] = new ServeurTCP(this, new ProtocoleGestionPortUser(), port_GestionUser);
        serveurs[port_admin] = new ServeurTCP(this, new ProtocoleAdministrateur(), port_admin);
        serveurs[port_CreationConversation] = new ServeurTCP(this, new ProtocoleGestionConversations(), port_CreationConversation);

        // On ouvre l'ensemble des serveurs
        for (ServeurTCP s : serveurs) {
            if (s != null) {
                s.start();
            }
        }
    }

    @Override
    public void addConversation(Conversation newConversation, int port) {

        // On ajoute le nouveau ServeurTCP sur le port souhaité
        serveurs[port] = new ServeurTCP(newConversation, new ProtocoleGestionPortConversation(), port);

        // On ouvre le serveur
        serveurs[port].start();

        List<User> membres = newConversation.getMembres();
        for (User m : membres) {
            List<Integer> listConv = m.getIDConversations();
            listConv.add(newConversation.getID());
            m.setListIDConversations(listConv);
        }

        // On notifie les observateurs
        this.notifyObservers();
    }

    public int getNewPort(){

        // Cette méthode a pour but de fournir un numéro de port non utilisé pour créer les nouvelles conversations
        for (int i = 0; i < nombrePorts; i++) {
            if (serveurs[i] == null){
                return i;
            }
        }
        return -1;
    }
}
