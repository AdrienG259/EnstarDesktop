package client;

import common.User;

import javax.naming.InterruptedNamingException;

public class ControleurUser extends Controleur {

    private static final int port_GestionUser = 10002;

    public ControleurUser() {
        super(port_GestionUser);
    }

    public int createUser(String userName, String password){

        /* Premier message, l'intention */
        String intention = "createUser";
        System.out.println("intention = createUser "+ "paramètres = " + userName + ";" + password);

        /* On l'envoie et on récupère le retour */
        monClientTCP.connecterAuServeur();
        String retIntention = monClientTCP.transmettreChaine(intention).split("\\n")[0];
        int entierRetIntention = Integer.parseInt(retIntention);

        /* On interprète le retour */
        if (entierRetIntention == 0){
            /* Cas où le retour nous confirme qu'on peut envoyer les paramètres */
            String parametres = userName + ";" +password;
            /*retour : -1 si utilisateur deja existant, 0 sinon*/
            String retParametres = monClientTCP.transmettreChaine(parametres).split("\\n")[0];
            monClientTCP.deconnecterDuServeur();
            setChanged();
            notifyObservers();
            return Integer.parseInt(retParametres);
        } else{
            /* Cas d'échec, on ne peut pas envoyer les paramètres, le serveur n'est pas préparé à les recevoir */
            System.err.println("Problème communication, intention "+intention+ " non prise en compte par le serveur");
            //On peut renvoyer une exception sinon
            monClientTCP.deconnecterDuServeur();
            return -2;
        }
    }

    public int deleteUser(String userName){

        /* Intention */
        String intention = "deleteUser";
        System.out.println("intention = deleteUser "+ "paramètres = " + userName);

        /*Envoie intention et retour du serveur*/
        String retIntention = monClientTCP.transmettreChaineConnexionPonctuelle(intention).split("\\n")[0];
        int entierRetIntention = Integer.parseInt(retIntention);

        /* Interprétation du retour */
        if (entierRetIntention == 0){
            /* Serveur prêt pour la réception des paramètres */
            String parametres = userName;
            /* retour : ? */
            String retParametres = monClientTCP.transmettreChaineConnexionPonctuelle(parametres).split("\\n")[0];
            setChanged();
            notifyObservers();
            return Integer.parseInt(retParametres);
        } else{
            /* Cas d'échec, on ne peut pas envoyer les paramètres, le serveur n'est pas préparé à les recevoir */
            System.err.println("Problème communication, intention "+intention+ " non prise en compte par le serveur");
            //On peut renvoyer une exception sinon
            return -2;
        }
    }

    public User getUser(int userID){

        /* Intention */
        String intention = "getUser";
        System.out.println("intention = getUser "+ "paramètres = " + userID);

        /*Envoie intention et retour du serveur*/
        String retIntention = monClientTCP.transmettreChaineConnexionPonctuelle(intention).split("\\n")[0];
        int entierRetIntention = Integer.parseInt(retIntention);

        /* Interprétation du retour */
        if (entierRetIntention == 0){
            /* Serveur prêt pour la réception des paramètres */
            String parametres = String.valueOf(userID);
            /* retour : Utilisateur demandé */
            User retUser = (User) monClientTCP.receiveSerializable(parametres);;
            setChanged();
            notifyObservers();
            return retUser;
        } else{
            /* Cas d'échec, on ne peut pas envoyer les paramètres, le serveur n'est pas préparé à les recevoir */
            System.err.println("Problème communication, intention "+intention+ " non prise en compte par le serveur");
            //On retourne un objet null
            return null;
        }

    }


}
