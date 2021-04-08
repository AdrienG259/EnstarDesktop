package server.Protocoles;

import common.Conversation;
import common.Message;
import common.User;
import server.IContext;
import server.IProtocole;
import serverFiles.InstantiateSerializable;

import java.io.*;

public class ProtocoleSendMessage implements IProtocole {

    @Override
    public void execute(IContext aContext, InputStream anInputStream, OutputStream anOutputStream) {
        /* ProtocoleSendMessage sert à RECEVOIR  le message côté serveur, envoyé côté client par ControleurConversation
        dans la méthode sendMessage.*/

        Conversation conversation = (Conversation) aContext;
        Message message;
        PrintStream os = new PrintStream(anOutputStream);

        try {
            ObjectInputStream ois = new ObjectInputStream(anInputStream);
            if ((message = (Message) ois.readObject()) != null) {
                conversation.getHistorique().addMessage(message);
                os.println("0");
            } else{
                os.println("-1");
            }
        } catch (IOException | ClassNotFoundException ioException) {
            ioException.printStackTrace();
        }
    }
}
