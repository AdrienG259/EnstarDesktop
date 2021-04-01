package client;

import java.util.Observable;

public class OtomatOuverture  extends Observable implements IOtomat{

    private static int port = 10001;
    private ClientTCP monClientTCP;

    public OtomatOuverture(int port) {
        this.port = port;
        monClientTCP = new ClientTCP("localhost", port);
    }

    public boolean connexionMessagerie() {
        return monClientTCP.connecterAuServeur();
    }

    public void deconnexionMessagerie() {
        monClientTCP.deconnecterDuServeur();
    }

    public int connexionUtilisateur(String userName, String password){
        System.out.println("Envoi d'un message");
        String message = userName+";"+password;
        String ret = monClientTCP.transmettreChaineConnexionPonctuelle(message).split("\\n")[0];
        int entierRetour = Integer.parseInt(ret);
//        setChanged();
//        notifyObservers();
        return entierRetour;
    }

}
