package main.cd.test.server;


import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import main.cd.test.common.Calculator;

public class MensageiroServer {


	public static MensageiroServer mensageiroServer;

	public static void initialize(){
		mensageiroServer = new MensageiroServer();
	}

	public MensageiroServer() {
		try {
			LocateRegistry.createRegistry(1099);
			// Da uma lida nesse paranaue aqui meu confederado 
			// --> https://docs.oracle.com/javase/7/docs/technotes/tools/solaris/rmiregistry.html
			
			Calculator ms = new CalculatorImpl();
			Naming.rebind("rmi://localhost:1099/CalculatorService", ms);
		}
		catch( Exception e ) {
			System.out.println( "Trouble: " + e );
		}
	}

	public static void main(String[] args) {
		new MensageiroServer();
	}
}