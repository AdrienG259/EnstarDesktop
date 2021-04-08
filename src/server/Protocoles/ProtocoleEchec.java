package server.Protocoles;

import server.IContext;
import server.IProtocole;

import java.io.*;

public class ProtocoleEchec implements IProtocole {
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
