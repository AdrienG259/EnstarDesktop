package server;

import server.Gestion.Messagerie;

public class MainServer {

	public static void main( String[] args) {

		Messagerie maMesssagerie = new Messagerie(); // je pense qu'il faudra lui rajouter des arguments
		System.out.println("Ouverture de la messagerie");
		maMesssagerie.ouvrirMessagerie();

	}
}
