/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.cd.server.tcp.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import ui.server.serverTCP.ServerTCPController;


/**
 *
 * @author gabriel
 */
public class Server extends Thread{
    public static Server server;
    
    public static void main(String[] args)  {
        Server.init();
    }
    
    //Inicialização da UI e servidor
    public static void init(){
       server = new Server();
       ServerTCPController.init();
       server.start();
    }
    
    public void print(String msg){
        //System.out.println(msg);
        ServerTCPController.print(msg);
    }
    
    
    @Override
    public void run() {
        try {
            print("Executando Servidor TCP");        
            ServerSocket welcomeSocket = new ServerSocket(12345);
            while (true) {
                //Novo conexão Servidor - Cliente
                Socket socket = welcomeSocket.accept();
                //Tratar requisições do Cliente
                ServerTCPClient client = new ServerTCPClient(socket);
                //Se houver erro no cliente
                if(!client.getOk()) return;
                //Se não, conexão é estabelecida
                print("Conexao com servidor estabelecida");
                client.start();
            }
        } catch (IOException e) {
            print("Falha ao inicializar Servidor\nMensagem erro: "+ e.getMessage());
        }
    }
}
