package server.Protocoles;

import common.Conversation;
import common.User;
import server.Gestion.Messagerie;
import server.IContext;
import server.IProtocole;
import server.ServeurTCP;

import java.io.*;
import java.util.List;

public class ProtocoleDeleteConversation implements IProtocole {

    @Override
    public void execute(IContext aContext, InputStream anInputStream, OutputStream anOutputStream) {

        Messagerie messagerie = (Messagerie) aContext;
        String conversationIDString;
        BufferedReader is = new BufferedReader(new InputStreamReader(
                anInputStream));
        PrintStream os = new PrintStream(anOutputStream);

        try {
            if ((conversationIDString = is.readLine()) != null) {
                System.out.println(" Ordre Recu " + conversationIDString);
                int conversationID = Integer.parseInt(conversationIDString);
                ServeurTCP serveurTCPConversation = messagerie.getServeurs()[conversationID];
//                Conversation conversation = (Conversation) serveurTCPConversation.getContexte();
                serveurTCPConversation.interrupt();
                messagerie.getServeurs()[conversationID] = null;
                os.println(0);
            }
        } catch (IOException e) {
            System.out.println(" Pb d'exception ");
            e.printStackTrace();
        }

    }
}
