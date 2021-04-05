package server;

import common.User;
import serverFiles.InstantiateSerializable;

import java.io.*;

public class ProtocoleUser implements IProtocole {
    @Override
    public void execute(IContext aContext, InputStream anInputStream, OutputStream anOutputStream) {
        String inputReq;
        BufferedReader is = new BufferedReader(new InputStreamReader(
                anInputStream));

        try {
            if ((inputReq = is.readLine()) != null) {

                System.out.println(" Ordre Recu " + inputReq);
                String[] inputArray = inputReq.split(" ");
                switch (inputArray[0]){
                    case "createUser": createUser(inputArray, anOutputStream);
                    case "deleteUser": deleteUser(inputArray, anOutputStream);
                    case "getUser": getUser(inputArray, anOutputStream);
                    default:
                        PrintStream os = new PrintStream(anOutputStream);
                        os.println("-1"); os.close();
                }


            }
        } catch ( IOException ioException) {
            System.err.println(" Problème d'exception IO sur un OutputStream");
            ioException.printStackTrace();
        }
    }

    private void getUser(String[] inputArray, OutputStream anOutputStream) throws IOException {

        int userID = Integer.parseInt(inputArray[1]);

        ObjectOutputStream oos = new ObjectOutputStream(anOutputStream);

        try {
            File userFile = new File("serverFiles/users/" + userID);
            InstantiateSerializable<User> userInstantiate = new InstantiateSerializable<User>(userFile);
            User user = userInstantiate.fileToInstance();
            oos.writeObject(user);
        } catch (ClassNotFoundException classNotFoundException){
            System.err.println("Can not instanciate user "+userID+" from file");
            oos.writeObject(null);
        } finally {
            oos.flush();
            oos.close();
        }
    }

    private void createUser(String[] loginpasswordArray, OutputStream outputStream) throws IOException {

        String login = loginpasswordArray[1];
        String password = loginpasswordArray[2];

        AutorizedUser autorizedUsers = new AutorizedUser();
        AddUser adduser = new AddUser(autorizedUsers, login, password);
        int can_add = adduser.ajouterUser();
        UpdateUser updateUser = new UpdateUser(autorizedUsers.userMap);
        String messageRetour = can_add +"\n";

        PrintStream os = new PrintStream(outputStream);
        os.println(messageRetour);
        os.close();

    }

    private void deleteUser(String[] loginArray, OutputStream outputStream) throws IOException {

        String login = loginArray[1];

        AutorizedUser autorizedUsers = new AutorizedUser();
        DeleteUser del = new DeleteUser(autorizedUsers, login);
        del.SupprimerUser();

        PrintStream os = new PrintStream(outputStream);
        os.println("0");
        os.close();

    }

}
