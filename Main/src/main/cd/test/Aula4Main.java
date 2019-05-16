package main.cd.test;


import main.cd.test.ClientSideTCP;
import main.cd.test.ServerSideTCP;


import java.util.Scanner;
import main.cd.client.ClientTCP;

public class Aula4Main {
    public static void main(String[] args) {
        initialize();
    }

    public static void initialize() {

        System.out.println("Iniciando protocolo TCP\n\n\n");



        Scanner input = new Scanner(System.in);

        int method;
        while (true){
            System.out.println("Rodar o programa como?");
            System.out.println("[1] CleintTCP1");
            System.out.println("[2] ClienteUDP");
            System.out.println("[5] Servidor\n\n   --> ");

            method = input.nextInt();

            switch (method){
                case 5:
                    ServerSideTCP.initialize();
                    return;
                case 2:
                    return;
                case 1:
                    ClientSideTCP.initialize();
                    return;
            }
        }
    }



}
