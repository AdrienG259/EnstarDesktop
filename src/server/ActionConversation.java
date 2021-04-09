package server;

import common.Conversation;
import common.Historique;
import common.Message;
import common.User;
import serverFiles.InstantiateSerializable;
import serverFiles.SharedVariableCannotAccess;
import serverFiles.SharedVariables;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ActionConversation {

    public static String path = "serverFiles/conversations";

    public ActionConversation() throws IOException, ClassNotFoundException {

        // On crée un dossier dans lequel on va venir mettre les fichiers correspondant à la conversation
        File directoryConversations = new File(path);
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

    public void saveNom(Conversation conversation) throws IOException, SharedVariableCannotAccess {
        SharedVariables nom = new SharedVariables(path + conversation.getID());
        nom.setVariable("nomGroupe", conversation.getNomGroupe());
        List<String> listLastChanges = conversation.getListDatesLastChanges();
        listLastChanges.set(2, (new SimpleDateFormat("yyyyMMdd_HHmmss")).format(new Date()));
        conversation.setListDatesLastChanges(listLastChanges);
    }

    public void saveMembres(Conversation conversation) throws IOException {
        File fileusers = new File(path + conversation.getID() + "/membres");
        if (!fileusers.exists()) {
            fileusers.createNewFile();
        }
        InstantiateSerializable<ArrayList<Integer>> membres = new InstantiateSerializable<>(fileusers);
        ArrayList<Integer> membresID = new ArrayList<>();
        for (User m : conversation.getMembres()) {
            membresID.add(m.getId());
        }
        membres.instanceToFile(membresID);
        List<String> listLastChanges = conversation.getListDatesLastChanges();
        listLastChanges.set(1, (new SimpleDateFormat("yyyyMMdd_HHmmss")).format(new Date()));
        conversation.setListDatesLastChanges(listLastChanges);
    }

    public void saveID(Conversation conversation) throws IOException, SharedVariableCannotAccess {
        SharedVariables ID = new SharedVariables(path + conversation.getID());
        ID.setVariable("ID", String.valueOf(conversation.getID()));
    }

    public void saveHistorique(Conversation conversation) throws IOException {
        File filehistorique = new File(path + conversation.getID() + "/historique");
        if (!filehistorique.exists()) {
            filehistorique.createNewFile();
        }
        InstantiateSerializable<ArrayList<Message>> historique = new InstantiateSerializable<>(filehistorique);
        ArrayList<Message> messages = new ArrayList<>();
        for (Message m : conversation.getHistorique().getListeMessages()) {
            messages.add(m);
        }
        historique.instanceToFile(messages);
        List<String> listLastChanges = conversation.getListDatesLastChanges();
        listLastChanges.set(0, (new SimpleDateFormat("yyyyMMdd_HHmmss")).format(new Date()));
        conversation.setListDatesLastChanges(listLastChanges);
    }

    public void saveLastChanges(Conversation conversation) throws IOException {
        File filechanges = new File(path + conversation.getID() + "/dernierschangements");
        if (!filechanges.exists()) {
            filechanges.createNewFile();
        }
        InstantiateSerializable<ArrayList<String>> changes = new InstantiateSerializable<>(filechanges);
        ArrayList<String> dates = new ArrayList<>();
        changes.instanceToFile(dates);
    }

    public List<String> listeDernieresModifs(Conversation conversation) {
        List<String> modifsServeur = conversation.getListDatesLastChanges();
        return modifsServeur;
    }

    public Conversation getConversationID(Integer ID) throws IOException, SharedVariableCannotAccess, ClassNotFoundException {
        String nom = getNom(ID);
        ArrayList<User> membres = getMembres(ID);
        Historique historique = getHistorique(ID);
        Conversation conversation = new Conversation(nom, membres, ID);
        conversation.setHistorique(historique);
        return conversation;
    }

    public String getNom(Integer ID) throws IOException, SharedVariableCannotAccess {
        SharedVariables conv = new SharedVariables(path + ID);
        String nom = conv.accessVariable("nomGroupe");
        return nom;
    }

    public ArrayList<User> getMembres(Integer ID) throws IOException, ClassNotFoundException {
        File fileConversation = new File(path + ID);
        InstantiateSerializable<ArrayList<User>> membresSerialise = new InstantiateSerializable<>(fileConversation);
        ArrayList<User> membres = membresSerialise.fileToInstance();
        return membres;
    }

    public Historique getHistorique(Integer ID) throws IOException, ClassNotFoundException {
        File fileConversation = new File(path + ID);
        InstantiateSerializable<ArrayList<Message>> historiqueSerialise = new InstantiateSerializable<>(fileConversation);
        ArrayList<Message> listeMessages = historiqueSerialise.fileToInstance();
        Historique historique = new Historique();
        historique.setListeMessages(listeMessages);
        return historique;
    }

    public ArrayList<String> getLastChanges(Integer ID) throws IOException, ClassNotFoundException {
        File fileConversation = new File(path + ID);
        InstantiateSerializable<ArrayList<String>> changesSerialise = new InstantiateSerializable<>(fileConversation);
        ArrayList<String> changes = changesSerialise.fileToInstance();
        return changes;
    }

}
