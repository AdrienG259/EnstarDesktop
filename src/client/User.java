package client;

import java.util.List;

public class User {
    private int id;
    private String pseudo;
    private List centre_interet;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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



}
