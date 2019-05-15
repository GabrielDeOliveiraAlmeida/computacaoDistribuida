/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.cd.server.tcp;

import main.cd.server.tcp.ServerTCP;

/**
 *
 * @author gabriel
 */
public class Server {
    
   public static void main(String[] args){
        ServerTCP server = new ServerTCP();
        server.init();
    }
}
