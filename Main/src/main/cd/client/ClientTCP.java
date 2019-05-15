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
import main.cd.common.LinearSystems;
/**
 *
 * @author gabriel
 */
public class ClientTCP extends Thread {

    ObjectOutputStream outTo;
    ObjectInputStream inFrom;

    public ClientTCP() {

    }

    public void init() {
        System.out.println("Inicializando cliente");
        start();
    }

    public void sendMessage(Object message) {
        try {
            System.out.println("Type a message to Server: " + message);
            outTo.flush();
            outTo.writeObject(message);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readMessage() {
        try {
            Object message = inFrom.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            Socket clientSocket = new Socket("localhost", 10000);
            System.out.println("Cliente socket: " + clientSocket);
            outTo = new ObjectOutputStream(clientSocket.getOutputStream());
            inFrom = new ObjectInputStream(clientSocket.getInputStream());
            double[][] matrix = new double[3][3];
            double[] vetor= new double[3];
            for (int i = 0; i < 3; i++) {
                vetor[i] = Math.random();
                for (int j = 0; j < 3; j++) {
                    matrix[i][j] = Math.random();
                }
            }
            
            LinearSystems msg = new LinearSystems(matrix, vetor);
            sendMessage(msg);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
