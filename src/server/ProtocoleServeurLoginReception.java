package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class ProtocoleServeurLoginReception implements IProtocoleServeur {
    @Override
    public void execute(TCPServer unServeur, Socket clientSocket) {
        String input;

        try {
            BufferedReader is = new BufferedReader(new InputStreamReader(
                    clientSocket.getInputStream()));
            PrintStream os = new PrintStream(clientSocket.getOutputStream());
            System.out.println("Réception des informations de connexion");

            if ((input = is.readLine()) != null) {
                String login;
                String mdp;
                StringTokenizer st = new StringTokenizer(input, ";", false);
                try {
                    login = st.nextToken();
                    mdp = st.nextToken();
                } catch (NoSuchElementException elementException) {
                    elementException.printStackTrace();
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}