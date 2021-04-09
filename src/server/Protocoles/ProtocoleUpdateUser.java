package server.Protocoles;

import server.*;
import server.Gestion.Messagerie;

import java.io.*;

public class ProtocoleUpdateUser implements IProtocole {

    /* Les classes présentes dans le package protocole ont pour but de répondre au client après que ce dernier a envoyé son intention
     * ici l'intention est de mettre à jour l'utilisateur
     * le message renvoyé au client est alors un int 0 ou -1 selon la réussite de l'action
     * 0 la mise à jour a été reussie
     * -1 échec
     */

    @Override
    public void execute(IContext aContext, InputStream anInputStream, OutputStream anOutputStream) {

        Messagerie messagerie = (Messagerie)aContext;
        String inputReq;
        BufferedReader is = new BufferedReader(new InputStreamReader(
                anInputStream));


        try {

            String messageRetour = "-1";

            if ((inputReq = is.readLine()) != null) {
                System.out.println("Ordre Reçu : " + inputReq);

                //récupération et affectation de nouveau login et password
                String[] loginpassword = inputReq.split(";");
                String login = loginpassword[0];
                String password = loginpassword[1];

            }
        } catch ( IOException e) {
            e.printStackTrace();
            System.out.println(" Pb d'exception ");
        }
    }
}
