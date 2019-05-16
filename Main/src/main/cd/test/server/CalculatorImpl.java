package main.cd.test.server;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import main.cd.test.common.Calculator;

public class CalculatorImpl extends UnicastRemoteObject implements Calculator {

	double termo1;
	double termo2;
	String operator;

	public static final String POSSIBLE_OPERATOR = "+, -, *, /";

	public CalculatorImpl() throws RemoteException {
		super();
	}

	@Override
	public void setTermo1(double termo) throws RemoteException {
		this.termo1 = termo;
	}

	@Override
	public void setTermo2(double termo) throws RemoteException {
		this.termo2 = termo;
	}

	@Override
	public void setOperator(String operator) throws RemoteException {
		this.operator = operator;
	}

	@Override
	public String calcula() throws RemoteException {

		String result = null;
		switch (operator){
			case "+":
				result = String.valueOf(termo1 + termo2);
			case "-":
				result =  String.valueOf(termo1 - termo2);
			case "*":
				result =  String.valueOf(termo1 * termo2);
			case "/":
				result =  String.valueOf(termo1 / termo2);
		}

		if (result != null){
			System.out.println("The resulta calcuted for " + termo1 + " " + operator + " " + termo2 + " is : " + result);
			return result;
		}

		throw new NumberFormatException("Operator is not recognized [" + POSSIBLE_OPERATOR + "]");
	}
}
