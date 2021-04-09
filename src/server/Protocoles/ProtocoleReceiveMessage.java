package server.Protocoles;

import common.Conversation;
import common.Historique;
import common.Message;
import server.IContext;
import server.IProtocole;

import java.io.*;

public class ProtocoleReceiveMessage implements IProtocole {

    /* Les classes présentes dans le package protocole ont pour but de répondre au client après que ce dernier a envoyé son intention
     * ici l'intention est prévenir le serveur qu'un message va être reçu
     * le message renvoyé au client est alors un int 0 ou -1 selon la réussite de l'action
     * 0 message reçu et historique mis à jour
     * -1 échec
     */

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
                //mise à jour de l'historique
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
