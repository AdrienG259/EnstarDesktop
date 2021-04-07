package server;

import common.User;
import serverFiles.InstantiateSerializable;

import java.io.*;

public class ProtocoleGestionPortUser implements IProtocole {
    @Override
    public void execute(IContext aContext, InputStream anInputStream, OutputStream anOutputStream) {
        String intentionClient;
        BufferedReader is = new BufferedReader(new InputStreamReader(
                anInputStream));

        try {
            if ((intentionClient = is.readLine()) != null) {

                IProtocole protocole;
                String reponse;

                switch (intentionClient) {
                    case "createUser" -> {
                        protocole = new ProtocoleCreateUser();
                        reponse = "0";
                    }
                    case "deleteUser" -> {
                        protocole = new ProtocoleDeleteUser();
                        reponse = "0";
                    }
                    case "getUser" -> {
                        protocole = new ProtocoleGetUser();
                        reponse = "0";
                    }
                    default -> {
                        protocole = new ProtocoleEchec();
                        reponse = "-2";
                    }
                }
                PrintStream os = new PrintStream(anOutputStream);
                /* On envoie la réponse */
                os.println(reponse);
                /* On ferme les os et is car utilisent anInputStream et anOutputStream qui vont être réutilisés dans
                le nouveau protocole qu'on va éxécuter*/
                os.close(); is.close();
                /* On éxécute le protocole adéquat, déterminé par le switch case*/
                protocole.execute(aContext, anInputStream, anOutputStream);

            }
        } catch ( IOException ioException) {
            System.err.println(" Problème d'exception IO sur un OutputStream");
            ioException.printStackTrace();
        }
    }

}
