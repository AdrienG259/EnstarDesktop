package client;

import common.User;

public class ControleurUser extends Controleur {

    private static final int port_GestionUser = 10002;

    public ControleurUser() {
        super(port_GestionUser);
    }

    public int createUser(String userName, String password){
        System.out.println("createUser " + userName + " " + password);
        String message = "createUser " + userName + " " +password;
        String ret = monClientTCP.transmettreChaineConnexionPonctuelle(message).split("\\n")[0];
        int entierRetour = Integer.parseInt(ret);
        setChanged();
        notifyObservers();
        return entierRetour;
    }

    public int deleteUser(String userName){
        System.out.println("deleteUser "+ userName);
        String message = "deleteUser "+ userName;
        String ret = monClientTCP.transmettreChaineConnexionPonctuelle(message).split("\\n")[0];
        int entierRetour = Integer.parseInt(ret);
        setChanged();
        notifyObservers();
        return entierRetour;
    }

    public User getUser(int userID){

        System.out.println("getUser "+ userID);
        String message = "getUser "+ userID;
        User retUser = (User) monClientTCP.receiveSerializable(message);
        setChanged();
        notifyObservers();
        return retUser;
    }


}
