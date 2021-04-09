package server.Gestion;

import java.io.*;

import server.ActionUser;
import server.IContext;
import server.IProtocole;

public class ProtocoleConnexion implements IProtocole {

    @Override
    public void execute(IContext aContext, InputStream anInputStream, OutputStream anOutputStream) {

        // Le protocole permet de se connecter en vérifiant que l'utilisateur est bien présent dans la base de données
        String inputReq;
        BufferedReader is = new BufferedReader(new InputStreamReader(
                anInputStream));
        PrintStream os = new PrintStream(anOutputStream);

        try {
            String messageRetour;
            if ((inputReq = is.readLine()) != null) {
                System.out.println("Ordre Reçu : " + inputReq);

                // On récupère les informations de connexion à partir de l'InputStream
                String[] loginpassword = inputReq.split(";");
                String login = loginpassword[0];
                String password = loginpassword[1];

                // On compare avec les données de la base de données
                ActionUser actionUser = new ActionUser();
                int can_connect = actionUser.comparaison(login ,password);
                messageRetour = can_connect +"\n";
                os.println(messageRetour);
            }
        } catch (Exception e) {
            System.out.println("Problème d'exception");
            e.printStackTrace();
        }
    }
}
