package server;

import java.io.*;

public class ProtocoleAdministrateur implements IProtocole {
    @Override
    public void execute(IContext aContext, InputStream anInputStream, OutputStream anOutputStream) {
        String inputReq;
        BufferedReader is = new BufferedReader(new InputStreamReader(
                anInputStream));
        PrintStream os = new PrintStream(anOutputStream);
        try {
            String messageRetour = "0";
            if ((inputReq = is.readLine()) != null) {

                System.out.println(" Ordre Recu " + inputReq);

                switch (inputReq){
                    case "userMap": sendUserMap(os);
                    default: messageRetour = "-1";
                }

                os.println(messageRetour);
                os.close();
            }
        } catch (IOException ioException) {
            System.err.println("Problème d'exception IO sur un OutputStream");
            ioException.printStackTrace();
        }
    }
    private void sendUserMap(OutputStream outputStream) throws IOException {
        AutorizedUser autorizedUsers = new AutorizedUser();
        ObjectOutputStream oos = new ObjectOutputStream(outputStream);
        oos.writeObject(autorizedUsers.userMap);
        oos.flush();
        oos.close();
    }
}
