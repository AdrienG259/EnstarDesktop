package server;

import java.io.*;

public class ProtocoleDelete implements IProtocole {
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

                String login = inputReq;

                AutorizedUser autorizedUsers = new AutorizedUser();
                DeleteUser del = new DeleteUser(autorizedUsers, login);
                del.SupprimerUser();
                UpdateUser updateUser = new UpdateUser(autorizedUsers.userMap);
                autorizedUsers = null;
                updateUser = null;
                del = null;
                os.println(messageRetour);
            }
        } catch (Exception e) {
            System.out.println(" Pb d'exception ");
        }
    }
}
