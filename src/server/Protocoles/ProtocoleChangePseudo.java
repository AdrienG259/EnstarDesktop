package server.Protocoles;

import common.User;
import server.ActionUser;
import server.Gestion.Messagerie;
import server.IContext;
import server.IProtocole;
import serverFiles.InstantiateSerializable;

import java.io.*;

public class ProtocoleChangePseudo implements IProtocole {

    /* Les classes présentes dans le package protocole ont pour but de répondre au client après que ce dernier a envoyé son intention
     * ici l'intention est de changer le pseudo de l'utilisateur
     * le message renvoyé au client est alors un int 0 ou -1 selon la réussite de l'action
     * 0 le pseudi est changé
     * -1 échec
     */

    @Override
    public void execute(IContext aContext, InputStream anInputStream, OutputStream anOutputStream) {
//        Messagerie messagerie = (Messagerie)aContext;
        String parametres;
        BufferedReader is = new BufferedReader(new InputStreamReader(
                anInputStream));
        PrintStream os = new PrintStream(anOutputStream);
        try {

            //récupération du nouvea pseudo avec un bufferedReader
            if ((parametres = is.readLine()) != null) {
                String[] paramSplit = parametres.split(";");
                String pseudo = paramSplit[0];
                String newPseudo = paramSplit[1];

                try {
                    //implémentation du nouveau pseudo avec la méthode changePseudo de la classe actionUser
                    ActionUser actionUser = new ActionUser();
                    actionUser.changePseudo(pseudo, newPseudo);

                    os.println("0");
                } catch (ClassNotFoundException classNotFoundException) {
                    //on retourne -1 en cas d'échec
                    System.err.println("Can not instanciate user with login " + pseudo + " from file");
                    os.println("-1");
                }
            }
        } catch (Exception e) {
            System.out.println(" Pb d'exception ");
            e.printStackTrace();
        }

    }
}
