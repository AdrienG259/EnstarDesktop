package server.Protocoles;

import common.Conversation;
import common.Historique;
import common.User;
import server.IContext;
import server.IProtocole;
import serverFiles.InstantiateSerializable;

import java.io.*;

public class ProtocoleGetHistorique implements IProtocole {
    @Override
    public void execute(IContext aContext, InputStream anInputStream, OutputStream anOutputStream) {

        Conversation conversation = (Conversation) aContext;

        String inputReq;
        BufferedReader is = new BufferedReader(new InputStreamReader(
                anInputStream));
        PrintStream os = new PrintStream(anOutputStream);

        try {

            if ((inputReq = is.readLine()) != null) {

                Historique historique = conversation.getHistorique();

                ObjectOutputStream oos = new ObjectOutputStream(anOutputStream);

                oos.writeObject(historique);

                oos.flush();
                oos.close();
            }
        } catch (Exception e) {
            System.out.println(" Pb d'exception ");
        }
    }
}
