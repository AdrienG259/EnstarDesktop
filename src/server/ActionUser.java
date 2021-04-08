package server;

import common.User;
import serverFiles.InstantiateSerializable;
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
        InstantiateSerializable<HashMap<String, Integer>> instantiate_loginUserIDMap = new InstantiateSerializable<>(loginUserIDMapFile);
        File userIDPasswordMapFile = new File("serverFiles/userIDPasswordMap");
        InstantiateSerializable<HashMap<Integer, String>> instantiate_userIDPasswordMap = new InstantiateSerializable<>(userIDPasswordMapFile);
        loginUserIDMap = instantiate_loginUserIDMap.fileToInstance();
        userIDPasswordMap = instantiate_userIDPasswordMap.fileToInstance();
    }

    private void saveMaps() throws IOException {
        File loginUserIDMapFile = new File("serverFiles/loginUserIDMap");
        InstantiateSerializable<HashMap<String, Integer>> instantiate_loginUserIDMap = new InstantiateSerializable<>(loginUserIDMapFile);
        File userIDPasswordMapFile = new File("serverFiles/userIDPasswordMap");
        InstantiateSerializable<HashMap<Integer, String>> instantiate_userIDPasswordMap = new InstantiateSerializable<>(userIDPasswordMapFile);
        instantiate_loginUserIDMap.instanceToFile(loginUserIDMap);
        instantiate_userIDPasswordMap.instanceToFile(userIDPasswordMap);
    }

    public int ajouterUser(String identifiant, String motdepasse) throws IOException, SharedVariableCannotAccess {
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
        String userIDString = sharedVariables.accessVariable("NEWUSERID");
        int userID = Integer.parseInt(userIDString);
        int newUserIDAdmissible = userID+1;
        sharedVariables.setVariable("NEWUSERID", Integer.toString(newUserIDAdmissible));

        /* Ajouter dans les maps */
        loginUserIDMap.put(identifiant, userID);
        userIDPasswordMap.put(userID, motdepasse);
        saveMaps();

        /* Instancier le nouvel User et l'enregistrer */

        File userFile = new File("serverFiles/users/"+userID);
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

}
