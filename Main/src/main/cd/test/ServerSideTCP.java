package main.cd.test;

import main.cd.test.TCPThread;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerSideTCP extends Thread{

    
    public static String lastMessage = "";


    public static ServerSideTCP serverSide;
    public static List<TCPThread> TCPThreadList = new ArrayList<TCPThread>();

    public static void initialize(){
        serverSide = new ServerSideTCP();
        serverSide.start();
        System.out.println("Iniciando SERVER_SIDE");
    }

    @Override
    public void run() {
        try {
            // Instancia o ServerSocket ouvindo a porta 12345
            ServerSocket servidor = new ServerSocket(12345);
            System.out.println("----------------------------------\n\nServidor ouvindo a porta " + servidor.getLocalPort());

            while (true){
                Socket clientConnecting = servidor.accept();
                TCPThread TCPThread = new TCPThread(clientConnecting);

                if (TCPThread.isValid()){
                    TCPThread.start();
                    TCPThreadList.add(TCPThread);
                }
            }
        }
        catch(Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
