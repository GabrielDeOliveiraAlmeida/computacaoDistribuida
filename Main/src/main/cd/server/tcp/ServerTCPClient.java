/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.cd.server.tcp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import main.cd.server.common.Application;

/**
 *
 * @author gabriel
 */
public class ServerTCPClient extends Thread {

    Socket socket;
    DataOutputStream outTo;
    BufferedReader inFrom;

    public ServerTCPClient(Socket socket) {
        try {
            //RMI
            //Application appref = (Application) Naming.lookup( "rmi://localhost:1099/ApplicationService" );
            //Sockets TCP
            this.socket = socket;
            outTo = new DataOutputStream(socket.getOutputStream());
            inFrom = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMessege(String message) {
        try {
            System.out.println("Type a message to Server: " + message);
            outTo.flush();
            outTo.writeBytes(message + '\n');
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readMessege() {
        try {
            String message = inFrom.readLine();
            System.out.println("Messege received from Server: " + message);

        } catch (Exception e) {
            e.printStackTrace();
        }
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
                
                

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
