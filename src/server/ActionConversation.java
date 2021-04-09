package server;

import common.Conversation;
import common.User;
import serverFiles.InstantiateSerializable;
import serverFiles.SharedVariableAlreadyExists;
import serverFiles.SharedVariableCannotAccess;
import serverFiles.SharedVariables;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ActionConversation {

    public ActionConversation() throws IOException, ClassNotFoundException {
        File directoryConversations = new File("serverFiles/conversations");
        if (directoryConversations.exists()){
            if(!directoryConversations.isDirectory()){
                if(!directoryConversations.delete()){
                    throw new IOException("Impossible de supprimer le fichier "+directoryConversations.getAbsolutePath()
                            +"pour créer le dossier du même nom");
                } else {
                    if(!directoryConversations.mkdir()){
                        throw new IOException("Can't create directory "+ directoryConversations.getAbsolutePath());
                    }
                }
            }
        } else {
            if(!directoryConversations.mkdir()){
                throw new IOException("Can't create directory "+ directoryConversations.getAbsolutePath());
            }
        }
    }
//
//    private void saveConversationAttributes(Conversation ){
//
//    }
//
//
//    public int addConversation(Conversation newConversation){
//
//        /* Trouver un nouveau userID admissible */
//
//
//        /* Ajouter dans les maps */
//        loginUserIDMap.put(identifiant, userID);
//        userIDPasswordMap.put(userID, motdepasse);
//        saveMaps();
//
//        /* Instancier le nouvel User et l'enregistrer */
//
//        File userFile = new File("serverFiles/users/"+userID);
//        userFile.createNewFile();
//        InstantiateSerializable<User> instantiate_user = new InstantiateSerializable<>(userFile);
//        User newUser = new User(userID, identifiant, new ArrayList<>(),new ArrayList<>());
//        instantiate_user.instanceToFile(newUser);
//
//        return 0;
//    }
//
//    public int deleteConversation(int userID) throws IOException, ClassNotFoundException {
//        //vérification si dans la hashmap
//        for (int userIDissudelamap : userIDPasswordMap.keySet()){
//            // i = key = username
//            if (userIDissudelamap == userID){
//                // identifiant deja utilisé
//                break;
//            }
//            else {
//                return -1;
//            }
//        }
//        /* Instancier le nouvel User et l'enregistrer */
//
//        File userFile = new File("serverFiles/users/"+userID);
//        InstantiateSerializable<User> instantiate_user = new InstantiateSerializable<>(userFile);
//        User newUser = instantiate_user.fileToInstance();
//        boolean hasBeenDeleted = userFile.delete();
//        if (!hasBeenDeleted){
//            return -2;
//        }
//
//        /* Ajouter dans les maps */
//        loginUserIDMap.remove(newUser.getPseudo());
//        userIDPasswordMap.remove(userID);
//        saveMaps();
//
//        return 0;
//
//    }
//
//    public int addUser(String login, String motdepasse) {
//        if (!membres.contains(member)) {
//            membres.add(member);
//            setChanged();
//            notifyObservers();
//        }
//        else {
//            System.err.println("Le membre " +member+ " appartient déjà à la conversation");
//        }
//    }
//
//    public int removeUser(String login){
//        return loginUserIDMap.get(login);
//    }
//
//    public String changeNomConversation(String login){
//        int userID = this.getUserIDFromLogin(login);
//        return userIDPasswordMap.get(userID);
//    }
//
//    public String setHistorique(int userID){
//        return userIDPasswordMap.get(userID);
//    }
//
//    public void changePseudo(String pseudo, String newPseudo) throws IOException, ClassNotFoundException {
//        int userID = loginUserIDMap.get(pseudo);
//        File userFile = new File("serverFiles/users/" + userID);
//        InstantiateSerializable<User> userInstantiate = new InstantiateSerializable<User>(userFile);
//        User user = userInstantiate.fileToInstance();
//        user.setPseudo(newPseudo);
//        userInstantiate.instanceToFile(user);
//
//        loginUserIDMap.remove(pseudo);
//        loginUserIDMap.put(newPseudo, userID);
//        saveMaps();
//    }
//
//
//    public void changePassword(String pseudo, String newPassword) throws IOException {
//        userIDPasswordMap.replace(loginUserIDMap.get(pseudo), newPassword);
//        saveMaps();
//    }
//
//    public void changeListDates(){}
}
