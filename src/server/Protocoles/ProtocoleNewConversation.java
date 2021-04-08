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

    @Override
    public void execute(IContext aContext, InputStream anInputStream, OutputStream anOutputStream) {

        Messagerie messagerie = (Messagerie) aContext;
        String nomConversation;
        BufferedReader is = new BufferedReader(new InputStreamReader(
                anInputStream));
        PrintStream os = new PrintStream(anOutputStream);

        try {

            String messageRetour;

            /* on récupère les données : d'abord nomConversation*/
            if ((nomConversation = is.readLine()) != null) {
                System.out.println(" Ordre Recu " + nomConversation);
                /* On reçoit la liste d'utilisateurs */
                ObjectInputStream ois = new ObjectInputStream(anInputStream);
                List<User> membres = (List<User>) ois.readObject();

                /* On cherche un nouveau port */
                int newPort = messagerie.getNewPort();
                os.println(newPort);
                if (newPort != -1) {
                    /* Si  le port est admissible*/
                    messagerie.addConversation(new Conversation(nomConversation, membres, newPort), newPort);
                    os.println(newPort);
                } else{
                    os.println("-1");
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(" Pb d'exception ");
            e.printStackTrace();
        }

    }
}
