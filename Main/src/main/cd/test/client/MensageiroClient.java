package main.cd.test.client;


import java.rmi.Naming;
import java.util.Scanner;
import main.cd.test.common.Calculator;

public class MensageiroClient {

	public static void initialize() {
		try {
			Calculator mref = (Calculator) Naming.lookup( "rmi://localhost:1099/CalculatorService" );

			System.out.println("Greetings my friend!");

			while (true){
				Scanner input = new Scanner(System.in);

				System.out.print("Insira o termo 1: \n    --> ");
				double termo1 = input.nextDouble();

				System.out.print("Insira o termo 2: \n    --> ");
				double termo2 = input.nextDouble();

				input = new Scanner(System.in);

				System.out.print("Insira o operador");
				String operator = input.nextLine();

				mref.setTermo1(termo1);
				mref.setTermo2(termo2);
				mref.setOperator(operator);

				System.out.println(mref.calcula());
			}



		}catch ( Exception e){
			e.printStackTrace();
		}
	}
}
