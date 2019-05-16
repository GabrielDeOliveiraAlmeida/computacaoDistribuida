package main.cd.test;


import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientSideTCP extends Thread {

    public static ClientSideTCP clientSideTCP;
    public static void initialize(){
        clientSideTCP = new ClientSideTCP();
        clientSideTCP.start();

        System.out.println("Iniciando CLIENT_SIDE");
    }

    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;

    public void sendToServer(String message){
        try {
            System.out.println("Sending to Server [" + message + "]");
            objectOutputStream.flush();
            objectOutputStream.writeObject(message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            // Inicia conexão com o servidor
            Socket serverSocket = new Socket("localhost",12345);
            objectOutputStream = new ObjectOutputStream(serverSocket.getOutputStream());
            objectInputStream = new ObjectInputStream(serverSocket.getInputStream());
            //objectInputStream = new ObjectInputStream(serverSocket.getInputStream());
            System.out.println("Conectando ao servidor [" + serverSocket.getInetAddress().getHostAddress() + "]");

            System.out.println("\n\n");

            Scanner input = new Scanner(System.in);

            while (true){
                String message = input.nextLine();
                if (message.equalsIgnoreCase("exit")){
                    break;
                }
                sendToServer(message);
            }

            objectOutputStream.close();
        }catch(Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
        System.out.println("Forte abraço!");
    }

}
