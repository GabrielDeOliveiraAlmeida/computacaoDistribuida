/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.cd.server.rmi;

import java.rmi.RemoteException;
import main.cd.server.common.Application;

/**
 *
 * @author gabriel
 */
public class SolveApplication implements Application{
    double[][] matrix;
    double[] vetor;
    
    public SolveApplication() throws RemoteException{
        super();
    }
    
    @Override
    public void setMatrix(double[][] coefficient) throws RemoteException {
        
    }

    @Override
    public void setIndTerms(double[] independente) throws RemoteException {
        
    }

    @Override
    public void calculate(double[][] coeficientes, double[] independentes) throws RemoteException {
        
    }
    
}
