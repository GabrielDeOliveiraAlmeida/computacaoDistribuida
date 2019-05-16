/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.cd.server.tcp.tcp;

import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author gabriel
 */
public class Server extends Thread{

    public static void main(String[] args)  {
        Server server = new Server();
        server.start();
    }
    
    @Override
    public void run() {
        try {
            System.out.println("Inicializando Servidor");
            ServerSocket welcomeSocket = new ServerSocket(12345);
            while (true) {
                Socket socket = welcomeSocket.accept();
                ServerTCPClient client = new ServerTCPClient(socket);
                System.out.println("Conexão com servidor estabelecida");
                client.start();
            }
        } catch (Exception e) {
            System.out.println("Falha ao inicializar Servidor");
        }
    }
}
