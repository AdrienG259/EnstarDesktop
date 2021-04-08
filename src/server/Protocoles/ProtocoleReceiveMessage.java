package server.Protocoles;

import common.Conversation;
import common.Historique;
import common.Message;
import server.IContext;
import server.IProtocole;

import java.io.*;

public class ProtocoleReceiveMessage implements IProtocole {
    @Override
    public void execute(IContext aContext, InputStream anInputStream, OutputStream anOutputStream) {
        /* ProtocoleSendMessage sert à ENVOYER le message côté serveur, reçu côté client par ControleurConversation
        dans la méthode receiveMessage.*/

        Conversation conversation = (Conversation) aContext;
        Historique historique;
        PrintStream os = new PrintStream(anOutputStream);

        try {
            ObjectInputStream ois = new ObjectInputStream(anInputStream);
            if ((historique = (Historique) ois.readObject()) != null) {
                conversation.setHistorique(historique);
                os.println("0");
            } else{
                os.println("-1");
            }
        } catch (IOException | ClassNotFoundException ioException) {
            ioException.printStackTrace();
        }
    }
}
