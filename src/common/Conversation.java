package common;

import server.IContext;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public abstract class Conversation implements IContext {

    public Conversation(String nomGroupe, List<User> members) {
        this.nomGroupe = nomGroupe;
        this.membres = members;
    }

    private String nomGroupe;
    private int idConversation;
    private List<User> membres;

    public String getNomGroupe() {
        return nomGroupe;
    }
    public void setNomGroupe(String nomGroupe) {
        this.nomGroupe = nomGroupe;
    }

    public int getIdConversation() {
        return idConversation;
    }
    public void setIdConversation(int idConversation) {
        this.idConversation = idConversation;
    }

    public List<User> getMembres() {
        return membres;
    }
    public void setMembres(List<User> membres) {
        this.membres = membres;
    }

    //possibilité d'ajouter un membre add_member
    public void addMember(User member){
        if (!membres.contains(member)) {
            membres.add(member);
        }
        else {
            System.err.println("Member " +member+ " is already in the conversation members' list");
        }
    }
    //udpate_conv pour récup les derniers messages


}
