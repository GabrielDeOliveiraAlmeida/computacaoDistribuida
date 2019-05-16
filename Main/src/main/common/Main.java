/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.common;

import java.util.Scanner;
import main.cd.client.Client;
import main.cd.client.ClientTCP;
import main.cd.server.rmi.ServerRMI;
import main.cd.server.tcp.tcp.Server;

/**
 *
 * @author gabriel
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int method;
        Scanner input = new Scanner(System.in);
        
        while (true) {
            System.out.println("[1] Cliente");
            System.out.print("[2] Servidor\n");
            System.out.print("[3] Servidor RMI\n");
            System.out.print("[5] TUDO\n");
            method = input.nextInt();

            switch (method) {
                case 1:
                    ClientTCP.init();
                    return;
                case 2:
                    Server.main(args);
                    return;
                case 3:
                    ServerRMI.main(args);
                    return;
                case 5:
                    ServerRMI.main(args);
                    Server.main(args);
                    ClientTCP.init();
                default:

            }
        }
    }

}
