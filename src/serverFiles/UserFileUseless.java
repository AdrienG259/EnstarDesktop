package serverFiles;

import common.User;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.*;

public class UserFileUseless extends InstanciateSerializable{

    public UserFileUseless(String pathDirectory) throws IOException {
        super(pathDirectory);
    }

//    @Override
//    public User fileToInstance(File file) throws IOException, ClassNotFoundException{
////        (User) = super.fileToInstance(file)
//        FileInputStream fis=new FileInputStream(file);
//        ObjectInputStream ois=new ObjectInputStream(fis);
//        User user =(User)ois.readObject();
//        ois.close();
//        fis.close();
//        return user;
//    }
//
//
//    public File instanceToFile(User object) throws IOException{
////        createSave();
//        File userFile = new File(super.getAbsolutePathDirectory());
//        FileOutputStream fos = new FileOutputStream(userFile);
//        ObjectOutputStream oos=new ObjectOutputStream(fos);
//        oos.writeObject(object);
//        oos.flush();
//        oos.close();
//        fos.close();
//        return userFile;
//    }
}
