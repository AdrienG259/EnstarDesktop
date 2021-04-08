package clienserver.tests.unitaires;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import server.Gestion.Messagerie;
import server.ServeurTCP;

public class TestServerTCP {
	
	static ServeurTCP aServer;
	
	@BeforeClass
	public static void beforeClass(){
		System.out.println("before class");
		aServer = new ServeurTCP(new Messagerie(), new ProtocoleServeurPrivee(),5555 );
	}
			
	@AfterClass
	public static void afterClass(){
		System.out.println("after class");
	}
	
	@Before
	public void before() {
		System.out.println("before");
	}

	@After
	public void after() {
		System.out.println("after");
	}

	@Test
	public void testRunServerStarted() {
		assertNotNull(aServer);
		aServer.start();
		assertTrue(aServer.isAlive());
	}
	
}
