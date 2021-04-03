package common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    private final int id;
    private String pseudo;
    private List<String> centre_interet;
    private List<Integer> listIDConversations;


    public User(int userID, String pseudo, List<String> centre_interet, List<Integer> listIDConversations){
        id = userID;
        this.pseudo = pseudo;
        this.centre_interet = centre_interet;
        this.listIDConversations = listIDConversations;
    }

    public User(int userID){
        this(userID, "user"+userID, new ArrayList<String>(), new ArrayList<Integer>());
    }

    public int getId() {
        return id;
    }

    public List<Integer> getIDConversations(){
        return listIDConversations;
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

    public List<String> getCentre_interet() {
        return centre_interet;
    }

    public void setCentre_interet(List<String> centre_interet) {
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
}
