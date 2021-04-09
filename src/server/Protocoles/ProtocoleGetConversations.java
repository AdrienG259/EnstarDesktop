package server.Protocoles;

import common.Conversation;
import common.Historique;
import server.ActionConversation;
import server.IContext;
import server.IProtocole;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProtocoleGetConversations implements IProtocole {
    @Override
    public void execute(IContext aContext, InputStream anInputStream, OutputStream anOutputStream) {



        String inputReq;
        BufferedReader is = new BufferedReader(new InputStreamReader(
                anInputStream));
        PrintStream os = new PrintStream(anOutputStream);

        try {
            if (true) {
            //if ((inputReq = is.readLine()) != null)
            // Condition jamais verifiée
                ObjectInputStream ois = new ObjectInputStream(anInputStream);
                List<Integer> listIDConversation = (List<Integer>) ois.readObject();
                os.println("0");
                os.flush();
                is.readLine();

                ActionConversation actionConversation = new ActionConversation();
                List<Conversation> listConversation = new ArrayList<Conversation>() {
                };
                for(int i = 0; i < listIDConversation.size(); i++) {
                    listConversation.add(actionConversation.getConversationID(listIDConversation.get(i)));
                }
                ObjectOutputStream oos = new ObjectOutputStream(anOutputStream);
                oos.writeObject(listConversation);

                oos.flush();
//                oos.close();
            } else {
                os.println("-1");
            }
        } catch (Exception e) {
            System.out.println(" Pb d'exception ");
        }
    }
}
