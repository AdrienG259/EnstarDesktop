package server.Protocoles;

import common.Conversation;
import common.Historique;
import server.ActionConversation;
import server.IContext;
import server.IProtocole;
import server.ServeurTCP;
import serverFiles.SharedVariableCannotAccess;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProtocoleGetConversations implements IProtocole {
    @Override
    public void execute(IContext aContext, InputStream anInputStream, OutputStream anOutputStream) {



        List<Integer> listIDConversation;
        BufferedReader is = new BufferedReader(new InputStreamReader(
                anInputStream));
        PrintStream os = new PrintStream(anOutputStream);

        try {
            ObjectInputStream ois = new ObjectInputStream(anInputStream);
            if ((listIDConversation = (ArrayList<Integer>)ois.readObject()) != null) {
                os.println("0");
                System.out.println(" Ordre Recu 333" + listIDConversation);

                ActionConversation actionConversation = new ActionConversation();
                ArrayList<Conversation> listConversation = new ArrayList<Conversation>() {
                };

                for(int i = 0; i < listIDConversation.size()-1; i++) {
                    listConversation.add(actionConversation.getConversationID(listIDConversation.get(i)));
                }

                ObjectOutputStream oos = new ObjectOutputStream(anOutputStream);
                oos.writeObject(listConversation);
                System.err.println(listConversation);
                oos.flush();

                os.println(0);
            } else {
                os.println("-1");
            }
        } catch (IOException | ClassNotFoundException | SharedVariableCannotAccess e) {
            System.out.println(" Pb d'exception ");
            e.printStackTrace();
        }
    }
}
