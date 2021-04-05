package server;

import common.User;
import serverFiles.InstantiateSerializable;
import serverFiles.SharedVariableCannotAccess;
import serverFiles.SharedVariables;

import java.io.File;
import java.io.IOException;

public class AddUser {

    private final String identifiant;
    private final String motdepasse;
    public final AutorizedUser autorizedUser;

    public AddUser(AutorizedUser autorizedUsers, String login, String mdp) {
        this.identifiant = login;
        this.motdepasse = mdp;
        this.autorizedUser = autorizedUsers;
    }

    public int ajouterUser() throws IOException {
        //vérification si dans la hashmap
        for (String i : autorizedUser.userMap.keySet()){
            // i = key = username
            if (i.equals(this.identifiant)){
                // identifiant deja utilisé
                return -1;
            }
        }
        autorizedUser.userMap.put(this.identifiant, this.motdepasse);
        SharedVariables sharedVariables = new SharedVariables("serverFiles/sharedVariables");
        int newUserID = 0;
        try {
            newUserID = Integer.parseInt(sharedVariables.accessVariable("GLOBAL_NEWUSERID"));
            File userFile = new File("serverFiles/users/" + newUserID);
            InstantiateSerializable<User> userInstantiate = new InstantiateSerializable<User>(userFile);
            User user = new User(newUserID);
            userInstantiate.instanceToFile(user);
            //c'est ajouté
            return 1;
        } catch (SharedVariableCannotAccess sharedVariableCannotAccess) {
            sharedVariableCannotAccess.printStackTrace();
            return -1;
        }

    }
}
