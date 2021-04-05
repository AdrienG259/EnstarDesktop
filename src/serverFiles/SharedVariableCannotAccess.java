package serverFiles;

public class SharedVariableCannotAccess extends Exception{
    public SharedVariableCannotAccess(){
        super("Shared variable can't be accessed");
    }
    public SharedVariableCannotAccess(String s){
        super("Shared variable " + "\""+s+"\"" + " can't be accessed or does not exist");
    }

}