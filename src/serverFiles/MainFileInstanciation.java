package serverFiles;

import common.User;

import java.io.File;
import java.io.IOException;

public class MainFileInstanciation {
    public static void main(String[] args) {
        User u1 = new User(1000);
        InstanciateSerializable uf = null;
        try {
            uf = new InstanciateSerializable("serverFiles");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        try {
            uf.instanceToFile(u1, Integer.toString(u1.getId()));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        File file2 = new File("serverFiles/testSharedVariables");
        try {
            boolean b = file2.createNewFile();
            System.out.println(b +"\n"+file2.getAbsolutePath()+ "\n"+file2.getParentFile());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        try {
            SharedVariables sharedVariables = new SharedVariables(file2);
            sharedVariables.accessVariable("toto");
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
        try {
            SharedVariables sharedVariables = new SharedVariables(file2);
            sharedVariables.addNewSharedVariable("toto2", "22");
            String toto2value = sharedVariables.accessVariable("toto2");
            System.out.println("Value of toto2 : "+toto2value);
            sharedVariables.setVariable("toto2","55");
            toto2value = sharedVariables.accessVariable("toto2");
            System.out.println("New value of toto2 : "+toto2value);

        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }

        try {
            SharedVariables sharedVariables = new SharedVariables(file2);
            String toto2value = sharedVariables.accessVariable("toto2");
            System.out.println("Value of toto2 : "+toto2value);
            sharedVariables.setVariable("toto2","55");
            toto2value = sharedVariables.accessVariable("toto2");
            System.out.println("New value of toto2 : "+toto2value);

            sharedVariables.addNewSharedVariable("toto3", "23");
            String toto3value = sharedVariables.accessVariable("toto3");
            System.out.println("Value of toto3 : "+toto3value);
            sharedVariables.setVariable("toto3","53");
            toto3value = sharedVariables.accessVariable("toto3");
            System.out.println("New value of toto3 : "+toto3value);

            toto2value = sharedVariables.accessVariable("toto2");
            System.out.println("Value of toto2 : "+toto2value);

        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }


    }
}
