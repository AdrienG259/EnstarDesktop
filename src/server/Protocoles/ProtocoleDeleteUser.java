package server.Protocoles;

import server.*;
import server.Gestion.Messagerie;

import java.io.*;

public class ProtocoleDeleteUser implements IProtocole {

    /* Les classes présentes dans le package protocole ont pour but de répondre au client après que ce dernier a envoyé son intention
     * ici l'intention est de supprimer un utilisateur à l'aide de son password et son login
     * le message renvoyé au client est alors un int 0 ou -1 selon la réussite de l'action
     * 0 l'user est retiré de la HMap
     * -1 échec pendant la suppression
     */

    @Override
    public void execute(IContext aContext, InputStream anInputStream, OutputStream anOutputStream) {

        //Messagerie messagerie = (Messagerie)aContext;
        String inputReq;
        BufferedReader is = new BufferedReader(new InputStreamReader(
                anInputStream));
        PrintStream os = new PrintStream(anOutputStream);
        try {
            String messageRetour = "-1\n";
            if ((inputReq = is.readLine()) != null) {
                System.out.println(" Ordre Recu " + inputReq);

                String login = inputReq;

                /* on utilise la méthode deleteUser de la classe ActionUser pour pouvoir faire la suppression à la HMAP
                * on récupère l'ID de l'utilisateur (sur la messagerie) à l'aide de son login et de la méthode getUserID */

                ActionUser actionUser = new ActionUser();
                actionUser.deleteUser(actionUser.getUserIDFromLogin(login));

                os.println(messageRetour);
            }
        } catch (Exception e) {
            System.out.println(" Pb d'exception ");
            e.printStackTrace();
        }
    }
}
