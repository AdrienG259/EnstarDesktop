package serverFiles;

import java.io.*;
import java.util.*;
import java.util.regex.PatternSyntaxException;

public class SharedVariables implements Closeable{
    private final Map<String,Long> pointersMap;
    private final RandomAccessFile randomAccessFile;

    public SharedVariables(File file) throws IOException {
        randomAccessFile = new RandomAccessFile(file, "rw");
        pointersMap = new HashMap<>();
        randomAccessFile.readLine();
        long pointer = randomAccessFile.getFilePointer();
        String bufferString = randomAccessFile.readLine();

        while(bufferString != null){
            updateMap(bufferString, pointer);
            pointer = randomAccessFile.getFilePointer();
            bufferString = randomAccessFile.readLine();
        }
    }

    private void updateMap(String line, Long linePointer) {
        /* Fonction interne à la classe, utile dans le constructeur et pour
        l'ajout de nouvelles variables */
        try {
            StringTokenizer st = new StringTokenizer(line);
            String sharedVariable = st.nextToken("=");
            // On get la valeur de la clé;
//            String sharedVariable = st.nextToken("=");
//            String[] sharedVariableAndValue = line.split("=");
            // On get la clé qui est le nom de la variable
//            String sharedVariable = sharedVariableAndValue[0];
//            String sharedVariableValue = sharedVariableAndValue[1];

            //devient la valeur de la clé associée à la Map
            pointersMap.put(sharedVariable, linePointer);
        } catch (PatternSyntaxException patternSyntaxException) {
            System.err.println("Erreur à la ligne n°"+linePointer+ " : \""+line+"\"");
            patternSyntaxException.printStackTrace();
        }
    }

    public String accessVariable(String variableName) throws IOException, SharedVariableCannotAccess {
       Long pointer = pointersMap.get(variableName);

        if (pointer == null){
            throw new SharedVariableCannotAccess(variableName);
        }

        String variableString;
        //synchronisation du fichier pour être sûr que le pointer ne sera pas changé par un autre appel à randomAcessFile

        synchronized (randomAccessFile){
           randomAccessFile.seek(pointer);
           variableString = randomAccessFile.readLine();
        }
        String sharedVariableValue = null;
        try {
            StringTokenizer st = new StringTokenizer(variableString);
            st.nextToken("=");
            // On get la valeur de la clé;
            sharedVariableValue = st.nextToken("=");
//            String[] sharedVariableAndValue = variableString.split("=");
//            sharedVariableValue = sharedVariableAndValue[1];
        } catch (NoSuchElementException noSuchElementException) {
            System.err.println("Erreur à la ligne n°"+pointer+ " : \""+variableString+"\"");
            noSuchElementException.printStackTrace();
        }
        return sharedVariableValue;
    }

    public String setVariable(String variableName, String variableValue) throws IOException, SharedVariableCannotAccess {

        /* set la nouvelle valeur et renvoit l'ancienne */
        Long pointer = pointersMap.get(variableName);
        //synchronisation du fichier pour être sûr que le pointer ne sera pas changé par un autre appel à randomAccessFile

        if (pointer == null){
            throw new SharedVariableCannotAccess(variableName);
        }

        // On get l'ancienne valeur
        String sharedVariableOldValue = accessVariable(variableName);

        // On set la nouvelle valeur

        String line = variableName + "=" + variableValue+"\n";
        synchronized (randomAccessFile){
            randomAccessFile.seek(pointer);
            randomAccessFile.writeBytes(line);
        }

        return sharedVariableOldValue;
    }

    public void addNewSharedVariable(String variableName, String variableValue) throws SharedVariableAlreadyExists, IOException {
        // on vérifie déjà que cette variable n'existe pas
        Long pointer = pointersMap.get(variableName);

        if (pointer != null){
            throw new SharedVariableAlreadyExists(variableName);
        }

        //synchronisation du fichier pour être sûr que le pointer ne sera pas changé par un autre appel à randomAccessFile
        synchronized (randomAccessFile){
            randomAccessFile.seek(randomAccessFile.length());
            pointer = randomAccessFile.getFilePointer();
        }
        // sinon on ajoute la clé et la valeur (pointeur)
        pointersMap.put(variableName, pointer);

        // et on écrit dans le fichier la variable partagée et sa valeur
        String line = variableName + "=" + variableValue+"\n";
        synchronized (randomAccessFile){
            randomAccessFile.seek(pointer);
            randomAccessFile.writeBytes(line);
        }
    }

    public String deleteSharedVariable(String variableName) throws IOException, SharedVariableCannotAccess {
        /* Supprime la variable partagée et renvoie sa dernière valeur */

        // On récupère la dernière valeur
        String sharedVariableOldValue = accessVariable(variableName);

        // on vérifie déjà si cette variable existe
        Long pointer = pointersMap.remove(variableName);
        if (pointer == null){
            throw new SharedVariableCannotAccess(variableName);
        }

        return sharedVariableOldValue;
    }

    public List<String> getSharedVariablesNames(){
        /*Equivalent à :*/
        //List<String> stringList = new ArrayList<>();
        //stringList.addAll(pointersMap.keySet());
        return new ArrayList<>(pointersMap.keySet());
    }

    public void close() throws IOException {
        /* Pour fermer la connection avec l'objet RandomAccessFile */
        randomAccessFile.close();
    }
}
