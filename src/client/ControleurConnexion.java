package client;

public class ControleurConnexion extends Controleur {

    private static int port_connexion = 10001;

    public ControleurConnexion() {
        super(port_connexion);
    }

    public int connexionUtilisateur(String userName, String password){
        System.out.println("Envoi d'un message");
        String message = userName+";"+password;
        String ret = monClientTCP.transmettreChaineConnexionPonctuelle(message).split("\\n")[0];
        int entierRetour = Integer.parseInt(ret);
        setChanged();
        notifyObservers();
        return entierRetour;
    }

}
