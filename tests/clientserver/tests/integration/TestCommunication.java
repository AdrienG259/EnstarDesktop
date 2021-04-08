package clientserver.tests.integration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import server.Gestion.Messagerie;
import server.ProtocoleConversation;
import server.ServeurTCP;
import client.ClientTCP;

import static org.junit.Assert.*;


@RunWith(Parameterized.class)
public class TestCommunication {

	static ServeurTCP aServer;
	static ClientTCP client1;

	private ArrayList<String> messages;
	private int result;

	// Chaque parametre est un argument, A� chaque test une nouvelle valeur est donnee.
	public TestCommunication(ArrayList<String> messages, int result) {
	    this.messages = messages;
	    this.result = result;
	}

	@BeforeClass
	public static void beforeClass(){
		System.out.println("before class");
		aServer = new ServeurTCP(new Messagerie(), new ProtocoleConversation(), 5678 );
		client1 = new ClientTCP("localhost", 5678);
		aServer.start();
	}

	@AfterClass
	public static void afterClass(){
		System.out.println("after class");
		assertTrue(aServer.isAlive());
	}

	@Test
	public void testCommunications() {
		System.out.println("Test transmissions");
		try {
			client1.connecterAuServeur();
			for(String message : messages){
				client1.transmettreChaineConnexionPonctuelle(message);
			}
			client1.deconnecterDuServeur();
		} catch (Exception e) {
			fail();
		}

	}

	@Parameterized.Parameters
	public static Collection<Object[]> scenarios() {
		ArrayList<String> scenario1 = new ArrayList<String>();
		scenario1.add("send something");
		scenario1.add("demandeRetrait");
		scenario1.add("demandeDepot");
		scenario1.add("stop");

		ArrayList<String> scenario2 = new ArrayList<String>();
		scenario2.add("send something");
		scenario2.add("demandeDepot");
		scenario2.add("demandeRetrait");
		scenario2.add("stop");

		ArrayList<String> scenario3 = new ArrayList<String>();
		scenario3.add("demandeDepot");
		scenario3.add("demandeDepot");
		scenario3.add("stop");


	      return Arrays.asList(new Object[][] {
	         { scenario1, 5000 },
	         { scenario2, 5000 },
	         { scenario3, 5020 }
	      });
	   }
}
