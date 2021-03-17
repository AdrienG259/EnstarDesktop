package clienserver.tests.unitaires;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import client.ClientTCP;
import org.junit.Test;

public class TestClientTCP {

	@Test
	public void testClient() {
		ClientTCP myClient = new ClientTCP("localhost", 6666);
		assertNotNull(myClient);
	}
	
}
