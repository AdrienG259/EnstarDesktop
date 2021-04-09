package server.Protocoles;

import common.User;
import server.Gestion.Messagerie;
import server.IContext;
import server.IProtocole;
import serverFiles.InstantiateSerializable;

import java.io.*;

public class ProtocoleGetUser implements IProtocole {

    /* Les classes présentes dans le package protocole ont pour but de répondre au client après que ce dernier a envoyé son intention
     * ici l'intention est de récupérer l'objet utilisateur et d'instancier le fichier users
     */

    @Override
    public void execute(IContext aContext, InputStream anInputStream, OutputStream anOutputStream) {
        Messagerie messagerie = (Messagerie)aContext;
        String inputReq;
        BufferedReader is = new BufferedReader(new InputStreamReader(
                anInputStream));
        PrintStream os = new PrintStream(anOutputStream);
        try {

            if ((inputReq = is.readLine()) != null) {

                //récupération de l'ID de l'user
                int userID = Integer.parseInt(inputReq.split(" ")[0]);

                ObjectOutputStream oos = new ObjectOutputStream(anOutputStream);

                try {
                    //modification du fichier users
                    File userFile = new File("serverFiles/users/" + userID);
                    InstantiateSerializable<User> userInstantiate = new InstantiateSerializable<User>(userFile);
                    User user = userInstantiate.fileToInstance();
                    oos.writeObject(user);
                } catch (ClassNotFoundException classNotFoundException){
                    System.err.println("Can not instanciate user "+userID+" from file");
                    oos.writeObject(null);
                } finally {
                    oos.flush();
//                    oos.close();
                }

            } else {
                os.println("-1");
            }
        } catch (Exception e) {
            System.out.println(" Pb d'exception ");
            e.printStackTrace();
        }

    }
}
