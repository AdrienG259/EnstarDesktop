package server.Protocoles;

import server.*;
import server.Gestion.Messagerie;
import serverFiles.SharedVariableCannotAccess;

import java.io.*;

public class ProtocoleCreateUser implements IProtocole {

    /* Les classes présentes dans le package protocole ont pour but de répondre au client après que ce dernier a envoyé son intention
    * ici l'intention est de créer un nouvel utilisateur à l'aide de son passeport et son login
    * le message renvoyé au client est alors un int 0 ou -1 selon la réussite de l'action
    * 0 l'user est ajouté à la HMap
    * -1 échec pendant l'ajout
    */

    @Override
    public void execute(IContext aContext, InputStream anInputStream, OutputStream anOutputStream) {

        Messagerie messagerie = (Messagerie)aContext;
        String inputReq;
        BufferedReader is = new BufferedReader(new InputStreamReader(
                anInputStream));
        PrintStream os = new PrintStream(anOutputStream);

        try {

            String messageRetour = "-1";

            if ((inputReq = is.readLine()) != null) {
                System.out.println(" Ordre Recu " + inputReq);

                /* on récupère les données login et password grâce à l'input du client */

                String[] loginpassword = inputReq.split(";");
                String login = loginpassword[0];
                String password = loginpassword[1];

                /* on utilise la méthode addUser de la classe ActionUser pour pouvoir faire l'ajout à la HMAP */

                ActionUser actionUser = new ActionUser();
                int can_add = actionUser.addUser(login, password);
                messageRetour = can_add + "\n";

                /* on renvoit 0 si l'utilisateur a été ajouté, -1 (String messae retour de base) sinon */

                os.println(messageRetour);
            }
        } catch (IOException | ClassNotFoundException | SharedVariableCannotAccess e) {
            e.printStackTrace();
            System.out.println(" Pb d'exception ");
        }
    }
}
