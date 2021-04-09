package server.Protocoles;

import client.ControleurConversation;
import common.Conversation;
import server.ActionConversation;
import server.IContext;
import server.IProtocole;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProtocoleGetNomConversation implements IProtocole {
    @Override
    public void execute(IContext aContext, InputStream anInputStream, OutputStream anOutputStream) {

        Conversation conversation = (Conversation) aContext;

        String nomConversation;
        BufferedReader is = new BufferedReader(new InputStreamReader(
                anInputStream));
        PrintStream os = new PrintStream(anOutputStream);

        try {

            if ((nomConversation = is.readLine()) != null) {

                ActionConversation actionConversation = new ActionConversation();
                conversation.setNomGroupe(nomConversation);
                actionConversation.saveNom(conversation);
                os.println("0");
            }else {
                os.println("-1");
            }
        } catch (Exception e) {
            System.out.println(" Pb d'exception ");
            e.printStackTrace();
        }
    }
}
