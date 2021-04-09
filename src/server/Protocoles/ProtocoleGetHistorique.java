package server.Protocoles;

import common.Conversation;
import common.Historique;
import common.User;
import server.IContext;
import server.IProtocole;
import serverFiles.InstantiateSerializable;

import java.io.*;

public class ProtocoleGetHistorique implements IProtocole {

    /* Les classes présentes dans le package protocole ont pour but de répondre au client après que ce dernier a envoyé son intention
     * ici l'intention est de récupérer l'historique d'une conversation en particulier
     * et de le retransmettre au client
     */

    @Override
    public void execute(IContext aContext, InputStream anInputStream, OutputStream anOutputStream) {

        //notre contexte est  ici une conversation
        Conversation conversation = (Conversation) aContext;

        String inputReq;
        BufferedReader is = new BufferedReader(new InputStreamReader(
                anInputStream));
        PrintStream os = new PrintStream(anOutputStream);

        try {

            if ((inputReq = is.readLine()) != null) {

                //on récupère l'historique de la conversation
                Historique historique = conversation.getHistorique();

                ObjectOutputStream oos = new ObjectOutputStream(anOutputStream);

                oos.writeObject(historique);

                //renvoit de l'historique au client
                oos.flush();
//                oos.close();
            } else {
                os.println("-1");
            }
        } catch (Exception e) {
            System.out.println(" Pb d'exception ");
            e.printStackTrace();
        }
    }
}
