package server;

public class MainServer {

	public static void main( String[] args) {
		TCPServer aServer = new TCPServer( 6666 );
		
		aServer.start();
		
		
	}
}
