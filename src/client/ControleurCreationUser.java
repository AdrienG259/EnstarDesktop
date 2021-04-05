package client;

public class ControleurCreationUser extends Controleur {

    private static final int port_createUser = 10002;

    public ControleurCreationUser() {
        super(port_createUser);
    }

    public int creationUtilisateur(String userName, String password){
        System.out.println("Envoi d'un message");
        String message = userName+";"+password;
        String ret = monClientTCP.transmettreChaineConnexionPonctuelle(message).split("\\n")[0];
        int entierRetour = Integer.parseInt(ret);
        setChanged();
        notifyObservers();
        return entierRetour;
    }
}
