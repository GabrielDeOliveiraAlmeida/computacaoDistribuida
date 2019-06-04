/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.cd.server.tcp.linkrmi;

import main.cd.server.tcp.app.ComputeSistema;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import main.cd.common.CalculusDerivative;
import main.cd.common.CalculusIntegral;
import main.cd.common.LinearSystems;
import main.cd.server.common.Application;
import main.cd.server.tcp.app.ComputeDerivada;
import main.cd.server.tcp.app.ComputeIntegral;
/**
 *
 * @author gabriel
 */
public class RMIClient {

    Application app;
    Registry registry;
    public RMIClient() {
        try {
            registry = LocateRegistry.getRegistry();
            app = (Application) Naming.lookup("ApplicationService");
        } catch (Exception e) {
            System.out.print("RMI CLIENT error: " + e.getMessage());
        }
    }

    public Object linearSystems(LinearSystems msg) {
        try {
            System.out.println("Requisicao para resolucaoo de sistema linear");
            ComputeSistema resul = new ComputeSistema(msg.getMatrix(), msg.getVetor());
            msg.setResult((double[])(app.executeTask(resul)));
            return msg;
            
        } catch (Exception e) {
            System.out.println("Requisicao error: " + e.getMessage());
            //e.printStackTrace();
            return null;
        }
    }

    public Object solveIntegral(CalculusIntegral msg) {
        try {
            System.out.println("Requisicao para resolucao de Integral");
            ComputeIntegral resul = new ComputeIntegral(msg.getFunction(), msg.getLowerbound(), msg.getUpperBound());
            msg.setResult((double)(app.executeTask(resul)));
            return msg;
        } catch (Exception e) {
            System.out.print("Requisicao error: " + e.getMessage());
            //e.printStackTrace();
            return null;
        }
    }

    public Object solveDerivative(CalculusDerivative msg) {
        try {
            System.out.println("Requisicao para resolucao da Derivada");
            ComputeDerivada resul = new ComputeDerivada(msg.getFunction(), msg.getX());
            msg.setResult((double) app.executeTask(resul));
            return msg;
                    
        } catch (Exception e) {
            System.out.println("Requisicaoo error: " + e.getMessage());
            //e.printStackTrace();
            return null;
        }
    }

    
}
