package server;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class AutorizedUser {
    //création d'une MAP des user autorisés. Sert pour l'authentification de la connexion
    public HashMap<String, String> userMap = new HashMap<String, String>();
    //key = nom / value = mdp

    //on fait que la lecture du fichier ici à partir du fichier texte
    public AutorizedUser() {

        lecturemap();
    }
    public void lecturemap() {

        try {
            File toRead=new File("users");
            FileInputStream fis=new FileInputStream(toRead);
            ObjectInputStream ois=new ObjectInputStream(fis);

            userMap =(HashMap<String,String>)ois.readObject();

            ois.close();
            fis.close();
        } catch(Exception e) {}

    }


    public static void main(String args[]) {
        new AutorizedUser();
    }
}

//il faut créer une classe AddUser pour pouvoir ajouter un utilisateur