package server.Gestion;

import server.*;
import server.Protocoles.ProtocoleEchec;
import server.Protocoles.ProtocoleGetHistorique;
import server.Protocoles.ProtocoleReceiveMessage;
import server.Protocoles.ProtocoleSendMessage;

import java.io.*;

public class ProtocoleGestionPortConversation implements IProtocole {

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
                    case "sendMessage" -> {
                        protocole = new ProtocoleSendMessage();
                        reponse = "0";
                    }
                    case "receiveMessage" -> {
                        protocole = new ProtocoleReceiveMessage();
                        reponse = "0";
                    }
                    case "getHistorique" -> {
                        protocole = new ProtocoleGetHistorique();
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
                /* On ferme les os et is car utilisent anInputStream et anOutputStream qui vont être réutilisés dans
                le nouveau protocole qu'on va exécuter*/
                os.close(); is.close();
                /* On exécute le protocole adéquat, déterminé par le switch case*/
                protocole.execute(aContext, anInputStream, anOutputStream);
            }

        } catch (IOException ioException) {
            System.err.println("Problème d'exception IO sur un OutputStream");
            ioException.printStackTrace();
        }
    }
}
