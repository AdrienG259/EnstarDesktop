package client;

import java.util.Observable;


public class Otomat extends Observable implements IOtomat{

    private int port = 9998;
    private ClientTCP monClientTCP;

    public Otomat(int port, ClientTCP monClientTCP) {
        this.port = port;
        monClientTCP = new ClientTCP("localhost", port);
    }

    public boolean connexionMessagerie() {
        return monClientTCP.connecterAuServeur();
    }

    public void deconnexionMessagerie() {
        monClientTCP.deconnecterDuServeur();
    }

    public void envoyerMessage(String message) {
        System.out.println("Envoi d'un message");
        monClientTCP.transmettreChaine(message);
        setChanged();
        notifyObservers();
    }

    public void creerConversation() {

    }
}
