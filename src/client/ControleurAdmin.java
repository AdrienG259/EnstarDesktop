package client;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Observable;

public class ControleurAdmin extends Controleur {

    private static final int port_admin = 10003;

    public ControleurAdmin() {
        super(port_admin);
    }

    public HashMap<String,Integer> getLoginUserIDMap(){
        String message = "getLoginUserIDMap";
        monClientTCP.connecterAuServeur();
        HashMap<String,Integer> loginUserIDMap = (HashMap<String,Integer>) monClientTCP.receiveSerializable(message);
        monClientTCP.deconnecterDuServeur();
        setChanged();
        notifyObservers();
        return loginUserIDMap;
    }

    public HashMap<Integer,String> getUserIDPasswordMap(){
        String message = "getUserIDPasswordMap";
        monClientTCP.connecterAuServeur();
        HashMap<Integer,String> userIDPasswordMap = (HashMap<Integer,String>)monClientTCP.receiveSerializable(message);
        monClientTCP.deconnecterDuServeur();
        setChanged();
        notifyObservers();
        return userIDPasswordMap;
    }

}
