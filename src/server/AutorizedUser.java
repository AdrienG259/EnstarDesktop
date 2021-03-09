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
        /*userMap.put("Pierre-Olivier", "lemotdepasse");
        userMap.put("Marie-Amelie", "lemotdepasse");
        userMap.put("Julie", "lemotdepasse");
        userMap.put("Adrien", "lemotdepasse");
        userMap.put("Emilie", "lemotdepasse");
        userMap.put("t","t"); */

        lecturemap();
    }
    public void lecturemap() {

        /*try {
            File fileOne = new File("users");
            FileOutputStream fos = new FileOutputStream(fileOne);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(map);
            oos.flush();
            oos.close();
            fos.close();
        } catch (Exception e) {
        } */

        try {
            File toRead=new File("users");
            FileInputStream fis=new FileInputStream(toRead);
            ObjectInputStream ois=new ObjectInputStream(fis);

            userMap =(HashMap<String,String>)ois.readObject();

            ois.close();
            fis.close();
            //print All data in MAP
            for(Map.Entry<String,String> m :userMap.entrySet()){
                System.out.println(m.getKey()+" : "+m.getValue());
            }
        } catch(Exception e) {}

    }


    public static void main(String args[]) {
        new AutorizedUser();
    }
}

//il faut créer une classe AddUser pour pouvoir ajouter un utilisateur