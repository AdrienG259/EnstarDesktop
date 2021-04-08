package server.Gestion;

import server.*;
import server.Protocoles.*;

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
                    case "updateUser" -> {
                        protocole = new ProtocoleUpdateUser();
                        reponse = "0";
                    }
                    case "matchUser" -> {
                        protocole = new ProtocoleMatchUser();
                        reponse = "0";
                    }
                    case "changePseudo" -> {
                        protocole = new ProtocoleChangePseudo();
                        reponse = "0";
                    }
                    case "changePassword" -> {
                        protocole = new ProtocoleChangePassword();
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

                /* On éxécute le protocole adéquat, déterminé par le switch case*/
                protocole.execute(aContext, anInputStream, anOutputStream);
                os.close(); is.close();

            }
        } catch ( IOException ioException) {
            System.err.println(" Problème d'exception IO sur un OutputStream");
            ioException.printStackTrace();
        }
    }

}
