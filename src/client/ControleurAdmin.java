package client;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Observable;

public class ControleurAdmin extends Controleur {

    private static final int port_admin = 10003;

    public ControleurAdmin() {
        super(port_admin);
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
