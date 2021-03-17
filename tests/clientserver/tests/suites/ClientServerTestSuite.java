package clientserver.tests.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import clienserver.tests.unitaires.TestClientTCP;
import clienserver.tests.unitaires.TestServerTCP;
import clientserver.tests.integration.TestCommunication;
import clientserver.tests.integration.TestConnection;

@RunWith(Suite.class)

@Suite.SuiteClasses({
   TestClientTCP.class,
   TestServerTCP.class,
   TestConnection.class,
   TestCommunication.class
})

public class ClientServerTestSuite {   
}  	