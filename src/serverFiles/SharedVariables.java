package serverFiles;

import java.io.*;
import java.util.Map;
import java.util.HashMap;
import java.util.regex.PatternSyntaxException;

public class SharedVariables {
    private File sharedVariablesFile;
    private Map<String,Long> pointersMap;
    private final RandomAccessFile randomAccessFile;

    public SharedVariables(File file) throws IOException {
        sharedVariablesFile = file;
        randomAccessFile = new RandomAccessFile(sharedVariablesFile, "rw");
        pointersMap = new HashMap<>();

        long pointer = randomAccessFile.getFilePointer();
        String bufferString = randomAccessFile.readLine();

        while(bufferString != null){
            updateMap(bufferString, pointer);
            pointer = randomAccessFile.getFilePointer();
            bufferString = randomAccessFile.readLine();
        }
        long sharedVariablePointer;
        synchronized (randomAccessFile) {
            randomAccessFile.seek(randomAccessFile.length());
            // on get le pointeur à cette position
            sharedVariablePointer = randomAccessFile.getFilePointer();
        }

    }

    private void updateMap(String line, Long linePointer) throws IOException {
        /* Fonction interne à la classe, utile dans le constructeur et pour
        l'ajout de nouvelles variables */
        try {
            String[] sharedVariableAndValue = line.split("=");
            // On get la clé qui est le nom de la variable
            String sharedVariable = sharedVariableAndValue[0];
//            String sharedVariableValue = sharedVariableAndValue[1];

            //devient la valeur de la clé associée à la Map
            pointersMap.put(sharedVariable, linePointer);
        } catch (PatternSyntaxException patternSyntaxException) {
            System.err.println("Erreur à la ligne n°"+linePointer+ " : \""+line+"\"");
            patternSyntaxException.printStackTrace();
        }
    }

    public String accessVariable(String variableName) throws IOException, NullPointerException {
       Long pointer = pointersMap.get(variableName);
       //synchronisation du fichier pour être sûr que le pointer ne sera pas changé par un autre appel à randomAcessFile

        if (pointer == null){
            throw new NullPointerException("Shared variable "+variableName+" does not map with any value");
        }

        String variableString;
        synchronized (randomAccessFile){
           randomAccessFile.seek(pointer);
           variableString = randomAccessFile.readLine();
       }
        String sharedVariableValue = null;
        try {
            String[] sharedVariableAndValue = variableString.split("=");
            // On get la valeur de la clé;
            sharedVariableValue = sharedVariableAndValue[1];
        } catch (PatternSyntaxException patternSyntaxException) {
            System.err.println("Erreur à la ligne n°"+pointer+ " : \""+variableString+"\"");
            patternSyntaxException.printStackTrace();
        }
        return sharedVariableValue;
    }

    public String setVariable(String variableName, String variableValue) throws IOException, NullPointerException {
        /* set la nouvelle valeur et renvoit l'ancienne */
        Long pointer = pointersMap.get(variableName);
        //synchronisation du fichier pour être sûr que le pointer ne sera pas changé par un autre appel à randomAcessFile

        if (pointer == null){
            throw new NullPointerException("Shared variable "+variableName+" does not map with any any value");
        }

        String variableString;
        synchronized (randomAccessFile){
            randomAccessFile.seek(pointer);
            variableString = randomAccessFile.readLine();
        }
        String sharedVariableValue = null;
        try {
            String[] sharedVariableAndValue = variableString.split("=");
            // On get la valeur de la clé;
            sharedVariableValue = sharedVariableAndValue[1];
        } catch (PatternSyntaxException patternSyntaxException) {
            System.err.println("Erreur à la ligne n°"+pointer+ " : \""+variableString+"\"");
            patternSyntaxException.printStackTrace();
        }

        // On set la nouvelle valeur

        String line = variableName + "=" + variableValue+"\n";
        synchronized (randomAccessFile){
            randomAccessFile.seek(pointer);
            randomAccessFile.writeBytes(line);
        }

        return sharedVariableValue;
    }

    public void addNewSharedVariable(String variableName, String variableValue) throws NullPointerException, IOException {
        // on vérifie déjà que cette variable n'existe pas
        Long pointer = pointersMap.get(variableName);
        //synchronisation du fichier pour être sûr que le pointer ne sera pas changé par un autre appel à randomAcessFile
        if (pointer != null){
            throw new NullPointerException("Shared variable "+variableName+" already mapped");
        }

        // sinon on ajoute la clé et la valeur (pointeur)
        synchronized (randomAccessFile){
            randomAccessFile.seek(randomAccessFile.length());
            pointer = randomAccessFile.getFilePointer();
        }
        pointersMap.put(variableName, pointer);

        // et on écrit dans le fichier la variable partagée et sa valeur
        String line = variableName + "=" + variableValue+"\n";
        synchronized (randomAccessFile){
            randomAccessFile.seek(pointer);
            randomAccessFile.writeBytes(line);
        }
    }
}
