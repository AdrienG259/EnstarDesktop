package common;

import java.util.List;

public class ConversationGroupe extends Conversation implements IConversation {

    public ConversationGroupe(String nomGroupe, List<User> members) {
        super();
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
    //udpate_conv pour récup les derniers messages
}
