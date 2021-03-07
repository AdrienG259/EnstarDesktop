package server;

public class AddUser {

    private final String identifiant;
    private final String motdepasse;
    private final AutorizedUser autorizedUser;

    public AddUser(String login, String mdp) {
        this.identifiant = login;
        this.motdepasse = mdp;
        this.autorizedUser = new AutorizedUser();
    }

    public void ajouterUser(){
        //vérification si dans la hashmap
        for (String i : autorizedUser.userMap.keySet()){

        }
    }
}
