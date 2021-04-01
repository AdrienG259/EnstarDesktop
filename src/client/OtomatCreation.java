package client;

import java.util.Observable;

public class OtomatCreation extends Observable implements IOtomat{

    private static int port = 10002;
    private ClientTCP monClientTCP;

    public OtomatCreation(int port) {
        this.port = port;
        monClientTCP = new ClientTCP("localhost", port);
    }

    public boolean connexionMessagerie() {
        return monClientTCP.connecterAuServeur();
    }

    public void deconnexionMessagerie() {
        monClientTCP.deconnecterDuServeur();
    }

    public int creactionUtilisateur(String userName, String password){
        System.out.println("Envoi d'un message");
        String message = userName+";"+password;
        String ret = monClientTCP.transmettreChaineConnexionPonctuelle(message).split("\\n")[0];
        int entierRetour = Integer.parseInt(ret);
        setChanged();
        notifyObservers();
        return entierRetour;
    }
}
