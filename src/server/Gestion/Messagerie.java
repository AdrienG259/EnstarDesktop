package server.Gestion;

import common.Conversation;
import server.*;

import java.util.Observable;

public class Messagerie extends Observable implements IContext, IMessagerie {

    private static final int port_connexion = 10001;
    private static final int port_GestionUser = 10002;
    private static final int port_admin = 10003;
    private static final int port_GestionConversations = 10004;

    public ServeurTCP[] serveurs = new ServeurTCP[20000];

    public ServeurTCP[] getServeurs() {
        return serveurs;
    }

    public Messagerie(){};

    public void ouvrirMessagerie(){

        // On initialise les serveurs basiques sur les ports correspondants
        serveurs[port_connexion] = new ServeurTCP(this, new ProtocoleConnexion(), port_connexion);
        serveurs[port_GestionUser] = new ServeurTCP(this, new ProtocoleGestionPortUser(), port_GestionUser);
        serveurs[port_admin] = new ServeurTCP(this, new ProtocoleAdministrateur(), port_admin);
        serveurs[port_GestionConversations] = new ServeurTCP(this, new ProtocoleGestionPortConversation(), port_GestionConversations);

        // On ouvre l'ensemble des serveurs
        for(ServeurTCP s : serveurs){
            if (s != null) {
                s.start();
            }
        }
    }

    @Override
    public void addConversation(Conversation newConversation, int port) {

        serveurs[port] = new ServeurTCP(newConversation, new ProtocoleGestionPortConversation(), port);
        this.notifyObservers(); //est-ce que c'est bon ?
    }
}
