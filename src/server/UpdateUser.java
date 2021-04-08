package server;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UpdateUser{

    public HashMap<String, String> userMap;

    // sert à faire un update de fichier users pour prendre en compte les modifications de la hashmap
    public UpdateUser(HashMap<String, String> userMap) {
        this.userMap = userMap;

        updatefile(userMap);
    }

    private void updatefile(HashMap<String, String> userMap) {

        // on réécrit
        try {
            File fileOne=new File("users");
            FileOutputStream fos=new FileOutputStream(fileOne);
            ObjectOutputStream oos=new ObjectOutputStream(fos);

            oos.writeObject(userMap);
            oos.flush();
            oos.close();
            fos.close();
        } catch(Exception e) {}

        //read from file
        try {
            File toRead=new File("users");
            FileInputStream fis=new FileInputStream(toRead);
            ObjectInputStream ois=new ObjectInputStream(fis);

            HashMap<String,String> mapInFile = (HashMap<String,String>)ois.readObject();

            ois.close();
            fis.close();
            //print All data in MAP
            for(Map.Entry<String,String> m :mapInFile.entrySet()){
                System.out.println(m.getKey()+" : "+m.getValue());
            }
        } catch(Exception e) {}
    }
}
