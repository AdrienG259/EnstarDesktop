package serverFiles;

import org.junit.*;
import org.junit.runners.Parameterized;

import javax.sound.midi.SysexMessage;
import java.io.*;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class SharedVariablesTest {

    SharedVariables sharedVariables;
    HashMap<File, String> mapFile;

    @BeforeClass
    /* This method executes before running any of the test in the current Test Class. It is executed only once.
    * This method should be declared static whereas @Before don't have to be. */
    public static void beforeClass() throws IOException {

        String pathDirectory = "serverFiles/testsSharedVariable";
        pathDirectory = Files.createDirectories(Paths.get(pathDirectory)).toString();
        File testFile1 = new File(pathDirectory+"/toto1");
        File testFile2 = new File(pathDirectory+"/toto2");
        File testFile3 = new File(pathDirectory+"/toto3");
        for (File file: Arrays.asList(testFile1, testFile2, testFile3)){
            boolean doesNotExistsYet = file.createNewFile();
            if (!doesNotExistsYet){
                /* le fichier existe déjà : on efface tout son contenu */
                if (file.delete()) {
                    if (!file.createNewFile()){
                        throw new IOException("Can't create file \"serverFiles/testSharedVariables\"");
                    }
                }
                else {
                    throw new IOException("Can't delete file \"serverFiles/testSharedVariables\"");
                }
            }
        }

    }

    @AfterClass
    /* This method gets executed at the end, when all the tests have completed execution and gets executed only once.
    * Method should be declared static.*/
    public static void afterClass() throws IOException {
        String pathDirectory = "serverFiles/testsSharedVariable";
        File testFile1 = new File(pathDirectory+"/toto1");
        File testFile2 = new File(pathDirectory+"/toto2");
        File testFile3 = new File(pathDirectory+"/toto3");
        for (File file: Arrays.asList(testFile1, testFile2, testFile3)) {
            boolean successfullyDeleted = file.delete();
//        Path path = Paths.get("serverFiles/testSharedVariables");
//        try{Files.delete(path);}
//        catch(IOException e){ e.printStackTrace(); }
            if (!successfullyDeleted) {
                System.err.println("@AfterClass : Can't delete file \"serverFiles/testSharedVariables\"");
            }
        }
        Stream<File> filesStream = Files.walk(Paths.get(pathDirectory)).filter(Files::isRegularFile).map(Path::toFile);
        filesStream.forEach(File::delete);
        filesStream.close();
        Files.delete(Paths.get(pathDirectory));
    }


    @Before
    /* This method gets executed before each test execution in the current Test class. This method usually helps
    * initialize the resources required for the action test to execute. It is executed everytime before running a test method. */
    public void setUp() throws IOException {
        // on nettoie le fichier et on y met 3 variables partagées toto
        String pathDirectory = "serverFiles/testsSharedVariable";
        File testFile1 = new File(pathDirectory+"/toto1");
        File testFile2 = new File(pathDirectory+"/toto2");
        File testFile3 = new File(pathDirectory+"/toto3");
        mapFile = new HashMap<>();
        mapFile.put(testFile1, "52555");
        mapFile.put(testFile2, "ABC");
        mapFile.put(testFile3, "[1,2,3]");
        for (File file: Arrays.asList(testFile1, testFile2, testFile3)){
            FileWriter fileWriter = new FileWriter(file, false);
            PrintWriter printWriter = new PrintWriter(fileWriter, true);
            printWriter.print(mapFile.get(file));
            printWriter.close();
            fileWriter.close();
        }
        sharedVariables = new SharedVariables(pathDirectory);
    }

    @After
    /* This method gets executed after every test and it is used to clean up the test
    * and temporary data that are used by test.*/
    public void tearDown() throws IOException {
        // on nettoie le fichier et on y met 3 variables partagées toto
//        sharedVariables.close();
        for (File file: mapFile.keySet()){
            FileWriter fileWriter = new FileWriter(file, false);
            PrintWriter printWriter = new PrintWriter(fileWriter, false);
            printWriter.flush();
            printWriter.close();
            fileWriter.close();
        }
    }


    @Test
    public void setVariable() throws IOException, SharedVariableCannotAccess, SharedVariableAlreadyExists {
        // test de re-set d'une clé déjà assignée
        String valueToto1 = sharedVariables.accessVariable("toto1");
        assertEquals("52555",valueToto1);
        String stringTest = "\"les chaussettes de l'archiduchesse\"";
        sharedVariables.setVariable("toto1", stringTest);
        valueToto1 = sharedVariables.accessVariable("toto1");
        assertNotEquals("52555",valueToto1);
//        assertNotEquals("ABC",valueToto1);
//        assertNotEquals("[1,2,3]",valueToto1);
        assertEquals(stringTest,valueToto1);

        // ajout d'une nouvelle clé
        sharedVariables.addNewSharedVariable("testSetVariable", "^$!*Eyy");
        String valueTestSetVariable = sharedVariables.accessVariable("testSetVariable");
        assertNotEquals(stringTest,valueTestSetVariable);
        assertEquals("^$!*Eyy",valueTestSetVariable);

        sharedVariables.setVariable("toto3", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        String valuetoto3 = sharedVariables.accessVariable("toto3");
        assertEquals("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",valuetoto3);
        valueTestSetVariable = sharedVariables.accessVariable("testSetVariable");
        assertEquals("^$!*Eyy",valueTestSetVariable);

    }

    @Test
    public void getSharedVariablesNames() throws IOException, SharedVariableAlreadyExists {
        ArrayList<String> variablesNames = (ArrayList<String>) sharedVariables.getSharedVariablesNames();
        boolean btoto1 = variablesNames.contains("toto1");
        boolean btoto2 = variablesNames.contains("toto2");
        boolean btoto3 = variablesNames.contains("toto3");
        assertTrue(btoto1 && btoto2 && btoto3);
        sharedVariables.addNewSharedVariable("toto4", "poiuytreza515161!");
        boolean btoto4 = variablesNames.contains("toto3");
        assertTrue(btoto4);
    }


    @Test
    public void addNewSharedVariable() throws IOException, SharedVariableAlreadyExists, SharedVariableCannotAccess {
        sharedVariables.addNewSharedVariable("newVariable", "azerty");

        String valueNewVariable = sharedVariables.accessVariable("newVariable");
        String valueToto1 = sharedVariables.accessVariable("toto1");
        assertNotEquals(valueNewVariable,valueToto1);

        assertEquals("azerty", valueNewVariable);

    }

    @Test
    public void deleteSharedVariable() throws SharedVariableCannotAccess, IOException, SharedVariableAlreadyExists {
        String toto1Value = sharedVariables.deleteSharedVariable("toto1");

        assertEquals("52555", toto1Value);
        assertThrows(SharedVariableCannotAccess.class, () -> sharedVariables.accessVariable("toto1"));

        sharedVariables.addNewSharedVariable("toto1", "newValue");
        sharedVariables.addNewSharedVariable("a", "qsdf;");
        sharedVariables.addNewSharedVariable("b", "ghjk?");

        String aValue = sharedVariables.deleteSharedVariable("a");

        assertEquals("qsdf;", aValue);
        assertThrows(SharedVariableCannotAccess.class, () -> sharedVariables.accessVariable("a"));
    }

    @Test(expected = SharedVariableCannotAccess.class)
    public void accessVariableException() throws IOException, SharedVariableCannotAccess {
        try {
            Field fileMapField =sharedVariables.getClass().getDeclaredField("fileMap");
            fileMapField.setAccessible(true);
            HashMap<String, File> fileMap = (HashMap<String, File>) fileMapField.get(sharedVariables);
            // On insère un pointeur null de manière intentionnelle
            fileMap.put("toSharedVariableCannotAccess", null);
            fileMapField.setAccessible(false);
            // on teste ensuite accessVariable
            sharedVariables.accessVariable("toSharedVariableCannotAccess");
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = SharedVariableCannotAccess.class)
    public void setVariableException() throws SharedVariableCannotAccess, IOException {
        try {
            Field fileMapField =sharedVariables.getClass().getDeclaredField("fileMap");
            fileMapField.setAccessible(true);
            HashMap<String, File> fileMap = (HashMap<String, File>) fileMapField.get(sharedVariables);
            // On insère un pointeur null de manière intentionnelle
            fileMap.put("toSharedVariableCannotAccess", null);
            fileMapField.setAccessible(false);
            // on teste ensuite accessVariable
            sharedVariables.setVariable("toSharedVariableCannotAccess", "totototo");
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = SharedVariableAlreadyExists.class)
    public void addNewSharedVariableException() throws SharedVariableAlreadyExists, IOException {
        sharedVariables.addNewSharedVariable("sharedVariableAlreadyExists", "totototo1");
        sharedVariables.addNewSharedVariable("sharedVariableAlreadyExists", "totototo2");
    }

    @Test
    public void accessVariable() throws IOException, SharedVariableCannotAccess {
        String valueToto1 = sharedVariables.accessVariable("toto1");
        assertEquals("52555",valueToto1);
        String valueToto2 = sharedVariables.accessVariable("toto2");
        assertEquals("ABC",valueToto2);
        String valueToto3 = sharedVariables.accessVariable("toto3");
        assertEquals("[1,2,3]",valueToto3);

        assertNotEquals(valueToto1, valueToto2, valueToto3);
    }

}