/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.cd.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author gabriel
 */
public class ClientTCP extends Thread {

    DataOutputStream outTo;
    BufferedReader inFrom;

    public ClientTCP() {

    }

    public void init() {
        System.out.println("Inicializando cliente");
        start();
    }

    public void sendMessage(String message) {
        try {
            System.out.println("Type a message to Server: " + message);
            outTo.flush();
            outTo.writeBytes(message + '\n');
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readMessage() {
        try{
            String message = inFrom.readLine();
            System.out.println("Messege received from Server: "+ message);
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            Socket clientSocket = new Socket("localhost", 10000);
            System.out.println("Cliente socket: " + clientSocket);
            outTo = new DataOutputStream(clientSocket.getOutputStream());
            inFrom = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
