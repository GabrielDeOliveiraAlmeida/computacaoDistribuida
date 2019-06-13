/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.cd.server.rmi;

import java.rmi.RemoteException;
import main.cd.server.common.AppTask;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import main.cd.server.common.Application;
import ui.server.serverRMI.ServerRMIController;

/**
 *
 * @author gabriel
 */
public class ServerRMI implements Application {
    public static ServerRMI server;
    
    public static void main(String[] args) {
        ServerRMI.init();
    }
    /*
        Inicializar UI e servidor
    */
    public static void init(){
        server = new ServerRMI();
        ServerRMIController.init();
        server.execute();
    }

    public void print(String msg){
        ServerRMIController.print(msg);
    }
    
    /*
        Inicializar o solve
    */
    public void execute() {
        try {
            print("Executando servidor RMI");
            Registry registry = LocateRegistry.createRegistry(1099);
          
            //Interface
            Application app = server ;
            Application stub = (Application) UnicastRemoteObject.exportObject(app, 0);
            registry.rebind("ApplicationService", stub);

        } catch (RemoteException e) {
            print("Erro na inicializacao do Servidor RMI: "+ e.getMessage());
        }
    }

    /*
        Execução do método no Servidor RMI.
    */
    @Override
    public <T> T executeTask(AppTask<T> t) {
        return t.calculate();
    }

}
