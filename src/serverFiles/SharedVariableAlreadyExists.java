package serverFiles;

public class SharedVariableAlreadyExists extends Exception{
    public SharedVariableAlreadyExists(){
        super("Shared variable already exists");
    }
    public SharedVariableAlreadyExists(String s){
        super("Shared variable "+"\""+s+"\""+" already exists");
    }

}
