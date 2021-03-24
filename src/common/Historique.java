package common;

import common.Message;
import serverFiles.SharedVariableAlreadyExists;
import serverFiles.SharedVariables;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Historique {

    private Conversation conversation;
    private List<Message> listeMessage;
    private String pathHistorique;

    public Historique(Conversation conversation, List<Message> messages) {
        this.conversation = conversation;
        listeMessage = messages;
        pathHistorique = "conversations/historique" + Integer.toString(conversation.getIdConversation());
        File fileHistorique = new File(pathHistorique);
        if (fileHistorique.exists()){

        }
        else{
            try {
                fileHistorique.createNewFile();
                SharedVariables sharedVariablesHistorique = new SharedVariables(fileHistorique);
                for (Message message:messages){
                    String stringID = Integer.toString(message.getID());
                    sharedVariablesHistorique.addNewSharedVariable(stringID, message.toString());
                }
                sharedVariablesHistorique.close();
            } catch (IOException ioException) {
                System.err.println("Impossible d'ouvrir le fichier \""+fileHistorique.getName()+"\"");
                ioException.printStackTrace();
            } catch (SharedVariableAlreadyExists sharedVariableAlreadyExists){
                 sharedVariableAlreadyExists.printStackTrace();
            }
        }
    }

    public Historique(Conversation conversation){
        this(conversation, new LinkedList<Message>());
    }



    public Message getLastMessage(){
        return listeMessage.get(listeMessage.size()-1);
    }

    public void addMessage(Message newMessage){

        // gestion du fichier à faire ici
        listeMessage.add(newMessage);
    }

    public void removeMessage(Message message){
        //gestion du fichier ici
        if(!listeMessage.remove(message)){
            System.err.println("Message from "+message.getExpediteur()+" dated "+message.getDate()+" not found");
        }
    }

}
