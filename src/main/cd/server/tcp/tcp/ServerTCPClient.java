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
import main.cd.common.MonteCarlo;
import ui.server.serverTCP.ServerTCPController;

/**
 *
 * @author gabriel
 */
public class ServerTCPClient extends Thread {

    public Socket socket;
    public ObjectOutputStream outTo;
    public ObjectInputStream inFrom;
    public boolean ok;
    RMIClient rmi;

    public ServerTCPClient(Socket socket) {
        try {
            //TCPSocket
            this.socket = socket;
            this.outTo = new ObjectOutputStream(socket.getOutputStream());;
            this.inFrom = new ObjectInputStream(socket.getInputStream());
            ok = true;
            //RMI
            rmi = new RMIClient();

        } catch (IOException e) {
            ok = false;
            print("Erro conexão socket "+ e.getMessage());
        }
    }

    public void print(String msg){
        //System.out.println(msg);
        ServerTCPController.print(msg);
    }
    
    public boolean getOk(){
        return ok;
    }
    /*
    Enviar mensagem
    */
    public void sendMessage(Object message) {
        try {
            print("Mensagem para o Cliente: " + message.getClass());
            outTo.flush();
            outTo.writeObject(message);
        } catch (IOException e) {
            print("Send Menssagem Error: " + e.getMessage());
        }
    }

    /*
    Ler mensagem
    */
    public Object readMessage() {
        try {
            Object message = inFrom.readObject();
            return message;
        } catch (Exception e) {
            print("Read Menssagem Error: " + e.getMessage());
             if(e.getMessage().contains("Connection reset") || e.toString().contains("closed") ){
                return "STOPIMEDIATAMENTE";
            }
        }
        return null;
    }

    /*
    Fechar conexão
    */
    public void closeConnection() {
        try {
            socket.close();
        } catch (Exception e) {
            print(e.getMessage());
            e.printStackTrace();
        }
    }

    /*
    Thread para requisições dos clientes
    */
    @Override
    public void run() {
        Object resultado = null;
        try {
            while (true) {
                //Tratar as requisições do cliente.
                Object message = readMessage();
                
                /*
                Caso haja desconexão, fechar socket.
                */
                if(message.equals("STOPIMEDIATAMENTE")){
                    closeConnection();
                    return;
                }
                
                if(message == null){
                    //sendMessage((Object) "OPA");
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
                if(message instanceof MonteCarlo){
                    resultado = rmi.solveMonteCarlo((MonteCarlo) message);
                }
                if(resultado != null){
                    sendMessage(resultado);
                }
            }
        } catch (Exception e) {
            print(e.getMessage());
            e.printStackTrace();
        }
    }

}
