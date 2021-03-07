package server;

import java.io.File;

public class VerifLogin {

    private final String identifiant;
    private final String motdepasse;
    private final AutorizedUser autorizedUser;

    public VerifLogin(String login, String mdp) {
        this.identifiant = login;
        this.motdepasse = mdp;
        this.autorizedUser = new AutorizedUser();
    }

    public int comparaison() {
        if (autorizedUser.userMap.get(this.identifiant) == null){
            return -1;
        }
        if (autorizedUser.userMap.get(this.identifiant).equals(this.motdepasse)){
            return 1;
        }
        return 0;
    }
}
