package serverFiles;

import java.io.*;

import java.lang.reflect.Constructor;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.*;

import common.User;

public class UserFileanc  { //implements InstanciateFromFile<User>
    
    // pas d'accesseurs pour ces variables d'instances, on les contrôle nous même dans cette classe
    private File userFile;
    private User user;
    private BufferedReader reader;
    // la Map qui sert à lier un string à un utilisateur
    private HashMap<Integer, User> userMap;


    public UserFileanc(File file) {
        /*On suppose que le fichier existe, on créé l'objet de type File à l'extéiruer de cette classe*/
        userFile = file;

        //On fait une sauvegarde du fichier chargé
        createSave();

        try {
            //ouverture du fichier en lecture
            FileInputStream is = new FileInputStream(userFile);
            InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
            this.reader = new BufferedReader(isr);

            String line = reader.readLine();
            User newUser = instanceFromString(line);
            if (newUser != null) {
                user = newUser;
                }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Quel que soit ce qui se passe, on passera toujours ici
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public User getUser(){
        return user;
    }

    public void createSave(){
        /* Créé une sauvegarde du fichier users
        * avec pour syntaxe users_AAAAMMJJ_HHMMSS */
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Date d = new Date();
        String savefilename = userFile.getName()+"_"+df.format(d);
        File save = new File(savefilename);
        try{
            Files.copy(userFile.toPath(), save.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
        catch(IOException e){
            System.err.println("Unable to create the save file " + savefilename);
            e.printStackTrace();
        }

    }

//    @Override
//    public void setInstanciationConstructor(Constructor constructor) {
//        constructor = constructor;
//    }

//    @Override
    public User instanceFromString(String str) {
        // Map contenant les attributs (ou fields) avec leurs valeurs
        Map<String, String> attributesMap = new HashMap<>();
//        attributesMap.
        // Remplissage de la Map
        StringTokenizer st = new StringTokenizer(str, "\n");
        while (st.hasMoreTokens()) {
            String attributValeurLue = st.nextToken();
            if (attributValeurLue.contains("=")) {
                StringTokenizer st2 = new StringTokenizer(attributValeurLue, "=", false);
                String attribute = st2.nextToken();
                String value = st2.nextToken();
                attributesMap.put(attribute, value);
                //System.out.println(attribut + " = " + valeur);
            }
        }

        if (attributesMap.get("id") != null) {
            User u = new User(Integer.parseInt(attributesMap.get("id")));
            if (attributesMap.get("pseudo") != null) {
                u.setPseudo(attributesMap.get("pseudo"));
            } else {
                System.err.println("No pseudo found for id "+u.getId());

            }
            if (attributesMap.get("centre_interet") != null) {
                String listString = attributesMap.get("centre_interet");
                listString = listString.replace("[","");
                listString = listString.replace("]","");
                u.setCentre_interet(Arrays.asList(listString.split(",")));
            } else {
                System.err.println("No centre_interet found for id "+u.getId());
            }
            return u;
        } else {
                System.err.println("No id found");
                return null;
            }
    }

//    @Override
    public void instanceToFile(User user){
        createSave();
        this.user = user;
        String idString = Integer.toString(user.getId());
        try {
            FileOutputStream os = new FileOutputStream(userFile);
            OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
            BufferedWriter writer = new BufferedWriter(osw);
            try {
                writer.write("id=" + idString, 0, idString.length());
                writer.newLine();
                writer.write("pseudo=" + user.getPseudo(), 0, idString.length());
                writer.newLine();
                writer.write("centre_interet" + user.getCentre_interet().toString(), 0, idString.length());
                writer.newLine();
            } catch (IOException e){
                System.err.println("Cannot write user instance \""+user+"\" on file");
                e.printStackTrace();
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.err.println("Cannot find file \""+userFile.getName()+"\"");
            e.printStackTrace();
        } catch (IOException e){
            System.err.println("Cannot close file \""+userFile.getName()+"\" on file");
            e.printStackTrace();
        }

    }

//    @Override
//    public void setFieldsFromString(String str) {
//
//    }

//    public User userFromLine(String line){
//        /* Renvoit un nouvel utilisateur à partir d'une ligne */
//        return new User(id, );
//        StringTokenizer st = new StringTokenizer(ligne, ",");
//        Map<String, String> attributsValeursLues = new HashMap<>();
//        while (st.hasMoreTokens()) {
//            String attributValeurLue = st.nextToken();
//            if (attributValeurLue.contains("=")) {
//                StringTokenizer st2 = new StringTokenizer(attributValeurLue, "=", false);
//                String attribut = st2.nextToken();
//                String valeur = st2.nextToken();
//                attributsValeursLues.put(attribut, valeur);
//                //System.out.println(attribut + " = " + valeur);
//            }
//    }





}
