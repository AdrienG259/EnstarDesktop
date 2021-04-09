package common;

import server.IContext;
import java.util.*;


public class Conversation extends Observable implements IContext {

    private String nomGroupe;
    // Attribut final : l'id unique de chaque conversation est le port du serveur sur lequel il communique
    private final int idConversation;
    private List<User> membres;
    private Historique historique;
    private List<String> listDatesLastChanges;

    public Conversation(String nomGroupe, List<User> members, int idConversation) {
        this.nomGroupe = nomGroupe;
        this.membres = members;
        this.idConversation = idConversation;
        this.historique = new Historique();
        this.listDatesLastChanges = new ArrayList<String>(3);
    }

    public int getID(){return idConversation;}

    public String getNomGroupe() {
        return nomGroupe;
    }

    public void setNomGroupe(String nomGroupe) {
        this.nomGroupe = nomGroupe;
    }

    public Historique getHistorique(){
        return historique;
    }

    public void setHistorique(Historique historique){
        this.historique = historique;
    }

    public List<String> getListDatesLastChanges() {
        return listDatesLastChanges;
    }

    public void setListDatesLastChanges(List<String> listDatesLastChanges) {
        this.listDatesLastChanges = listDatesLastChanges;
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
            setChanged();
            notifyObservers();
        }
        else {
            System.err.println("Le membre " +member+ " appartient déjà à la conversation");
        }
    }

    public void removeMember(User member){
        if (!membres.remove(member)) {
            System.err.println("Le membre " + member + " n'appartient pas à la conversation " + idConversation);
        } else {
            setChanged();
            notifyObservers();
        }
    }

    @Override
    public String toString() {
        return nomGroupe;
    }
}
