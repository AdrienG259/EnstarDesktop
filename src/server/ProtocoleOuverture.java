package server;

import java.io.*;
import server.AutorizedUser;
import server.VerifLogin;

public class ProtocoleOuverture implements IProtocole {
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
                int can_connect = 0;
                VerifLogin logger = new VerifLogin(autorizedUsers, login, password);
                can_connect = logger.comparaison();
                messageRetour= String.valueOf(can_connect);
                os.println(messageRetour);
            }
        } catch ( Exception e) {
            System.out.println(" Pb d'exception ");
        }
    }
}
