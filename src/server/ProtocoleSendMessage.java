package server;

import common.User;
import serverFiles.InstantiateSerializable;

import java.io.*;

public class ProtocoleSendMessage implements IProtocole{

    @Override
    public void execute(IContext aContext, InputStream anInputStream, OutputStream anOutputStream) {
        //aContext pas encore utilisé car on sait pas encore ce que c'est : dépend des cas
        // protocole conversation : conversation etc ...
        /* Pour l'instant Protocole inutile car non lié à un contexte
        * Il faudrait que le morceau de code en dessous qui permet de récupérer un objet sérialisé soit mis dans
        * une classe spécialement créée*/


    }
}
