package serverFiles;

import common.User;

import java.io.File;
import java.io.IOException;

public class MainFileInstanciation {
    public static void main(String[] args) {
        User u1 = new User(1000);
//        File file = new File("serverFiles/user1");
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
        File file2 = new File("serverFiles/test");
        try {
            boolean b = file2.createNewFile();
            System.out.println(b +"\n"+file2.getAbsolutePath()+ "\n"+file2.getParentFile());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
