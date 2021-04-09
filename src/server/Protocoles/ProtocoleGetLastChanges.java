package server.Protocoles;

import common.Conversation;
import common.Historique;
import server.ActionConversation;
import server.IContext;
import server.IProtocole;

import java.io.*;
import java.util.ArrayList;

public class ProtocoleGetLastChanges implements IProtocole {
    @Override
    public void execute(IContext aContext, InputStream anInputStream, OutputStream anOutputStream) {

        Conversation conversation = (Conversation) aContext;

        String inputReq;
        BufferedReader is = new BufferedReader(new InputStreamReader(
                anInputStream));
        PrintStream os = new PrintStream(anOutputStream);

        try {

            if ((inputReq = is.readLine()) != null) {

                // on récupère les changements de la conversation
                ActionConversation actionConv = new ActionConversation();

                ObjectOutputStream oos = new ObjectOutputStream(anOutputStream);

                ArrayList<String> changes = actionConv.getLastChanges(conversation.getID());

                oos.writeObject(changes);

                // on renvoie les changements à l'utilisateur
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
