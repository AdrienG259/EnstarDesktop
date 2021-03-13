package server;

import common.Conversation;
import common.ConversationSimple;
import common.User;

import java.util.ArrayList;

public class MainServer {

	public static void main( String[] args) {
		ServeurTCP aServer = new ServeurTCP((IContext) new ConversationSimple("toto", new ArrayList<User>()),new ProtocoleServeurPrivee(),6666 );
		aServer.start();
		
		
	}
}
