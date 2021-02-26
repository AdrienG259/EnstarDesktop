package server;

import java.net.Socket;

public interface IProtocole {
    //IProtocole va permettre d'implémenter une protocole conversation de
    //groupe ou conversation privée (2 personnes)
    public void execute(TCPServer unServeur, Socket clientSocket);
}
