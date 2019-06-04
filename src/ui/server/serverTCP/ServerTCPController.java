/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.server.serverTCP;



/**
 *
 * @author gabriel
 */
public class ServerTCPController {
    public static ServerTCPController controller = new ServerTCPController();
    public static LogTCP ui;
    
    
    public static void init(){
        ui = new LogTCP();
        ui.setVisible(true);
    }
    
    public static void print(String msg){
        ui.printLog(msg);
    }
    
}
