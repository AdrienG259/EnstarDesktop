package server;

import java.io.*;
import java.net.*;


public class ConnectedClientThread extends Thread {

	private Socket clientSocket;
	private TCPServer myServer;
	int etat = 0;
	
	public ConnectedClientThread( Socket aClientSocket , TCPServer aServer ) {
		clientSocket = aClientSocket;
		myServer = aServer;
	}
	
	public int getEtat() {
		return etat;
	}
	
	public void run() {
    	String inputReq,outputReq;

		try {
			/* Ouverture des objets de type Stream sur la socket du client réseau  */
			BufferedReader  is = new BufferedReader ( new InputStreamReader (clientSocket.getInputStream()));
			PrintStream os = new PrintStream(clientSocket.getOutputStream());
			
			System.out.println( "Client Thread " );
			
			while ( (inputReq = is.readLine()) != null && etat != 3 ) {
				System.out.println( " Msg 2 Recu " + inputReq );
				String chaines[] = inputReq.split( " " );
				outputReq = "Msg From Server ";
				
				for( int i = 0 ; i < chaines.length ; i++ ) { 
						outputReq = outputReq + " Indice : " + i + " Mot : " + chaines[i];
						System.out.println( outputReq );
				}
				os.println(outputReq + "\n");
				os.flush();
				
				if ( chaines[0].equals("demandeRetrait") ) { 
					etat = 1;
					myServer.getMonCompte().demandeRetrait();
					System.out.println( outputReq + " val de etat " + etat );
					
				} else if ( chaines[0].equals("demandeDepot") ) { 
					etat = 2;
					myServer.getMonCompte().demandeDepot();
					System.out.println( outputReq + " val de etat " + etat );
				} else if ( chaines[0].equals("stop") ) {
					etat = 3;
					System.out.println( outputReq + " val de etat " + etat );
				} else { 
					etat = 0;
					System.out.println( outputReq + " val de etat " + etat );
				}
				
			}
			
			clientSocket.close();
			os.close();
			is.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

    	
    }
}
