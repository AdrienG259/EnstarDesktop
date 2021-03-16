package server;

import common.Conversation;
import common.ConversationSimple;
import common.User;

import java.util.ArrayList;

public class MainServer {

	public static void main( String[] args) {

		Messagerie maMesssagerie = new Messagerie(); // je pense qu'il faudra lui rajouter des arguments
		System.out.println("ouverture de la messagerie");
		maMesssagerie.ouvrirMessagerie();

		//ServeurTCP aServer = new ServeurTCP((IContext) new ConversationSimple("toto", new ArrayList<User>()),new ProtocoleServeurPrivee(),6666 );
		//aServer.start();
		
		
	}
}
