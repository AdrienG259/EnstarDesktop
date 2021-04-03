package serverFiles;

import common.User;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileUser {

    private final File userFile;
    private final Integer userID;

    public FileUser(int userID) throws IOException {
        this.userID = userID;
        String pathDirectory = "serverFiles/users";
        File testFile = new File(pathDirectory);
        boolean exist = testFile.exists() && testFile.isDirectory();
        if (!exist){
            throw new IOException("Directory " + pathDirectory + " not found\n");
        }
        userFile = new File(pathDirectory +"\\"+userID);
        if (!userFile.exists()){
            if(!userFile.createNewFile()){
                throw  new IOException("Cannot create file \""+pathDirectory +"\\"+userID+"\"");
            }
        }

    }

    public User userFromFile() throws IOException, SharedVariableCannotAccess {
        /* Structure d'un fichier User:
        * {
        * id = 555\n
        * pseudo = "toto"\n
        * centre_interet = ["sport",...]\n
        * list_conversations = [15555 ,...]\n
        * }
        * */
        SharedVariables sharedVariablesUser = new SharedVariables(userFile);

        String idString = sharedVariablesUser.accessVariable("id");
        String pseudoString = sharedVariablesUser.accessVariable("pseudo");
        String centre_interetString = sharedVariablesUser.accessVariable("centre_interet");
        String list_conversationsString = sharedVariablesUser.accessVariable("list_conversations");

        sharedVariablesUser.close();

        centre_interetString = centre_interetString.substring(1, centre_interetString.length()-1);
        String[] centre_interetArray = centre_interetString.split(",");
        ArrayList<String> centre_interet = new ArrayList<>(Arrays.asList(centre_interetArray));

//        list_conversationsString = list_conversationsString.replaceFirst("\\[", "");
        list_conversationsString = list_conversationsString.substring(1, list_conversationsString.length()-1);
        String[] listIDConversationsArrayString = list_conversationsString.split(",");
        List<Integer> listIDConversations = Arrays.asList(Arrays.stream(listIDConversationsArrayString).map(Integer::parseInt).toArray(Integer[]::new));
//        List<Integer> listIDConversations = Arrays.asList(Arrays.stream(listIDConversationsArrayString).map(s-> Integer.parseInt(s))));

        return new User(Integer.parseInt(idString), pseudoString, centre_interet, listIDConversations);
    }

    public boolean deleteUserFile() throws IOException {
//        try {
//            Files.delete(Paths.get("serverFiles/user/1514"));
//        }
//        catch(IOException io){
//            io.printStackTrace();
//        }
//        return false;
        return userFile.delete();
    }

    public void updateFile(User user) throws IOException {
        if (user.getId() != userID){
            throw new RuntimeException("User "+user.getId() +" is not user "+userID+ " associate to this FileUser instance\n");
        }

        SharedVariables sharedVariablesUser = new SharedVariables(userFile);
        /* Field id */
        try{
            sharedVariablesUser.setVariable("id", Integer.toString(user.getId()));
        } catch (SharedVariableCannotAccess sharedVariableCannotAccess) {
            // si on ne peut pas trouver la variable ID
            try {
                sharedVariablesUser.addNewSharedVariable("id", Integer.toString(user.getId()));
            } catch (SharedVariableAlreadyExists sharedVariableAlreadyExists) {
                System.err.println("Cannot create nor access to shared variable \"id\" in file "+userFile.getName());
                sharedVariableAlreadyExists.printStackTrace();
                sharedVariablesUser.close();
            }
        }
        /* Field pseudo */
        try{
            sharedVariablesUser.setVariable("pseudo", user.getPseudo());
        } catch (SharedVariableCannotAccess sharedVariableCannotAccess) {
            // si on ne peut pas trouver la variable ID
            try {
                sharedVariablesUser.addNewSharedVariable("pseudo", user.getPseudo());
            } catch (SharedVariableAlreadyExists sharedVariableAlreadyExists) {
                System.err.println("Cannot create nor access to shared variable \"pseudo\" in file "+userFile.getName());
                sharedVariableAlreadyExists.printStackTrace();
                sharedVariablesUser.close();
            }
        }
//        user.getIDConversations().getClass().getDeclaredField("id").get().toString();
        /* Field centre_interet */
        try{
            sharedVariablesUser.setVariable("centre_interet", user.getCentre_interet().toString());
        } catch (SharedVariableCannotAccess sharedVariableCannotAccess) {
            // si on ne peut pas trouver la variable ID
            try {
                sharedVariablesUser.addNewSharedVariable("centre_interet", user.getCentre_interet().toString());
            } catch (SharedVariableAlreadyExists sharedVariableAlreadyExists) {
                System.err.println("Cannot create nor access to shared variable \"centre_interet\" in file "+userFile.getName());
                sharedVariableAlreadyExists.printStackTrace();
                sharedVariablesUser.close();
            }
        }
        /* Field list_conversations */
        try{
            sharedVariablesUser.setVariable("list_conversations", user.getIDConversations().toString());
        } catch (SharedVariableCannotAccess sharedVariableCannotAccess) {
            // si on ne peut pas trouver la variable ID
            try {
                sharedVariablesUser.addNewSharedVariable("list_conversations", user.getIDConversations().toString());
            } catch (SharedVariableAlreadyExists sharedVariableAlreadyExists) {
                System.err.println("Cannot create nor access to shared variable \"list_conversations\" in file "+userFile.getName());
                sharedVariableAlreadyExists.printStackTrace();
                sharedVariablesUser.close();
            }
        }

        sharedVariablesUser.close();
    }
}
