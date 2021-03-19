package server;

import common.Conversation;
import common.Message;

import java.io.*;

public class IProtocoleMessage implements IProtocole {
    @Override
    public void execute(IContext aContext, InputStream inputStream, OutputStream outputStream) {
        Serializable serializableObject = null;
        try {
            // on reçoit un objet sérialisé
            ObjectInputStream ois = new ObjectInputStream(inputStream);
            serializableObject = (Serializable)ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException ioException) {
            ioException.printStackTrace();
        }
        if (serializableObject != null){
            // on ajoute le message à l'historique
            Conversation conversation = ((Conversation) aContext);
            conversation.getHistorique().addMessage((Message) serializableObject);
            // on notifie les observateurs de la conversation
            conversation.notifyAll();
        }

    }
}
