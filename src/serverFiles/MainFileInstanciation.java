package serverFiles;

import common.User;

import java.io.File;
import java.io.IOException;

public class MainFileInstanciation {
    public static void main(String[] args) {
        User u1 = new User(1000);
        InstantiateSerializable uf = null;
        File file = new File("serverFiles");
        try {
            uf = new InstantiateSerializable(file);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        try {
            uf.instanceToFile(u1);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
