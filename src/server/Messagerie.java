package server;

import common.Conversation;

import java.util.ArrayList;
import java.util.Observable;

public class Messagerie extends Observable implements IContext, IMessagerie{

    private static final int port_connexion = 10001;
    private static final int port_GestionUser = 10002;
    private static final int port_admin = 10003;
    private static final int port_GestionConversations = 10004;

//    public ArrayList<ServeurTCP> serveurs = new ArrayList<>(20000);
    public ServeurTCP[] serveurs = new ServeurTCP[20000];

    public Messagerie(){};

    public void ouvrirMessagerie(){
        serveurs[port_connexion] = new ServeurTCP(this, new ProtocoleConnexion(), port_connexion);
        serveurs[port_GestionUser] = new ServeurTCP(this, new ProtocoleGestionPortUser(), port_GestionUser);
//        serveurs.set(port_createUser, new ServeurTCP(this, new ProtocoleCreateUser(), port_createUser));
//        serveurs.set(port_deleteUser, new ServeurTCP(this, new ProtocoleDeleteUser(), port_deleteUser));
        serveurs[port_admin] = new ServeurTCP(this, new ProtocoleAdministrateur(), port_admin);
        for(ServeurTCP s : serveurs){
            if (s != null) {
                s.start();
            }
        }
    }

    @Override
    public void addConversation(Conversation newConversation, int port) {
//        serveurs.set(newConversation.getID(), new ServeurTCP(this, new ProtocoleServeurGroupe(), port_conv_generale));
        this.notifyObservers(); //est-ce que c'est bon ?
    }
}
