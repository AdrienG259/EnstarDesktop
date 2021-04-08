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
        HashMap<String,Integer> loginUserIDMap = (HashMap<String,Integer>) monClientTCP.receiveSerializable(message);
        setChanged();
        notifyObservers();
        return loginUserIDMap;
    }

    public HashMap<Integer,String> getUserIDPasswordMap(){
        String message = "getUserIDPasswordMap";
        HashMap<Integer,String> userIDPasswordMap = (HashMap<Integer,String>)monClientTCP.receiveSerializable(message);
        setChanged();
        notifyObservers();
        return userIDPasswordMap;
    }

}
