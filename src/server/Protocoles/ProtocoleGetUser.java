package server.Protocoles;

import common.User;
import server.Gestion.Messagerie;
import server.IContext;
import server.IProtocole;
import serverFiles.InstantiateSerializable;

import java.io.*;

public class ProtocoleGetUser implements IProtocole {
    @Override
    public void execute(IContext aContext, InputStream anInputStream, OutputStream anOutputStream) {
        Messagerie messagerie = (Messagerie)aContext;
        String inputReq;
        BufferedReader is = new BufferedReader(new InputStreamReader(
                anInputStream));
        PrintStream os = new PrintStream(anOutputStream);
        try {

            if ((inputReq = is.readLine()) != null) {

                int userID = Integer.parseInt(inputReq.split(" ")[0]);

                ObjectOutputStream oos = new ObjectOutputStream(anOutputStream);

                try {
                    File userFile = new File("serverFiles/users/" + userID);
                    InstantiateSerializable<User> userInstantiate = new InstantiateSerializable<User>(userFile);
                    User user = userInstantiate.fileToInstance();
                    oos.writeObject(user);
                } catch (ClassNotFoundException classNotFoundException){
                    System.err.println("Can not instanciate user "+userID+" from file");
                    oos.writeObject(null);
                } finally {
                    oos.flush();
                    oos.close();
                }

            }
        } catch (Exception e) {
            System.out.println(" Pb d'exception ");
        }

    }
}
