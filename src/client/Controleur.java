package client;

import java.util.Observable;

public abstract class Controleur  extends Observable {

    protected final ClientTCP monClientTCP;
//    protected static desktopBusinessRules businessRules = new desktopBusinessRules();

    protected Controleur(int port) {
        this("localhost", port);
    }

    protected Controleur(String adresse, int port) {
        this.monClientTCP = new ClientTCP(adresse, port);
    }
}
