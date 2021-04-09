package server.Protocoles;

import common.Conversation;
import common.User;
import server.Gestion.Messagerie;
import server.IContext;
import server.IProtocole;
import server.ServeurTCP;

import java.io.*;
import java.util.List;

public class ProtocoleDeleteConversation implements IProtocole {

    /* Les classes présentes dans le package protocole ont pour but de répondre au client après que ce dernier a envoyé son intention
     * ici l'intention est de supprimer une conversation (une conversation étant un objet comprenant un numéro d'identification, la liste des membres, un historique et le nom du groupe
     * le message renvoyé au client est alors un int 0 ou -1 selon la réussite de l'action
     * 0 la conversation est supprimée
     * -1 échec
     */

    @Override
    public void execute(IContext aContext, InputStream anInputStream, OutputStream anOutputStream) {

        Messagerie messagerie = (Messagerie) aContext;
        String conversationIDString;
        BufferedReader is = new BufferedReader(new InputStreamReader(
                anInputStream));
        PrintStream os = new PrintStream(anOutputStream);

        try {
            if ((conversationIDString = is.readLine()) != null) {
                System.out.println(" Ordre Recu " + conversationIDString);
                //récupération du numéro d'identification de a conversation
                int conversationID = Integer.parseInt(conversationIDString);
                //identification du serveur hôte de la conversation grace au ID
                ServeurTCP serveurTCPConversation = messagerie.getServeurs()[conversationID];
//                Conversation conversation = (Conversation) serveurTCPConversation.getContexte();
                //suppression de la conversation et de son serveur
                serveurTCPConversation.interrupt();
                messagerie.getServeurs()[conversationID] = null;
                os.println(0);
            }
        } catch (IOException e) {
            System.out.println(" Pb d'exception ");
            e.printStackTrace();
        }

    }
}
