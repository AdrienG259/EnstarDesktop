package common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    private final int id;
    private String pseudo;
    private List centre_interet;
    private List<Conversation> listConversations;

    public User(int userID){
        id = userID;
    }

    public int getId() {
        return id;
    }

    public List<Conversation> getConversations(){
        return listConversations;
    }
    /* Pas de setter car id est final */
//    public void setId(int id) {
//        this.id = id;
//    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public List getCentre_interet() {
        return centre_interet;
    }

    public void setCentre_interet(List centre_interet) {
        this.centre_interet = centre_interet;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", pseudo='" + pseudo + '\'' +
                ", centre_interet=" + centre_interet +
                '}';
    }
    public List<Conversation> getConversation(){
        List<Conversation> liste = null; //à changer pour récupérer la liste des conversations
        return liste;
    }
}
