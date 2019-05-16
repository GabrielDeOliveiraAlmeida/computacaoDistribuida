/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.cd.client;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import main.cd.common.LinearSystems;

/**
 *
 * @author gabriel
 */
public class ClientTCP extends Thread {

    private static ClientTCP client;

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
            return null;
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

@Override
        public void run() {
        try {
            Object reply;
            
            Socket clientSocket = new Socket("localhost", 12345);
            System.out.println("Cliente socket: " + clientSocket);
            outTo = new ObjectOutputStream(clientSocket.getOutputStream());
            inFrom = new ObjectInputStream(clientSocket.getInputStream());

            System.out.println("Conectando ao servidor [" + clientSocket.getInetAddress().getHostAddress() + "]");

            double[][] matrix = new double[3][3];
            double[] vetor = new double[3];

            vetor[0] = 6;
            vetor[1] = 9;
            vetor[2] = 11;
            matrix[0][0] = 1;
            matrix[0][1] = 1;
            matrix[0][2] = 1;
            matrix[1][0] = 1;
            matrix[1][1] = 2;
            matrix[1][2] = 2;
            matrix[2][0] = 2;
            matrix[2][1] = 1;
            matrix[2][2] = 3;
            
//            for (int i = 0; i < 3; i++) {;
//                vetor[i] = Math.random();
//                for (int j = 0; j < 3; j++) {
//                    matrix[i][j] = Math.random();
//                }
//            }
            LinearSystems msg = new LinearSystems(matrix, vetor);
            sendMessage(msg);
            while(true){
                reply = readMessage();
                if(reply == null) continue;
                
                if(reply instanceof LinearSystems){
                    printVetor((double[]) reply);
                }
                
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
