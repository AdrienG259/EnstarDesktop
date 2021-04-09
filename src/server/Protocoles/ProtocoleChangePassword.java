package server.Protocoles;

import common.User;
import server.ActionUser;
import server.Gestion.Messagerie;
import server.IContext;
import server.IProtocole;
import serverFiles.InstantiateSerializable;

import java.io.*;

public class ProtocoleChangePassword implements IProtocole {

    /* Les classes présentes dans le package protocole ont pour but de répondre au client après que ce dernier a envoyé son intention
     * ici l'intention est de changer le mot de passe de l'utilisateur
     * le message renvoyé au client est alors un int 0 ou -1 selon la réussite de l'action
     * 0 le mot de passe est changé
     * -1 échec
     */

    @Override
    public void execute(IContext aContext, InputStream anInputStream, OutputStream anOutputStream) {
        Messagerie messagerie = (Messagerie)aContext;
        String parametres;
        BufferedReader is = new BufferedReader(new InputStreamReader(
                anInputStream));
        PrintStream os = new PrintStream(anOutputStream);
        try {

            //récupération du nouveau mot de passe dans newPassword
            if ((parametres = is.readLine()) != null) {
                String[] paramSplit = parametres.split(";");
                String pseudo = paramSplit[0];
                String newPassword = paramSplit[1];

                try {
                    //affectation du nouveau mot de passe grace à la méthode changePassword de actionUser
                    ActionUser actionUser = new ActionUser();
                    actionUser.changePassword(pseudo, newPassword);
//                    int userID = actionUser.getUserIDFromLogin(pseudo);
//                    actionUser.getUserIDPasswordMap().replace(userID, newPassword);
                    os.println("0");
                } catch (ClassNotFoundException classNotFoundException) {
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
