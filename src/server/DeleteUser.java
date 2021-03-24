package server;

public class DeleteUser {

    private final String identifiant;
    public final AutorizedUser autorizedUser;

    public DeleteUser(AutorizedUser autorizedUsers, String login) {
        this.identifiant = login;
        this.autorizedUser = autorizedUsers;
    }

    public void SupprimerUser(){
        autorizedUser.userMap.remove(this.identifiant);
    }
}
