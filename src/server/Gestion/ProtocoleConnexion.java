package server.Gestion;

import java.io.*;

import server.ActionUser;
import server.IContext;
import server.IProtocole;

public class ProtocoleConnexion implements IProtocole {
    @Override
    public void execute(IContext aContext, InputStream anInputStream, OutputStream anOutputStream) {
        Messagerie messagerie = (Messagerie)aContext;
        String inputReq;
        BufferedReader is = new BufferedReader(new InputStreamReader(
                anInputStream));
        PrintStream os = new PrintStream(anOutputStream);
        try {
            String messageRetour = "-1";
            if ((inputReq = is.readLine()) != null) {
                System.out.println(" Ordre Recu " + inputReq);

                String[] loginpassword = inputReq.split(";");
                String login = loginpassword[0];
                String password = loginpassword[1];

                ActionUser actionUser = new ActionUser();
                int can_connect =actionUser.comparaison(login ,password);
                messageRetour= can_connect +"\n";
                os.println(messageRetour);
            }
        } catch ( Exception e) {
            System.out.println(" Pb d'exception ");
        }
    }
}
