package client;

import java.util.*;

public class MainClient {

	public static void main(String[] args) {
		String myS = null;
		Scanner aSC = new Scanner(System.in);
		ClientTCP myClient = new ClientTCP("localhost", 6666);

		try {
			if (myClient.connecterAuServeur()) {

				for (int i = 0; i < 5; i++) {
					System.out.println(" Saisir une chaine ");
					myS = aSC.nextLine();

					if ( myClient.transmettreChaineConnexionPonctuelle(myS) == null ) break;

				}

				aSC.close();

				myClient.deconnecterDuServeur();
			} else {
				System.err.println("Connexion echouee");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
