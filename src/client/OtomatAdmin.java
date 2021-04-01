package client;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Observable;

public class OtomatAdmin extends Observable implements IOtomat{

    private static int port = 10004;
    private ClientTCP monClientTCP;

    public OtomatAdmin(int port) {
        this.port = port;
        monClientTCP = new ClientTCP("localhost", port);
    }

    public boolean connexionMessagerie() {
        return monClientTCP.connecterAuServeur();
    }

    public void deconnexionMessagerie() {
        monClientTCP.deconnecterDuServeur();
    }

    public HashMap<String,String> getuserMap(){
        String message = "userMap";
        Serializable ret = monClientTCP.receiveSerializable(message);
        HashMap<String,String> userMap = (HashMap<String,String>) ret;
        setChanged();
        notifyObservers();
        return userMap;
    }

}
