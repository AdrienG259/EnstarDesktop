package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class ServeurTCP extends Thread{

	private static int nbConnexions = 0;
	
	/** Maximum de connexions client autorisées */
	private final int maxConnexions;
	
	private Socket clientSocket;

	private final IContext contexte;
	
	private final IProtocole protocole;
	
	private final int numeroPort;

//	public ServeurTCP(int unNumeroPort) {
//		numeroPort = unNumeroPort;
//		maxConnexions = Integer.MAX_VALUE;
//	}

	public ServeurTCP(IContext b, IProtocole p, int port) {
//		this(port);
		numeroPort = port;
		maxConnexions = Integer.MAX_VALUE;
		contexte = b;
		protocole = p;
	}

	public String toString() {        
		return "[ServeurTCP] Port : " +  numeroPort + ", Contexte: " + contexte ;
	} 

	public void run() {

		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket ( numeroPort );
		} catch (IOException e) {
			System.out.println("Could not listen on port: " + numeroPort + ", " + e);
			System.exit(1);
		}

		/* On autorise maxConnexions traitements*/
		while (nbConnexions <= maxConnexions) {
			try {
				System.out.println(" Attente du serveur pour la communication d'un client " );
				clientSocket = serverSocket.accept();
				nbConnexions ++;
				System.out.println("Nb clients : " + nbConnexions);
			} catch (IOException e) {
				System.out.println("Accept failed: " + serverSocket.getLocalPort() + ", " + e);
				System.exit(1);
			}
			ProcessusServer st = new ProcessusServer( clientSocket , this );
			st.start();
		}
		System.out.println(nbConnexions + " clients connectés. Maximum autorisé atteint");

		try {
			serverSocket.close();
			nbConnexions --;
		} catch (IOException e) {
			System.out.println("Could not close");
		}
	}

	public IProtocole getProtocole() {
		return protocole;
	}

	public IContext getContexte() {
		return contexte;
	}
		

}
