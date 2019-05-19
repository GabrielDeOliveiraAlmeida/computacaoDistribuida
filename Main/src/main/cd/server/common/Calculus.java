/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.cd.server.common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import main.cd.common.CalculusDerivative;
import main.cd.common.CalculusIntegral;

/**
 *
 * @author gabriel
 */
public interface Calculus extends Remote{
    
    public double solveIntegral(CalculusIntegral exp) throws RemoteException;
    public double solveDerivative(CalculusDerivative exp) throws RemoteException;
             
}
