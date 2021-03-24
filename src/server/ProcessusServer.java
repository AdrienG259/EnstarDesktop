package server;
import java.io.IOException;
import java.net.Socket;

/**
 * Processus de Transaction (anciennement ServeurSpecifique)
 */
class ProcessusServer extends Thread {

	private Socket clientSocket;
	private ServeurTCP monServeurTCP;

	public ProcessusServer(Socket uneSocket, ServeurTCP unServeur){
		super("ServeurThread");
		clientSocket = uneSocket;
		System.out.println("[ProcessusTransaction] CLIENT : " + clientSocket);
		monServeurTCP = unServeur;
	} 

	public void run() {        
		try {
			monServeurTCP.getProtocole().execute( monServeurTCP.getContexte() , clientSocket.getInputStream() , clientSocket.getOutputStream() );
			System.out.println("Processus lié au protocole" + monServeurTCP.getProtocole().toString() + " fait");
		} catch (IOException e) {
			System.err.println("[ProcessusServer] Exception : " + e );
			e.printStackTrace();
		}
	} 
}
