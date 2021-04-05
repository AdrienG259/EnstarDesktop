package server;

import common.Conversation;

import java.util.ArrayList;
import java.util.Observable;

public class Messagerie extends Observable implements IContext, IMessagerie{

    private static final int port_conv_generale = 6666;
    private static final int port_conv_privee_1 = 7777;
    private static final int port_GestionUser = 10002;
//    private static int port_createUser = 10002;
//    private static int port_deleteUser = 10003;
    private static final int port_admin = 10004;
    private static final int port_GestionConversations = 10005;

    public ArrayList<ServeurTCP> serveurs = new ArrayList<>(20000);

    public Messagerie(){};

    public void ouvrirMessagerie(){
        serveurs.set(port_conv_generale, new ServeurTCP(this, new ProtocoleServeurGroupe(), port_conv_generale));
        serveurs.set(port_conv_privee_1, new ServeurTCP(this, new ProtocoleServeurPrivee(), port_conv_privee_1));
        int port_connexion = 10001;
        serveurs.set(port_connexion, new ServeurTCP(this, new ProtocoleConnexion(), port_connexion));
        serveurs.set(port_GestionUser, new ServeurTCP(this, new ProtocoleUser(), port_GestionUser));
//        serveurs.set(port_createUser, new ServeurTCP(this, new ProtocoleCreateUser(), port_createUser));
//        serveurs.set(port_deleteUser, new ServeurTCP(this, new ProtocoleDeleteUser(), port_deleteUser));
        serveurs.set(port_admin, new ServeurTCP(this, new ProtocoleAdministrateur(), port_admin));
        for(ServeurTCP s : serveurs){
            s.start();
        }
    }

    @Override
    public void addConversation(Conversation newConversation, int port) {
        serveurs.set(newConversation.getID(), new ServeurTCP(this, new ProtocoleServeurGroupe(), port_conv_generale));
        this.notifyObservers(); //est-ce que c'est bon ?
    }
}
