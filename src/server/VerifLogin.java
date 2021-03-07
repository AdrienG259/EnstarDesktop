package server;

import java.io.File;

public class VerifLogin {

    private final String identifiant;
    private final String motdepasse;

    AutorizedUser autorizedUser;

    public VerifLogin(String login, String mdp) {
        this.identifiant = login;
        this.motdepasse = mdp;
    }

    public boolean comparaison(String identifiant, String motdepasse) {

        return (autorizedUser.userMap.get(identifiant)).equals(motdepasse);
    }
}
