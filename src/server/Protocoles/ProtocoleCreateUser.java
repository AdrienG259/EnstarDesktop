package server.Protocoles;

import server.*;
import server.Gestion.Messagerie;

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

                AutorizedUser autorizedUsers = new AutorizedUser();
                AddUser adduser = new AddUser(autorizedUsers, login, password);
                int can_add = adduser.ajouterUser();
                UpdateUser updateUser = new UpdateUser(autorizedUsers.userMap);
                messageRetour = can_add + "\n";
                autorizedUsers = null;
                adduser = null;
                updateUser = null;
                os.println(messageRetour);
            }
        } catch ( IOException e) {
            e.printStackTrace();
            System.out.println(" Pb d'exception ");
        }
    }
}
