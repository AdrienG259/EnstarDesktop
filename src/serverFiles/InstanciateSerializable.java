package serverFiles;

import common.User;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InstanciateSerializable implements InstanciateFromFile<Serializable>{

    private final String absolutePathDirectory;

    public InstanciateSerializable(String pathDirectory) throws IOException {
        /*On suppose que le fichier existe, on créé l'objet de type File à l'extéiruer de cette classe*/
//        File testFile = new File(pathDirectory+"\\test");
//        boolean exist = testFile.exists();
//        if (!exist){
//            throw new IOException("Directory " + pathDirectory + " not found\n");
//        }
//        else{
//            this.absolutePathDirectory = testFile.getAbsolutePath();
//        }
        File testFile = new File(pathDirectory);
        boolean exist = testFile.exists() && testFile.isDirectory();
        if (!exist){
            throw new IOException("Directory " + pathDirectory + " not found\n");
        }
        else{
            this.absolutePathDirectory = testFile.getAbsolutePath();
        }
    }

    public String getAbsolutePathDirectory() {
        return absolutePathDirectory;
    }

    public void createSave(File file){
        /* Créé une sauvegarde du fichier users
         * avec pour syntaxe users_AAAAMMJJ_HHMMSS */
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Date d = new Date();
        String savefilename = absolutePathDirectory+file.getName()+"_"+df.format(d);
        File save = new File(savefilename);
        try{
            Files.copy(file.toPath(), save.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
        catch(IOException e){
            System.err.println("Unable to create the save file " + savefilename);
            e.printStackTrace();
        }
    }
    @Override
    public Serializable fileToInstance(File file) throws IOException, ClassNotFoundException{
        FileInputStream fis=new FileInputStream(file);
        ObjectInputStream ois=new ObjectInputStream(fis);
        Serializable so =(Serializable)ois.readObject();
        ois.close();
        fis.close();
        return so;
    }

    @Override
    public File instanceToFile(Serializable serializableObject, String filename) throws IOException{
//        createSave();
        File file = new File(absolutePathDirectory + "\\" +filename);
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos=new ObjectOutputStream(fos);
        oos.writeObject(serializableObject);
        oos.flush();
        oos.close();
        fos.close();
        return file;
    }

}
