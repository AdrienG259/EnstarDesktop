package server;

import common.Conversation;
import common.Message;
import common.User;
import serverFiles.InstantiateSerializable;
import serverFiles.SharedVariableCannotAccess;
import serverFiles.SharedVariables;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ActionConversation {

    public ActionConversation() throws IOException, ClassNotFoundException {

        // On crée un dossier dans lequel on va venir mettre les fichiers correspondant à la conversation
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

    private void saveNom(Conversation conversation) throws IOException, SharedVariableCannotAccess {
        SharedVariables nom = new SharedVariables("serverFiles/conversations/" + conversation.getID());
        nom.setVariable("nomGroupe", conversation.getNomGroupe());
    }

    private void saveMembres(Conversation conversation) throws IOException {
        File fileusers = new File("serverFiles/conversations/" + conversation.getID() + "/membres");
        if (!fileusers.exists()) {
            fileusers.createNewFile();
        }
        InstantiateSerializable<ArrayList<Integer>> membres = new InstantiateSerializable<>(fileusers);
        ArrayList<Integer> membresID = new ArrayList<>();
        for (User m : conversation.getMembres()) {
            membresID.add(m.getId());
        }
        membres.instanceToFile(membresID);
    }

    private void saveID(Conversation conversation) throws IOException, SharedVariableCannotAccess {
        SharedVariables ID = new SharedVariables("serverFiles/conversations/" + conversation.getID());
        ID.setVariable("ID", String.valueOf(conversation.getID()));
    }

    private void saveHistorique(Conversation conversation) throws IOException {
        File filehistorique = new File("serverFiles/conversations/" + conversation.getID() + "/historique");
        if (!filehistorique.exists()) {
            filehistorique.createNewFile();
        }
        InstantiateSerializable<ArrayList<Message>> historique = new InstantiateSerializable<>(filehistorique);
        ArrayList<Message> messages = new ArrayList<>();
        for (Message m : conversation.getHistorique().getListeMessages()) {
            messages.add(m);
        }
        historique.instanceToFile(messages);
    }

    public List<String> listeDernieresModifs(Conversation conversation) {
        List<String> modifsServeur = conversation.getListDatesLastChanges();
        return modifsServeur;
    }

    public void getConversation(Integer ID) {

    }
}
