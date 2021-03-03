package serverFiles;

import common.User;

import java.io.File;
import java.lang.reflect.Constructor;

public interface InstanciateFromFile<D>{

//    public void setInstanciationConstructor(Constructor constructor); // the constructor used to instanciate
    public D instanceFromString(String str); // instanciate an object using a string
    public void instanceToFile(D object); // add object to file
//    public void setFieldsFromString(String str); // filling the instance's non-constructor fields using a string

}
