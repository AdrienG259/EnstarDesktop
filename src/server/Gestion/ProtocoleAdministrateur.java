package server.Gestion;

import server.ActionUser;
import server.IContext;
import server.IProtocole;

import java.io.*;

public class ProtocoleAdministrateur implements IProtocole {

    @Override
    public void execute(IContext aContext, InputStream anInputStream, OutputStream anOutputStream) {

        // Ce protocole entre en jeu lorsque l'utilisateur veut se connecter en tant qu'administrateur à l'application
        String inputReq;
        BufferedReader is = new BufferedReader(new InputStreamReader(
                anInputStream));
        PrintStream os = new PrintStream(anOutputStream);

        try {
            String messageRetour;

            // On vérifie qu'un message a bien été transmis dans l'InputStream
            if ((inputReq = is.readLine()) != null) {

                System.out.println(" Ordre Recu " + inputReq);

                // L'administrateur peut chercher à accéder aux deux Hashmap : celle liant Login et userID et celle
                // laint userID et password.
                switch (inputReq){
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

                // En fonction du succès ou non de l'opération, la méthode retourne 0 (succès), -1 (le message envoyé ne
                // correspond à aucun des cas) ou -2 (erreur lors de la transmission de la Hashmap)
                os.println(messageRetour);
                os.close();
            }

        } catch (IOException ioException) {
            System.err.println("Problème d'exception IO sur un OutputStream");
            ioException.printStackTrace();
        }
    }

    // Cette méthode renvoie la Hashmap contenant les Login et les userID
    private void sendLoginUserIDMap(OutputStream outputStream) throws IOException, ClassNotFoundException {
        ObjectOutputStream oos = new ObjectOutputStream(outputStream);
        ActionUser actionUser = new ActionUser();
        oos.writeObject(actionUser.getLoginUserIDMap());
        oos.flush();
//        oos.close();
    }

    // Cette méthode renvoie la Hashmap contenant les userID et les password
    private void sendUserIDPasswordMap(OutputStream outputStream) throws IOException, ClassNotFoundException {
        ObjectOutputStream oos = new ObjectOutputStream(outputStream);
        ActionUser actionUser = new ActionUser();
        oos.writeObject(actionUser.getUserIDPasswordMap());
        oos.flush();
//        oos.close();
    }
}
