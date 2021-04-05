package client;

import common.Message;

import java.io.Serializable;

public class ControleurConversation extends Controleur {

    private static final int port_GestionConversations = 10005;

    public ControleurConversation() {
        super(port_GestionConversations);
    }

    public String sendMessage(Message message){
        System.out.println("Envoi d'un message");
        monClientTCP.connecterAuServeur();
        String ret = monClientTCP.sendSerializableObject((Serializable) message);
        monClientTCP.deconnecterDuServeur();
        setChanged();
        notifyObservers();
        return ret;
    }

    public Message receiveMessage(){
        System.out.println("Reception d'un message");
        monClientTCP.connecterAuServeur();
        Message message = (Message) monClientTCP.receiveSerializable("please send me a message");
        monClientTCP.deconnecterDuServeur();
        setChanged();
        notifyObservers();
        return message;
    }
}
