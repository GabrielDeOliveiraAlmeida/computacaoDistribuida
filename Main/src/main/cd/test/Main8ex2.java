package main.cd.test;




import java.util.Scanner;
import main.cd.test.client.MensageiroClient;
import main.cd.test.server.MensageiroServer;

public class Main8ex2 {

    public static void main(String[] args) {

        int method;
        Scanner input = new Scanner(System.in);
        //while (true){

            while (true){
                System.out.println("Rodar qual programa?");
                System.out.println("[1] Cliente");
                System.out.print("[2] Servidor\n    --> ");
                method = input.nextInt();

                switch (method){
                    case 1:
                        MensageiroClient.initialize();
                        return;
                    case 2:
                        MensageiroServer.initialize();
                        return;
                    default:
                        System.out.println("Dados inseridos inválidos!");
 
                }
 
            }
        //}

    }


}
