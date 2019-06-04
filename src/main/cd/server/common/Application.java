/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.cd.server.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author gabriel
 */
public interface Application extends Remote{

    <Object> Object executeTask(AppTask<Object> t) throws RemoteException;
    
}
