/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.cd.client;

import ui.client.SystemsLinearUIController;


/**
 *
 * @author gabriel
 */
public class Client {
    
    public static void main(String[] args) {
        Client.init();
    }
    
    public static void init(){
        ClientTCP.init();
        SystemsLinearUIController.showUI();
    }
    
    

}
