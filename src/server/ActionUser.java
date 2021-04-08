package server;

import common.User;
import serverFiles.InstantiateSerializable;
import serverFiles.SharedVariableAlreadyExists;
import serverFiles.SharedVariableCannotAccess;
import serverFiles.SharedVariables;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ActionUser {

    public HashMap<String, Integer> loginUserIDMap = new HashMap<String, Integer>();
    public HashMap<Integer, String> userIDPasswordMap = new HashMap<Integer, String>();

    public ActionUser() throws IOException, ClassNotFoundException {
        instantiateMaps();
    }

    public void instantiateMaps() throws IOException, ClassNotFoundException {
        File loginUserIDMapFile = new File("serverFiles/loginUserIDMap");
        if (!loginUserIDMapFile.exists()){
            loginUserIDMapFile.createNewFile();
        }
        InstantiateSerializable<HashMap<String, Integer>> instantiate_loginUserIDMap = new InstantiateSerializable<>(loginUserIDMapFile);
        File userIDPasswordMapFile = new File("serverFiles/userIDPasswordMap");
        if (!userIDPasswordMapFile.exists()){
            userIDPasswordMapFile.createNewFile();
        }
        InstantiateSerializable<HashMap<Integer, String>> instantiate_userIDPasswordMap = new InstantiateSerializable<>(userIDPasswordMapFile);
        try {loginUserIDMap = instantiate_loginUserIDMap.fileToInstance();}
        catch(EOFException eofException){
            instantiate_loginUserIDMap.instanceToFile(new HashMap<String, Integer>());
            loginUserIDMap = instantiate_loginUserIDMap.fileToInstance();
        }
        try {userIDPasswordMap = instantiate_userIDPasswordMap.fileToInstance();}
        catch(EOFException eofException){
            instantiate_userIDPasswordMap.instanceToFile(new HashMap<Integer, String>());
            userIDPasswordMap = instantiate_userIDPasswordMap.fileToInstance();
        }
    }

    private void saveMaps() throws IOException {
        File loginUserIDMapFile = new File("serverFiles/loginUserIDMap");
        InstantiateSerializable<HashMap<String, Integer>> instantiate_loginUserIDMap = new InstantiateSerializable<>(loginUserIDMapFile);
        File userIDPasswordMapFile = new File("serverFiles/userIDPasswordMap");
        InstantiateSerializable<HashMap<Integer, String>> instantiate_userIDPasswordMap = new InstantiateSerializable<>(userIDPasswordMapFile);
        instantiate_loginUserIDMap.instanceToFile(loginUserIDMap);
        instantiate_userIDPasswordMap.instanceToFile(userIDPasswordMap);
    }

    public int addUser(String identifiant, String motdepasse) throws IOException, SharedVariableCannotAccess, SharedVariableAlreadyExists {
        //vérification si dans la hashmap
        for (String i : loginUserIDMap.keySet()){
            // i = key = username
            if (i.equals(identifiant)){
                // identifiant deja utilisé
                return -1;
            }
        }
        /* Trouver un nouveau userID admissible */

        SharedVariables sharedVariables = new SharedVariables("serverFiles/sharedVariables");
        String userIDString;
        try{userIDString = sharedVariables.accessVariable("NEWUSERID");}
        catch (SharedVariableCannotAccess sharedVariableCannotAccess){
            sharedVariables.addNewSharedVariable("NEWUSERID", "0");
            userIDString = sharedVariables.accessVariable("NEWUSERID");
        }
        int userID = Integer.parseInt(userIDString);
        int newUserIDAdmissible = userID+1;
        sharedVariables.setVariable("NEWUSERID", Integer.toString(newUserIDAdmissible));

        /* Ajouter dans les maps */
        loginUserIDMap.put(identifiant, userID);
        userIDPasswordMap.put(userID, motdepasse);
        saveMaps();

        /* Instancier le nouvel User et l'enregistrer */

        File userFile = new File("serverFiles/users/"+userID);
        userFile.createNewFile();
        InstantiateSerializable<User> instantiate_user = new InstantiateSerializable<>(userFile);
        User newUser = new User(userID, identifiant, new ArrayList<>(),new ArrayList<>());
        instantiate_user.instanceToFile(newUser);

        return 0;
    }

    public int deleteUser(int userID) throws IOException, ClassNotFoundException {
        //vérification si dans la hashmap
        for (int userIDissudelamap : userIDPasswordMap.keySet()){
            // i = key = username
            if (userIDissudelamap == userID){
                // identifiant deja utilisé
                break;
            }
            else {
                return -1;
            }
        }
        /* Instancier le nouvel User et l'enregistrer */

        File userFile = new File("serverFiles/users/"+userID);
        InstantiateSerializable<User> instantiate_user = new InstantiateSerializable<>(userFile);
        User newUser = instantiate_user.fileToInstance();
        boolean hasBeenDeleted = userFile.delete();
        if (!hasBeenDeleted){
            return -2;
        }

        /* Ajouter dans les maps */
        loginUserIDMap.remove(newUser.getPseudo());
        userIDPasswordMap.remove(userID);
        saveMaps();

        return 0;

    }

    public int comparaison(String login, String motdepasse) {
        Integer userID;
        if ((userID = this.loginUserIDMap.get(login)) == null){
            return -1;
        }
        if (this.userIDPasswordMap.get(userID).equals(motdepasse)){
            return 1;
        }
        return 0;
    }

    public int getUserIDFromLogin(String login){
        return loginUserIDMap.get(login);
    }

    public String getPasswordFromLogin(String login){
        int userID = this.getUserIDFromLogin(login);
        return userIDPasswordMap.get(userID);
    }

    public String getPasswordFromUserID(int userID){
        return userIDPasswordMap.get(userID);
    }

    public HashMap<String,Integer> getLoginUserIDMap(){
        return this.loginUserIDMap;
    }

    public HashMap<Integer,String> getUserIDPasswordMap(){
        return this.userIDPasswordMap;
    }

    public void changePseudo(String pseudo, String newPseudo) throws IOException, ClassNotFoundException {
        int userID = loginUserIDMap.get(pseudo);
        File userFile = new File("serverFiles/users/" + userID);
        InstantiateSerializable<User> userInstantiate = new InstantiateSerializable<User>(userFile);
        User user = userInstantiate.fileToInstance();
        user.setPseudo(newPseudo);
        userInstantiate.instanceToFile(user);

        loginUserIDMap.remove(pseudo);
        loginUserIDMap.put(newPseudo, userID);
        saveMaps();
    }


    public void changePassword(String pseudo, String newPassword) throws IOException {
        userIDPasswordMap.replace(loginUserIDMap.get(pseudo), newPassword);
        saveMaps();
    }
}
