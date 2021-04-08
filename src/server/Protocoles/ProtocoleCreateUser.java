package server.Protocoles;

import server.*;
import server.Gestion.Messagerie;
import serverFiles.SharedVariableCannotAccess;

import java.io.*;

public class ProtocoleCreateUser implements IProtocole {
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
                int can_add = actionUser.addUser(login, password);
                messageRetour = can_add + "\n";
                os.println(messageRetour);
            }
        } catch (IOException | ClassNotFoundException | SharedVariableCannotAccess e) {
            e.printStackTrace();
            System.out.println(" Pb d'exception ");
        }
    }
}
