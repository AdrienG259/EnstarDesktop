package server;

public class AddUser {

    private final String identifiant;
    private final String motdepasse;
    public final AutorizedUser autorizedUser;

    public AddUser(AutorizedUser autorizedUsers, String login, String mdp) {
        this.identifiant = login;
        this.motdepasse = mdp;
        this.autorizedUser = autorizedUsers;
    }

    public int ajouterUser(){
        //vérification si dans la hashmap
        for (String i : autorizedUser.userMap.keySet()){
            // i = key = username
            if (i.equals(this.identifiant)){
                // identifiant deja utilisé
                return -1;
            }
        }
        autorizedUser.userMap.put(this.identifiant, this.motdepasse);
        //c'est ajouté
        return 1;
    }
}
