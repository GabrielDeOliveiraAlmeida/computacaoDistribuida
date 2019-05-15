/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.cd.server.tcp;

import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author gabriel
 */
public class ServerTCP {

    public ServerTCP(){

    }

    public void init() {
        System.out.println("Inicializando servidor");
        run();
    }
    
    public void run() { //It isnt Thread xD
        try {
            ServerSocket welcomeSocket = new ServerSocket(10000);
            while (true) {
                Socket socket = welcomeSocket.accept();
                ServerTCPClient client = new ServerTCPClient(socket);
                System.out.println("Conexão com servidor estabelecida");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
