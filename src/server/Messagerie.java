package server;

import common.Conversation;
import common.ConversationGroupe;

import java.util.ArrayList;
import java.util.Observable;

public class Messagerie extends Observable implements IContext, IMessagerie{

    // équivalent de la classe Banque du TD3 pour ref
    // c'est la classe qui se carge d'ouvrir les serveurs
    // un serveur = une conversation

    private int port_conv_generale = 6666;
    private int port_conv_privee_1 = 7777;

    public ArrayList<ServeurTCP> serveurs = new ArrayList<>();

    private IProtocole protocole;

    public Messagerie(){};

    public void ouvrirMessagerie(){
        serveurs.add(new ServeurTCP(this, new ProtocoleServeurGroupe(), port_conv_generale));
        serveurs.add(new ServeurTCP(this, new ProtocoleServeurPrivee(), port_conv_privee_1));
        for(ServeurTCP s : serveurs){
            s.start();
        }
    }

    @Override
    public void addConversation(Conversation newConversation, int port) {
        serveurs.add(new ServeurTCP(this, new ProtocoleServeurGroupe(), port_conv_generale));
    }

    public void addConversation(ConversationGroupe newConversation, int port) {
        serveurs.add(new ServeurTCP(this, new ProtocoleServeurGroupe(), port_conv_generale));
    }
    //s'il faut rajouter des fonctions  : les déclarer dans l'interface !
}
