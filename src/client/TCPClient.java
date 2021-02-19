package client;

import java.io.*;
import java.net.*;


public class TCPClient {
		private int numeroPort;
		private String nomServeur;

		private Socket socketServeur;
		private PrintStream socOut;
		private BufferedReader socIn;	
		
		/** Un client se connecte a un serveur identifie par un nom (unNomServeur), sur un port unNumero */
		public  TCPClient(String unNomServeur, int unNumero) {        
			numeroPort = unNumero;
			nomServeur = unNomServeur;
		} 

		public boolean connectToServer() throws Exception {        
			boolean ok = false;
			try {
				System.out.println("Tentative : " + nomServeur + " -- " + numeroPort);
				socketServeur = new Socket( nomServeur, numeroPort);
				socOut = new PrintStream(socketServeur.getOutputStream());
				socIn = new BufferedReader ( 
						new InputStreamReader (socketServeur.getInputStream()));
				ok = true;
			} catch (UnknownHostException e) {
				System.err.println("Serveur inconnu : " + e);
				throw e;

			} catch (ConnectException e) {
				System.err.println("Exception lors de la connexion:" + e);
				e.printStackTrace();
				throw e;
			} catch (IOException e) {
				System.err.println("Exception lors de l'echange de donnees:" + e);
				throw e;
			}
			System.out.println("Connexion terminée");
			return ok;
		} 	
		
		public void disconnectFromServer() throws Exception {        
			try {
				System.out.println("[ClientTCP] CLIENT : " + socketServeur);
				socOut.close();
				socIn.close();
				socketServeur.close();
			} catch (Exception e) {
				System.err.println("Exception lors de la deconnexion :  " + e);
				throw e;
			}
		} 	
		

		public String stringTransmitAndServerConnection(String uneChaine) throws Exception {
			String msgServeur = null;
			String chaineRetour = "";
			System.out.println("\nClient connexionTransmettreChaine " + uneChaine);

			if (connectToServer() == true) {
				try {
					socOut.println(uneChaine);
					socOut.flush();

					/*  code si serveur repond 
					msgServeur = socIn.readLine();
					while( msgServeur != null && msgServeur.length() >0) {
						chaineRetour += msgServeur + "\n";
						msgServeur = socIn.readLine();
					}
					System.out.println("Client msgServeur " + chaineRetour);
					*/
					
					
					disconnectFromServer();
				} catch (Exception e) {
					System.err.println("Exception lors de la connexion client:  " + e);
				}
			}
			else
			{	
				System.err.println("Connexion echouee");
			}
			return chaineRetour;
		}

	public String stringTransmitOnly(String uneChaine) {
		String msgServeur = null;
		String chaineRetour = "";
		System.out.println("\nClient TransmettreChaine Only " + uneChaine);

		try {
			socOut.println(uneChaine);
			socOut.flush();

			
			 //* code si serveur repond 
			 
			 if ( (msgServeur = socIn.readLine()) == null ) return null; 
			 while( msgServeur != null && msgServeur.length() >0) { 
				 chaineRetour += msgServeur + "\n"; 
				 msgServeur = socIn.readLine(); 
			 }
			 System.out.println("Client chaineRetour " + chaineRetour);
			 

		} catch (Exception e) {
			System.err.println("Exception lors de la connexion client:  " + e);
		}
		return chaineRetour;
	}

}
