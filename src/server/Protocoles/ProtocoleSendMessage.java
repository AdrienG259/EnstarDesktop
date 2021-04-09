package server.Protocoles;

import common.Conversation;
import common.Message;
import common.User;
import server.IContext;
import server.IProtocole;
import serverFiles.InstantiateSerializable;

import java.io.*;

public class ProtocoleSendMessage implements IProtocole {

    /* Les classes présentes dans le package protocole ont pour but de répondre au client après que ce dernier a envoyé son intention
     * ici l'intention est prévenir de l'envoi d'un message pour s'assurer de la mise à jour de l'historique
     * le message renvoyé au client est alors un int 0 ou -1 selon la réussite de l'action
     * 0 le message est envoyé et l'historique mis à jour
     * -1 échec
     */

    @Override
    public void execute(IContext aContext, InputStream anInputStream, OutputStream anOutputStream) {
        /* ProtocoleSendMessage sert à RECEVOIR  le message côté serveur, envoyé côté client par ControleurConversation
        dans la méthode sendMessage.*/

        Conversation conversation = (Conversation) aContext;
        Message message;
        PrintStream os = new PrintStream(anOutputStream);

        try {
            ObjectInputStream ois = new ObjectInputStream(anInputStream);
            if ((message = (Message) ois.readObject()) != null) {
                //ajout du message à l'historique
                conversation.getHistorique().addMessage(message);
                os.println("0");
            } else{
                os.println("-1");
            }
        } catch (IOException | ClassNotFoundException ioException) {
            ioException.printStackTrace();
        }
    }
}
