package serverFiles;

import common.User;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InstantiateSerializable<A extends Serializable> implements InstanciateFromFile<A>{

    private final String absolutePathDirectory;
    private final File fileSerializable;

    public InstantiateSerializable(File file) throws IOException {
        /*On suppose que le fichier existe, on créé l'objet de type File à l'extérieur de cette classe*/

        boolean exist = file.exists();
        absolutePathDirectory = file.getAbsolutePath().replace(file.getName(),"");
        fileSerializable = file;
        if (!exist){
            throw new IOException("File " + file.getAbsolutePath() + " not found\n");
        }
    }

    public String getAbsolutePathDirectory() {
        return absolutePathDirectory;
    }

    public void createSave(){
        /* Créé une sauvegarde du fichier users
         * avec pour syntaxe users_AAAAMMJJ_HHMMSS */
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Date d = new Date();
        String savefilename = absolutePathDirectory+fileSerializable.getName()+"_"+df.format(d);
        File save = new File(savefilename);
        try{
            Files.copy(fileSerializable.toPath(), save.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
        catch(IOException e){
            System.err.println("Unable to create the save file " + savefilename);
            e.printStackTrace();
        }
    }

    public A fileToInstance() throws IOException, ClassNotFoundException{
        FileInputStream fis=new FileInputStream(fileSerializable);
        ObjectInputStream ois=new ObjectInputStream(fis);
        A a = (A) ois.readObject();
        ois.close();
        fis.close();
        return a;
    }

    public File instanceToFile(A serializableObject) throws IOException{
//        createSave();
        FileOutputStream fos = new FileOutputStream(fileSerializable);
        ObjectOutputStream oos=new ObjectOutputStream(fos);
        oos.writeObject(serializableObject);
        oos.flush();
        oos.close();
        fos.close();
        return fileSerializable;
    }

}
