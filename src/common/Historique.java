package common;

import common.Message;

import java.util.List;

public class Historique {

//    private int idConversation;
    private List<Message> listeMessage;

//    public int getIdConversation() {
//        return idConversation;
//    }

//    public void setIdConversation(int idConversation) {
//        this.idConversation = idConversation;
//    }

    public List<Message> getListeMessage() {
        return listeMessage;
    }

//    public void setListeMessage(List<Message> listeMessage) {
//        this.listeMessage = listeMessage;
//    }

    public Message getLastMessage(){
        return listeMessage.get(listeMessage.size()-1);
    }

    public void addMessage(Message newMessage){
        listeMessage.add(newMessage);
    }

    public void removeMessage(Message message){
        if(!listeMessage.remove(message)){
            System.err.println("Message from "+message.getExpediteur()+" dated "+message.getDate()+" not found");
        }
    }

}
