package server.Gestion;

import server.IContext;
import server.IProtocole;
import server.Protocoles.*;

import java.io.*;

public class ProtocoleGestionConversations implements IProtocole {

    @Override
    public void execute(IContext aContext, InputStream anInputStream, OutputStream anOutputStream) {

        // Ce processus permet de gérer les actions à effectuer dans les conversations
        String intentionClient;
        BufferedReader is = new BufferedReader(new InputStreamReader(
                anInputStream));

        try {
            if ((intentionClient = is.readLine()) != null) {

                IProtocole protocole;
                String reponse;

                switch (intentionClient) {
                    case "createConversation" -> {
                        protocole = new ProtocoleCreateConversation();
                        reponse = "0";
                    }
                    case "deleteConversation" -> {
                        protocole = new ProtocoleDeleteConversation();
                        reponse = "0";
                    }
                    case "getConversations" -> {
                        protocole = new ProtocoleGetConversations();
                        reponse = "0";
                    }
                    default -> {
                        protocole = new ProtocoleEchec();
                        reponse = "-1";
                    }
                }

                PrintStream os = new PrintStream(anOutputStream);
                /* On envoie la réponse */
                os.println(reponse);

                /* On exécute le protocole adéquat, déterminé par le switch case*/
                protocole.execute(aContext, anInputStream, anOutputStream);

                /* On ferme les os et is APRES l'écécution du protocole ci-dessus, sinon erreur de socket dans ce dernierr*/
                os.close(); is.close();
            }

        } catch (IOException ioException) {
            System.err.println("Problème d'exception IO sur un OutputStream");
            ioException.printStackTrace();
        }
    }

}
