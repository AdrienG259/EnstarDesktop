package client;

import java.io.*;
import java.net.*;

public class ClientTCP {

	private int numeroPort;

	private String nomServeur;

	private Socket socketServeur;

	private PrintStream socOut;

	private BufferedReader socIn;

	/**
	 * Un client se connecte a un serveur identifie par un nom (unNomServeur), sur un port unNumero
	 */
	public ClientTCP(String unNomServeur, int unNumero) {
		numeroPort = unNumero;
		nomServeur = unNomServeur;
	}

	public boolean connecterAuServeur() {
		boolean ok = false;
		try {
			System.out.println("Tentative : " + nomServeur + " -- " + numeroPort);
			socketServeur = new Socket(nomServeur, numeroPort);
			socOut = new PrintStream(socketServeur.getOutputStream());
			socIn = new BufferedReader(
					new InputStreamReader(socketServeur.getInputStream()));
			ok = true;
		} catch (UnknownHostException e) {
			System.err.println("Serveur inconnu : " + e);

		} catch (ConnectException e) {
			System.err.println("Exception lors de la connexion:" + e);
			e.printStackTrace();

		} catch (IOException e) {
			System.err.println("Exception lors de l'echange de donnees:" + e);
		}
		System.out.println("Connexion faite");
		return ok;
	}

	public void deconnecterDuServeur() {
		try {
			System.out.println("[ClientTCP] CLIENT : " + socketServeur);
			socOut.close();
			socIn.close();
			socketServeur.close();
		} catch (Exception e) {
			System.err.println("Exception lors de la deconnexion :  " + e);
		}
	}

	public String sendSerializableObject(Serializable serializableObject) {
		String msgServeur = null;
		try {
			System.out.println("Requete client : " + serializableObject.toString());
			ObjectOutputStream oos = new ObjectOutputStream(socketServeur.getOutputStream());
			oos.writeObject(serializableObject);
			oos.flush();
			oos.close();
		} catch (IOException ioException) {
			System.err.println("Envoi client vers serveur impossible :\n" + ioException);
			ioException.printStackTrace();
		}
		try {
			msgServeur = socIn.readLine();
			System.out.println("Reponse serveur : " + msgServeur);
		} catch (IOException ioException) {
			System.err.println("Reception retour serveur vers client impossible :\n" + ioException);
			ioException.printStackTrace();
		}
		return msgServeur;
	}

	public String transmettreChaine(String uneChaine) {
		String msgServeur = null;
		try {
			System.out.println("Requete client : " + uneChaine);
			socOut.println(uneChaine);
			socOut.flush();
			msgServeur = socIn.readLine();
			System.out.println("Reponse serveur : " + msgServeur);

		} catch (UnknownHostException e) {
			System.err.println("Serveur inconnu : " + e);
		} catch (IOException e) {
			System.err.println("Exception entree/sortie:  " + e);
			e.printStackTrace();
		}
		return msgServeur;
	}

	/* A utiliser pour ne pas deleguer la connexion aux interfaces GUI */
	public String transmettreChaineConnexionPonctuelle(String uneChaine) {
		String msgServeur = null;
		String chaineRetour = "";
		System.out.println("\nClient connexionTransmettreChaine " + uneChaine);
		if (connecterAuServeur()) {
			try {
				socOut.println(uneChaine);
				socOut.flush();
				msgServeur = socIn.readLine();
				System.out.println(msgServeur);
				while (msgServeur != null && msgServeur.length() > 0) {
					chaineRetour += msgServeur + "\n";
					msgServeur = socIn.readLine();
				}
				System.out.println("Client msgServeur " + chaineRetour);
				deconnecterDuServeur();
			} catch (Exception e) {
				System.err.println("Exception lors de la connexion client:  " + e);
			}
		} else {
			System.err.println("Connexion echouee");
		}
		return chaineRetour;
	}

	public Serializable receiveSerializable(String message){
		Serializable receivedObject = null;
		System.out.println("\nClient connexionTransmettreChaine " + message);
		if (connecterAuServeur()) {
			try {
				socOut.println(message);
				socOut.flush();

				ObjectInputStream ois=new ObjectInputStream(socketServeur.getInputStream());
				receivedObject =(Serializable)ois.readObject();
				ois.close();

				System.out.println("Client object serializable\n");
				deconnecterDuServeur();
			} catch (Exception e) {
				System.err.println("Exception lors de la connexion client:  " + e);
			}
		} else {
			System.err.println("Connexion echouee");
		}
		return receivedObject;
	}


	public void creerConversation() {
		String msgServer = null;
		try {
			System.out.println("Demande de création de conversation");
			socOut.println("CreerConv");
			socOut.flush();
			msgServer = socIn.readLine();
			System.out.println("Reponse serveur : " + msgServer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
