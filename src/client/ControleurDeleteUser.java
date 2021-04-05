package client;

public class ControleurDeleteUser extends Controleur {

    private static final int port_deleteUser = 10003;

    public ControleurDeleteUser() {
        super(port_deleteUser);
    }

    public int deleteUtilisateur(String userName){
        System.out.println("deleteUtilisateur");
        String message = userName;
        String ret = monClientTCP.transmettreChaineConnexionPonctuelle(message).split("\\n")[0];
        int entierRetour = Integer.parseInt(ret);
        setChanged();
        notifyObservers();
        return entierRetour;
    }
}
