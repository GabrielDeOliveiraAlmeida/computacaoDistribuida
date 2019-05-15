/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.cd.server.rmi;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import main.cd.server.common.Application;

/**
 *
 * @author gabriel
 */
public class ServerRMI {
    
    public ServerRMI() {
        try {
            //RMI
            LocateRegistry.createRegistry(1099);
            Application app = new SolveApplication();
            Naming.rebind("rmi://localhost:1099/ApplicationService", app);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
