package server.Protocoles;

import common.User;
import server.ActionUser;
import server.Gestion.Messagerie;
import server.IContext;
import server.IProtocole;
import serverFiles.InstantiateSerializable;

import java.io.*;

public class ProtocoleChangePassword implements IProtocole {
    @Override
    public void execute(IContext aContext, InputStream anInputStream, OutputStream anOutputStream) {
        Messagerie messagerie = (Messagerie)aContext;
        String parametres;
        BufferedReader is = new BufferedReader(new InputStreamReader(
                anInputStream));
        PrintStream os = new PrintStream(anOutputStream);
        try {

            if ((parametres = is.readLine()) != null) {
                String[] paramSplit = parametres.split(";");
                String pseudo = paramSplit[0];
                String newPassword = paramSplit[1];

                try {
                    ActionUser actionUser = new ActionUser();
                    actionUser.changePassword(pseudo, newPassword);
//                    int userID = actionUser.getUserIDFromLogin(pseudo);
//                    actionUser.getUserIDPasswordMap().replace(userID, newPassword);
                    os.println("0");
                } catch (ClassNotFoundException classNotFoundException) {
                    System.err.println("Can not instanciate user with login " + pseudo + " from file");
                    os.println("-1");
                }
            }
        } catch (Exception e) {
            System.out.println(" Pb d'exception ");
            e.printStackTrace();
        }


    }
}
