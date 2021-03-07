package server;

import java.util.HashMap;

public class AutorizedUser {
    //création d'une MAP des user autorisés. Sert pour l'authentification de la connexion
    HashMap<String, String> userMap = new HashMap<String, String>();
    //key = nom / value = mdp
    public HashMap<String, String> AutorizedUser() {
        userMap.put("Pierre-Olivier", "lemotdepasse");
        userMap.put("Marie-Amélie", "lemotdepasse");
        userMap.put("Julie", "lemotdepasse");
        userMap.put("Adrien", "lemotdepasse");
        userMap.put("Emilie", "lemotdepasse");

        return userMap;
    }
}
