/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.cd.server.tcp.linkrmi;

import java.rmi.Naming;
import main.cd.common.LinearSystems;
import main.cd.server.common.Application;

/**
 *
 * @author gabriel
 */
public class RMIClient {

    Application app;

    public RMIClient() {
        try {
            app = (Application) Naming.lookup("rmi://localhost:1099/ApplicationService");
        } catch (Exception e) {
            System.out.print("RMI CLIENT error: " + e.getMessage());
        }
    }

    public Object linearSystems(LinearSystems obj) {
        try {
            System.out.println("Requisição para resolução de sistema linear");
            app.setMatrix(obj.getMatrix());
            app.setIndTerms(obj.getVetor());
            obj.setResult(app.calculate());
            return obj;
            
        } catch (Exception e) {
            System.out.print("Requisição error: " + e.getMessage());
            return null;
        }
    }

}
