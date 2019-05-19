/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.cd.server.tcp.tcp;

import main.cd.server.tcp.linkrmi.RMIClient;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import main.cd.common.CalculusDerivative;
import main.cd.common.CalculusIntegral;
import main.cd.common.LinearSystems;

/**
 *
 * @author gabriel
 */
public class ServerTCPClient extends Thread {

    public Socket socket;
    public ObjectOutputStream outTo;
    public ObjectInputStream inFrom;
    RMIClient rmi;

    public ServerTCPClient(Socket socket) {
        try {
            //TCPSocket
            this.socket = socket;
            this.outTo = new ObjectOutputStream(socket.getOutputStream());;
            this.inFrom = new ObjectInputStream(socket.getInputStream());
            //RMI
            rmi = new RMIClient();
            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(Object message) {
        try {
            System.out.println("Mensagem para o Cliente: " + message);
            outTo.flush();
            outTo.writeObject(message);
        } catch (IOException e) {
            System.out.println("Send Menssagem Error: " + e.getMessage());
        }
    }

    public Object readMessage() {
        try {
            Object message = inFrom.readObject();
            return message;
        } catch (Exception e) {
            System.out.println("Read Menssagem Error: " + e.getMessage());
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
        Object resultado = null;
        try {
            while (true) {
                //Tratar as requisições do cliente.
                Object message = readMessage();
                System.out.println("Objeto = " + message);
                if(message == null){
                    continue;
                }
                if (message instanceof LinearSystems) {
                    resultado = rmi.linearSystems((LinearSystems) message);
                }
                
                if(message instanceof CalculusIntegral){
                        resultado = rmi.solveIntegral((CalculusIntegral) message);
                }
                if(message instanceof CalculusDerivative){
                        resultado = rmi.solveDerivative((CalculusDerivative) message);
                }
                
                if(resultado != null){
                    sendMessage(resultado);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
