package server.Protocoles;

import common.Conversation;
import common.User;
import server.ActionUser;
import server.Gestion.Messagerie;
import server.IContext;
import server.IProtocole;
import serverFiles.SharedVariableAlreadyExists;
import serverFiles.SharedVariableCannotAccess;

import java.io.*;
import java.util.List;

public class ProtocoleNewConversation implements IProtocole {

    /* Les classes présentes dans le package protocole ont pour but de répondre au client après que ce dernier a envoyé son intention
     * ici l'intention est de créer une nouvelle conversation
     * le message renvoyé au client est alors un int 0 ou -1 selon la réussite de l'action
     * 0 la nouvelle conversation a été créée
     * -1 échec
     */

    @Override
    public void execute(IContext aContext, InputStream anInputStream, OutputStream anOutputStream) {

        Messagerie messagerie = (Messagerie) aContext;
        String nomConversation;
        BufferedReader is = new BufferedReader(new InputStreamReader(
                anInputStream));
        PrintStream os = new PrintStream(anOutputStream);

        try {
            /* on récupère les données : d'abord nomConversation*/
            if ((nomConversation = is.readLine()) != null) {
                System.out.println(" Ordre Recu " + nomConversation);
                os.println("0");
                /* On reçoit la liste d'utilisateurs */
                ObjectInputStream ois = new ObjectInputStream(anInputStream);
                List<User> membres = (List<User>) ois.readObject();

                /* On cherche un nouveau port */
                int newPort = messagerie.getNewPort();
                System.err.println(newPort);
                os.println(newPort);
                if (newPort != -1) {
                    /* Si  le port est admissible*/
                    messagerie.addConversation(new Conversation(nomConversation, membres, newPort), newPort);
                }
            } else {
                os.println("-1");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(" Pb d'exception ");
            e.printStackTrace();
        }

    }
}
