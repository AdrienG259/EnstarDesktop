package client;

import java.util.Observable;

public class OtomatDelete extends Observable implements IOtomat{

    private static int port = 10003;
    private ClientTCP monClientTCP;

    public OtomatDelete(int port) {
        this.port = port;
        monClientTCP = new ClientTCP("localhost", port);
    }

    public boolean connexionMessagerie() {
        return monClientTCP.connecterAuServeur();
    }

    public void deconnexionMessagerie() {
        monClientTCP.deconnecterDuServeur();
    }

    public int deleteUtilisateur(String userName){
        System.out.println("Envoi d'un message");
        String message = userName;
        String ret = monClientTCP.transmettreChaineConnexionPonctuelle(message).split("\\n")[0];
        int entierRetour = Integer.parseInt(ret);
        setChanged();
        notifyObservers();
        return entierRetour;
    }
}
