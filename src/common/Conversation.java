package common;

import server.IContext;

import java.util.List;
import java.util.Observable;
import java.util.Observer;


public class Conversation implements IContext, Observer {

    private String nomGroupe;
    // Attribut final : l'id unique de chaque conversation est le port du serveur sur lequel il communique
    private final int idConversation;
    private List<User> membres;
    private Historique historique;

    public Conversation(String nomGroupe, List<User> members, int idConversation) {
        this.nomGroupe = nomGroupe;
        this.membres = members;
        this.idConversation = idConversation;
        this.historique = new Historique(this);
    }

    public Conversation(String nomGroupe, List<User> members) {
        this.nomGroupe = nomGroupe;
        this.membres = members;
        this.idConversation = newId();
        this.historique = new Historique(this);
    }

    protected int newId(){
        return 0;
    };

    public int getID(){return idConversation;}

    public String getNomGroupe() {
        return nomGroupe;
    }
    public void setNomGroupe(String nomGroupe) {
        this.nomGroupe = nomGroupe;
    }

    public int getIdConversation() {
        return idConversation;
    }
//    public void setIdConversation(int idConversation) {
//        this.idConversation = idConversation;
//    }

    public Historique getHistorique(){
        return historique;
    }

    public List<User> getMembres() {
        return membres;
    }
    public void setMembres(List<User> membres) {
        this.membres = membres;
    }

    //possibilité d'ajouter un membre add_member
    public void addMember(User member){
        // gestion fichier
        if (!membres.contains(member)) {
            membres.add(member);
        }
        else {
            System.err.println("Member " +member+ " is already in the conversation members' list");
        }
    }
    public void removeMember(User member){
        if (!membres.remove(member)) {
            System.err.println("Can't remove Member " +member+ ", User not in Conversation "+idConversation);
        }
    }

    @Override
    public void update(Observable o, Object arg) {

    }
    //udpate_conv pour récup les derniers messages

}
