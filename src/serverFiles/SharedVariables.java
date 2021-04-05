package serverFiles;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Stream;

public class SharedVariables{
    private final Map<String,File> fileMap;
    private final Path directoryPath;


    public SharedVariables(String directoryPath) throws IOException {
        Path directoryPath1;
        directoryPath1 = Paths.get(directoryPath);
        if(!Files.isDirectory(directoryPath1)){
            directoryPath1 = Files.createDirectories(directoryPath1);
        }
        this.directoryPath = directoryPath1;
        Stream<File> filesStream = Files.walk(this.directoryPath).filter(Files::isRegularFile).map(Path::toFile);
        fileMap = new HashMap<>();
        filesStream.forEach((File file)->fileMap.put(file.getName(), file));
        filesStream.close();

    }

    public synchronized String accessVariable(String variableName) throws IOException, SharedVariableCannotAccess {
        File variableFile = fileMap.get(variableName);

        if (variableFile == null){
            throw new SharedVariableCannotAccess(variableName);
        }

        long bytesLength = variableFile.length();
        byte[] bytesArray = new byte[(int) bytesLength];
        RandomAccessFile accessFile = new RandomAccessFile(variableFile, "r");
        accessFile.readFully(bytesArray);
        accessFile.close();
        return new String(bytesArray);
    }

    public synchronized String setVariable(String variableName, String variableValue) throws IOException, SharedVariableCannotAccess {

        File variableFile = fileMap.get(variableName);

        if (variableFile == null){
            throw new SharedVariableCannotAccess(variableName);
        }

        long bytesLength = variableFile.length();
        byte[] bytesArray = new byte[(int) bytesLength];
        RandomAccessFile accessFile = new RandomAccessFile(variableFile, "rw");
        accessFile.readFully(bytesArray);

        accessFile.seek(0);
        accessFile.write(new byte[(int) bytesLength]);
        accessFile.seek(0);
        accessFile.writeBytes(variableValue);

        accessFile.close();
        return new String(bytesArray);
    }

    public synchronized void addNewSharedVariable(String variableName, String variableValue) throws IOException, SharedVariableAlreadyExists {

        // on vérifie déjà que cette variable n'existe pas
        File variableFile = fileMap.get(variableName);

        if (variableFile != null){
            throw new SharedVariableAlreadyExists(variableName);
        }
        variableFile = new File(directoryPath+"/"+variableName);
        if(!variableFile.createNewFile()){
            fileMap.put(variableName, variableFile);
            System.err.println("Share variable already exist but was not indexed to shared variables map");
            System.err.println("Share variable "+variableName+" has been indexed then");
            throw new SharedVariableAlreadyExists(variableName);
        }
        fileMap.put(variableName, variableFile);

        long bytesLength = variableFile.length();
        byte[] bytesArray = new byte[(int) bytesLength];
        RandomAccessFile accessFile = new RandomAccessFile(variableFile, "rw");
        accessFile.readFully(bytesArray);

        accessFile.seek(0);
        accessFile.write(new byte[(int) bytesLength]);
        accessFile.seek(0);
        accessFile.writeBytes(variableValue);

        accessFile.close();

    }

    public synchronized String deleteSharedVariable(String variableName) throws IOException, SharedVariableCannotAccess {
        /* Supprime la variable partagée et renvoie sa dernière valeur */

        // On récupère la dernière valeur
        String sharedVariableOldValue = accessVariable(variableName);

        // on vérifie déjà si cette variable existe
        File variableFile = fileMap.remove(variableName);
        if (variableFile == null){
            throw new SharedVariableCannotAccess(variableName);
        }
        if(!variableFile.delete()){
            throw new IOException("Can not delete file"+variableFile.getAbsolutePath());
        }
        return sharedVariableOldValue;
    }

    public synchronized List<String> getSharedVariablesNames(){
        /*Equivalent à :*/
        //List<String> stringList = new ArrayList<>();
        //stringList.addAll(pointersMap.keySet());
        return new ArrayList<>(fileMap.keySet());
    }
}
