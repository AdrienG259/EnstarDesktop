package server.Protocoles;

import server.Gestion.Messagerie;
import server.IContext;
import server.IProtocole;

import java.io.*;

public class ProtocoleNewConversation implements IProtocole {

    @Override
    public void execute(IContext aContext, InputStream anInputStream, OutputStream anOutputStream) {

        Messagerie messagerie = (Messagerie)aContext;
        String inputReq;
        BufferedReader is = new BufferedReader(new InputStreamReader(
                anInputStream));
        PrintStream os = new PrintStream(anOutputStream);
        
        
    }
}
