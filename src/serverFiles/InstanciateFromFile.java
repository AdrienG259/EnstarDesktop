package serverFiles;

import common.User;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;

public interface InstanciateFromFile<O>{

//    public void setInstanciationConstructor(Constructor constructor); // the constructor used to instanciate
//    public D instanceFromString(String str); // instanciate an object using a string
    public O fileToInstance(File file) throws IOException, ClassNotFoundException; // instanciate an object using a string
    public File instanceToFile(O object, String filename) throws IOException; // add object to file
//    public void setFieldsFromString(String str); // filling the instance's non-constructor fields using a string

}
