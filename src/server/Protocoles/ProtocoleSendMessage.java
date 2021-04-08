package server.Protocoles;

import common.Conversation;
import common.User;
import server.IContext;
import server.IProtocole;
import serverFiles.InstantiateSerializable;

import java.io.*;

public class ProtocoleSendMessage implements IProtocole {

    @Override
    public void execute(IContext aContext, InputStream anInputStream, OutputStream anOutputStream) {
        //aContext pas encore utilisé car on sait pas encore ce que c'est : dépend des cas
        // protocole conversation : conversation etc ...
        /* Pour l'instant Protocole inutile car non lié à un contexte
         * Il faudrait que le morceau de code en dessous qui permet de récupérer un objet sérialisé soit mis dans
         * une classe spécialement créée*/

        Conversation conversation = (Conversation) aContext;
        String message;
        BufferedReader is = new BufferedReader(new InputStreamReader(
                anInputStream));

        try {
            if ((message = is.readLine()) != null) {
                int id = conversation.getID();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
