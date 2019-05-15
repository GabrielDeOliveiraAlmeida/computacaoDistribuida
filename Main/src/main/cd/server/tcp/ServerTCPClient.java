/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.cd.server.tcp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import main.cd.common.LinearSystems;

/**
 *
 * @author gabriel
 */
public class ServerTCPClient extends Thread {

    Socket socket;
    ObjectOutputStream outTo;
    ObjectInputStream inFrom;

    public ServerTCPClient(Socket socket) {
        try {
            //RMI
            //Application appref = (Application) Naming.lookup( "rmi://localhost:1099/ApplicationService" );
            //Sockets TCP
            this.socket = socket;
            outTo = new ObjectOutputStream(this.socket.getOutputStream());
            inFrom = new ObjectInputStream(this.socket.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMessege(String message) {
        try {
            System.out.println("Mensagem para o Cliente: " + message);
            outTo.flush();
            outTo.writeBytes(message + '\n');
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object readMessege() {
        try {
            Object message = inFrom.readObject();
            return message;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void closeConnection() {
        try {
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                //Tratar as requisições do cliente.
                Object message = inFrom.readObject();
                System.out.println("Objeto = " +  message);
                if (message instanceof LinearSystems) {
                    System.out.println("Requisição para resolução de sistema linear");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
