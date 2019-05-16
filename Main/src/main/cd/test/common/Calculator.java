package main.cd.test.common;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Calculator extends Remote {

	public void setTermo1(double termo) throws RemoteException;
	public void setTermo2(double termo) throws RemoteException;
	public void setOperator(String operator) throws RemoteException;
	public String calcula() throws RemoteException, NumberFormatException;

}