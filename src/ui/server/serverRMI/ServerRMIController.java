/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.server.serverRMI;

/**
 *
 * @author gabriel
 */
public class ServerRMIController {
    public static ServerRMIController controller = new ServerRMIController();
    public static LogRMI ui;
    
    
    public static void init(){
        ui = new LogRMI();
        ui.setVisible(true);
    }
    
    public static void print(String msg){
        ui.printLog(msg);
    }
    
    public static void close(){
        ui.setVisible(false);
    }
}
    