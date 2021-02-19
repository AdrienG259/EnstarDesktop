package server;

public class Compte {

	int variablePartagee = 1000;
	
	public Compte( int uneValeur ) {
		variablePartagee = uneValeur;
	}
	
	public synchronized int demandeRetrait() {
		variablePartagee -= 10; 
		return variablePartagee;
	}

	public synchronized int demandeDepot() {
		variablePartagee += 10;
		return variablePartagee;
	}
	
	public int getVariablePartagee() {
		return variablePartagee;
	}
}
