package common;

import serverFiles.SharedVariableAlreadyExists;
import serverFiles.SharedVariables;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Historique {

    private Conversation conversation;
    private List<Message> listeMessages;
    private String pathHistorique;

    public Historique(Conversation conversation, List<Message> messages) {
        this.conversation = conversation;
        listeMessages = messages;
        pathHistorique = "conversations/historique" + Integer.toString(conversation.getIdConversation());
        File fileHistorique = new File(pathHistorique);
        if (!fileHistorique.exists()){
            try {
                fileHistorique.createNewFile();
            } catch (IOException ioException) {
                System.err.println("Impossible de créer le fichier \"" + fileHistorique.getName() + "\"");
                ioException.printStackTrace();
            }
        }
        SharedVariables sharedVariablesHistorique = null;
        try {
            sharedVariablesHistorique = new SharedVariables(fileHistorique);
            for (Message message:listeMessages){
                String stringID = Integer.toString(message.getID());
                sharedVariablesHistorique.addNewSharedVariable(stringID, message.toString());
            }
            sharedVariablesHistorique.close();
        } catch (IOException ioException) {
            System.err.println("Impossible d'ouvrir le fichier \"" + fileHistorique.getName() + "\"");
            ioException.printStackTrace();
        } catch (SharedVariableAlreadyExists sharedVariableAlreadyExists) {
            sharedVariableAlreadyExists.printStackTrace();
        }
    }

    public Historique(Conversation conversation){
        this(conversation, new LinkedList<Message>());
    }

    public List<Message> getListeMessages(){
        return listeMessages;
    }

    public Message getLastMessage(){
        return listeMessages.get(listeMessages.size()-1);
    }

    public void addMessage(Message newMessage){
        // gestion du fichier à faire ici
        listeMessages.add(newMessage);
        File fileHistorique = new File(pathHistorique);
        if (!fileHistorique.exists()){
            try {
                fileHistorique.createNewFile();
            } catch (IOException ioException) {
                System.err.println("Impossible de créer le fichier \"" + fileHistorique.getName() + "\"");
                ioException.printStackTrace();
            }
        }
        SharedVariables sharedVariablesHistorique = null;
        try {
            sharedVariablesHistorique = new SharedVariables(fileHistorique);
            String stringID = Integer.toString(newMessage.getID());
            sharedVariablesHistorique.addNewSharedVariable(stringID, newMessage.toString());
            sharedVariablesHistorique.close();
        } catch (IOException ioException) {
            System.err.println("Impossible d'ouvrir le fichier \"" + fileHistorique.getName() + "\"");
            ioException.printStackTrace();
        } catch (SharedVariableAlreadyExists sharedVariableAlreadyExists) {
            sharedVariableAlreadyExists.printStackTrace();
        }
    }

    public void removeMessage(Message message){
        //gestion du fichier ici
        if(!listeMessages.remove(message)){
            System.err.println("Message from "+message.getExpediteur()+" dated "+message.getDate()+" not found");
        }
    }
}
