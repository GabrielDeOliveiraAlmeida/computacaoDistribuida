/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.cd.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import main.cd.common.CalculusDerivative;
import main.cd.common.CalculusIntegral;
import main.cd.common.LinearSystems;
import ui.client.SystemsLinearUIController;

/**
 *
 * @author gabriel
 */
public class ClientTCP extends Thread {

    private static ClientTCP client;
    private static Socket clientSocket;
    public static ClientTCP getClient() {
        return client;
    }

    public static void init() {
        client = new ClientTCP();
        System.out.println("Inicializando cliente");
        client.start();
    }

    ObjectOutputStream outTo;
    ObjectInputStream inFrom;

    public void sendMessage(Object message) {
        try {
            System.out.println("Type a message to Server: " + message);
            outTo.flush();
            outTo.writeObject(message);

        } catch (IOException e) {
            System.out.println("Send Menssagem Error: " + e.getMessage());

        }
    }

    public Object readMessage() {
        try {
            Object message = inFrom.readObject();
            System.out.println("Messege received from Server: " + message);
            return message;
        } catch (Exception e) {
            System.out.println("Read Menssagem Error: " + e.getMessage());
            if(e.getMessage().contains("Connection reset") || e.toString().contains("closed") ){
                stopSocket();
                return null;
            }
        }
        return null;
    }

    private void stopSocket(){
        try{
            clientSocket.close();
        }catch(Exception e){
            System.out.println("Socket couldn't be closed");
        }
    }
    public void closeConnection() {
        try {
            outTo.close();
            inFrom.close();
        } catch (Exception e) {
            System.out.println("Erro ao fechar Socket");
        }
    }

    private void printVetor(double[] x) {
        for (int i = 0; i < x.length; i++) {
            System.out.printf(" %f,", x[i]);
        }
    }

    public static void calcular(double[][] matriz, double[] vetor) {
        client.sendMessage((Object) new LinearSystems(matriz, vetor));
    }

    public static void solveIntegral(String function, String top, String bot) {
        client.sendMessage((Object) new CalculusIntegral(function, top, bot));
    }

    public static void solveDerivative(String function, String x) {
        client.sendMessage((Object) new CalculusDerivative(function, x));
    }

    @Override
    public void run() {
        try {
            Object reply;

            clientSocket = new Socket("localhost", 12345);
            System.out.println("Cliente socket: " + clientSocket);
            outTo = new ObjectOutputStream(clientSocket.getOutputStream());
            inFrom = new ObjectInputStream(clientSocket.getInputStream());

            System.out.println("Conectando ao servidor [" + clientSocket.getInetAddress().getHostAddress() + "]");

            while (true) {
                reply = readMessage();
                if (reply == null) {
                    continue;
                }

                if (reply instanceof LinearSystems) {
                    //printVetor(((LinearSystems) reply).getVetor());
                    SystemsLinearUIController.showResposta(((LinearSystems) reply));
                }
                if (reply instanceof CalculusIntegral) {
                    SystemsLinearUIController.showIntegral(((CalculusIntegral) reply).getResult());
                }
                if (reply instanceof CalculusDerivative) {
                    SystemsLinearUIController.showDerivada(((CalculusDerivative) reply).getResult());
                }

            }

        } catch (Exception e) {
            if(e.getMessage().endsWith("reset")){
                closeConnection();
            }
            e.printStackTrace();
        }
    }
}
