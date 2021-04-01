package server;

import common.Conversation;

import java.util.ArrayList;
import java.util.Observable;

public class Messagerie extends Observable implements IContext, IMessagerie{

    // équivalent de la classe Banque du TD3 pour ref
    // c'est la classe qui se carge d'ouvrir les serveurs
    // un serveur = une conversation

    private int port_conv_generale = 6666;
    private int port_conv_privee_1 = 7777;
    private static int port_ouverture = 10001;

    public ArrayList<ServeurTCP> serveurs = new ArrayList<>();

    private IProtocole protocole;

    public Messagerie(){};

    public void ouvrirMessagerie(){
        serveurs.add(new ServeurTCP(this, new ProtocoleServeurGroupe(), port_conv_generale));
        serveurs.add(new ServeurTCP(this, new ProtocoleServeurPrivee(), port_conv_privee_1));
        serveurs.add(new ServeurTCP(this, new ProtocoleOuverture(), port_ouverture));
        for(ServeurTCP s : serveurs){
            s.start();
        }
    }

    @Override
    public void addConversation(Conversation newConversation, int port) {
        serveurs.add(new ServeurTCP(this, new ProtocoleServeurGroupe(), port_conv_generale));
        this.notifyObservers(); //est-ce que c'est bon ?
    }
}
