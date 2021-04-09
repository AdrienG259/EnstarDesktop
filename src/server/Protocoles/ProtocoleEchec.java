package server.Protocoles;

import server.IContext;
import server.IProtocole;

import java.io.*;

public class ProtocoleEchec implements IProtocole {

    /* Les classes présentes dans le package protocole ont pour but de répondre au client après que ce dernier a envoyé son intention
     * ici l'intention est de montrer l'échec de l'action
     * le message renvoyé au client est alors un int 0 ou -1 selon la réussite de l'action
     * ici le protocole renvoit systematiquement -1 car il s'agit d'un protocole d'échec
     */

    @Override
    public void execute(IContext aContext, InputStream anInputStream, OutputStream anOutputStream) {
        String inputReq;
        BufferedReader is = new BufferedReader(new InputStreamReader(
                anInputStream));
        PrintStream os = new PrintStream(anOutputStream);
        try {
            String messageRetour = "-1\n";
            if ((inputReq = is.readLine()) != null) {
                System.out.println(" Ordre Recu " + inputReq);
                os.println(messageRetour);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" Pb d'exception ");
        }
    }
}
