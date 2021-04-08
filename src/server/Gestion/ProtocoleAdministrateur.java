package server.Gestion;

import server.ActionUser;
import server.IContext;
import server.IProtocole;

import java.io.*;

public class ProtocoleAdministrateur implements IProtocole {
    @Override
    public void execute(IContext aContext, InputStream anInputStream, OutputStream anOutputStream) {
        String inputReq;
        BufferedReader is = new BufferedReader(new InputStreamReader(
                anInputStream));
        PrintStream os = new PrintStream(anOutputStream);
        try {
            String messageRetour;
            if ((inputReq = is.readLine()) != null) {

                System.out.println(" Ordre Recu " + inputReq);

                switch (inputReq){ // 1 seul cas mais on peut supposer qu'il y en aura plus
                    case "getLoginUserIDMap":
                        try{
                            sendLoginUserIDMap(os);
                            messageRetour = "0";
                        }catch(ClassNotFoundException classNotFoundException){
                            classNotFoundException.printStackTrace();
                            messageRetour = "-2";
                        }
                        break;
                    case "getUserIDPasswordMap":
                        try{
                            sendUserIDPasswordMap(os);
                            messageRetour = "0";
                        }catch(ClassNotFoundException classNotFoundException){
                            classNotFoundException.printStackTrace();
                            messageRetour = "-2";
                        }
                        break;
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
    private void sendLoginUserIDMap(OutputStream outputStream) throws IOException, ClassNotFoundException {
        ObjectOutputStream oos = new ObjectOutputStream(outputStream);
        ActionUser actionUser = new ActionUser();
        oos.writeObject(actionUser.getLoginUserIDMap());
        oos.flush();
        oos.close();
    }

    private void sendUserIDPasswordMap(OutputStream outputStream) throws IOException, ClassNotFoundException {
        ObjectOutputStream oos = new ObjectOutputStream(outputStream);
        ActionUser actionUser = new ActionUser();
        oos.writeObject(actionUser.getUserIDPasswordMap());
        oos.flush();
        oos.close();
    }
}
