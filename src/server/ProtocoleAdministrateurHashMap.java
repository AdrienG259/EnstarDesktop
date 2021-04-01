package server;

import java.io.*;

public class ProtocoleAdministrateurHashMap implements IProtocole {
    @Override
    public void execute(IContext aContext, InputStream anInputStream, OutputStream anOutputStream) {
        String inputReq;
        BufferedReader is = new BufferedReader(new InputStreamReader(
                anInputStream));
        PrintStream os = new PrintStream(anOutputStream);
        try {
            String messageRetour = "-1";
            if ((inputReq = is.readLine()) != null) {

                System.out.println(" Ordre Recu " + inputReq);

                AutorizedUser autorizedUsers = new AutorizedUser();

                ObjectOutputStream oos = new ObjectOutputStream(anOutputStream);
                oos.writeObject(autorizedUsers.userMap);
                oos.flush();
                oos.close();

                os.println(messageRetour);
            }
        } catch ( Exception e) {
            System.out.println(" Pb d'exception ");
        }
    }
}
