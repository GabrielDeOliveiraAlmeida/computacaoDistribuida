/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.cd.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.Socket;
import main.cd.common.CalculusDerivative;
import main.cd.common.CalculusIntegral;
import main.cd.common.LinearSystems;
import main.cd.common.MonteCarlo;
import ui.client.ClientUIController;

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
            System.out.println("Send a message to Server: " + message);
            outTo.flush();
            outTo.writeObject(message);

        } catch (IOException e) {
            //JOptionPane.showMessageDialog(, "Não foi possivel estabelecer conexão com o servidor.");
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
                return "STOPIMEDIATAMENTE";
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

    /*
        Enviar mensagem ao Servidor via TCP
    */
    public static void solveIntegral(String function, String top, String bot) {
        client.sendMessage((Object) new CalculusIntegral(function, top, bot));
    }

    public static void solveDerivative(String function, String x) {
        client.sendMessage((Object) new CalculusDerivative(function, x));
    }
    
    public static void pi(long n, int row){
        client.sendMessage((Object) new MonteCarlo(n, row));
    }

    /*
        Thread
    */
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
                
                /*
                Tratar mensagens do servidor.
                */
                if (reply == null) {
                    continue;
                }
                
                if(reply.equals("STOPIMEDIATAMENTE")) return;
                
                if(reply instanceof String){
                    ClientUIController.showError();
                }
                
                if (reply instanceof LinearSystems) {
                    //printVetor(((LinearSystems) reply).getVetor());
                    printVetor( ((LinearSystems) reply).getResult());
                    ClientUIController.showResposta(((LinearSystems) reply));
                }
                else if (reply instanceof CalculusIntegral) {
                    ClientUIController.showIntegral(((CalculusIntegral) reply).getResult());
                }
                else if (reply instanceof CalculusDerivative) {
                    ClientUIController.showDerivada(((CalculusDerivative) reply).getResult());
                }else if(reply instanceof MonteCarlo){
                    ClientUIController.piNumber(((MonteCarlo) reply).getResult(),((MonteCarlo) reply).getRow());
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
