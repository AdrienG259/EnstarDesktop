package serverFiles;

import common.User;

import java.io.File;
import java.io.IOException;

public class FileUser {

    private final String pathDirectory = "serverFiles/users";
    private final File userFile;


    public FileUser(int userID) throws IOException {
        File testFile = new File(pathDirectory);
        boolean exist = testFile.exists() && testFile.isDirectory();
        if (!exist){
            throw new IOException("Directory " + pathDirectory + " not found\n");
        }
        userFile = new File(pathDirectory+"\\"+userID);
        if (!userFile.exists()){
            throw new IOException("No file found for user " + userID + "\n");
        }

    }

    public User userFromFile(){
        /* Structure d'un fichier User:
        * {
        * id = 555\n
        * pseudo = "toto"\n
        * centre_interet = ["sport",...]\n
        * list_conversations = [15555 ,...]\n
        * }
        * */
        User user = new User(0000);
        return user;
    }

    public void updateFile(){

    }

}
