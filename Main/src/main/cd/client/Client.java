/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.cd.client;

/**
 *
 * @author gabriel
 */
public class Client {

    public static void main(String[] args) {
        ClientTCP client = new ClientTCP();
        client.init();
    }

}
