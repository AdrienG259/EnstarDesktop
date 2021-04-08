package server.Protocoles;

import server.*;
import server.Gestion.Messagerie;

import java.io.*;

public class ProtocoleDeleteUser implements IProtocole {
    @Override
    public void execute(IContext aContext, InputStream anInputStream, OutputStream anOutputStream) {
        Messagerie messagerie = (Messagerie)aContext;
        String inputReq;
        BufferedReader is = new BufferedReader(new InputStreamReader(
                anInputStream));
        PrintStream os = new PrintStream(anOutputStream);
        try {
            String messageRetour = "-1\n";
            if ((inputReq = is.readLine()) != null) {
                System.out.println(" Ordre Recu " + inputReq);

                String login = inputReq;

                ActionUser actionUser = new ActionUser();
                actionUser.deleteUser(actionUser.getUserIDFromLogin(login));

                os.println(messageRetour);
            }
        } catch (Exception e) {
            System.out.println(" Pb d'exception ");
        }
    }
}
