/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.cd.server.rmi;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import main.cd.server.common.Application;
import main.cd.server.common.Calculus;

/**
 *
 * @author gabriel
 */
public class ServerRMI extends Thread{

    public static void main(String[] args) {
        ServerRMI server = new ServerRMI();
        server.init();
    }

    public ServerRMI() {
    }

    public void init() {
        try {
            //RMI
            System.out.println("Executando servidor RMI");
            LocateRegistry.createRegistry(1099);
            LocateRegistry.createRegistry(1100);

            Application app = new SolveApplication();
            Naming.rebind("rmi://localhost:1099/ApplicationService", app);
            
            Calculus app2 = new Calculo();
            Naming.rebind("rmi://localhost:1100/CalculusService", app2);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
