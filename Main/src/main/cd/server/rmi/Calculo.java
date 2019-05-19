/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.cd.server.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import main.cd.common.CalculusDerivative;
import main.cd.common.CalculusIntegral;
import main.cd.server.common.Calculus;
import org.mariuszgromada.math.mxparser.Expression;

/**
 *
 * @author gabriel
 */
public class Calculo extends UnicastRemoteObject implements Calculus  {
    
    
    public Calculo() throws RemoteException {
        super();
    }
    
    
    @Override
    public double solveIntegral(CalculusIntegral msg){
        String expfinal = "int(";
        expfinal+=msg.getFunction()+",x,"+msg.getLowerbound()+","+msg.getUpperBound()+")";
        Expression e = new Expression(expfinal);
        double resul =  e.calculate();
        System.out.println("Calculo da integral = " + resul);
        return resul;
    }
    
    @Override
    public double solveDerivative(CalculusDerivative msg){
        String expfinal = "der(";
        expfinal+=msg.getFunction()+",x,"+msg.getX()+")";
        Expression e = new Expression(expfinal);
        double resul =  e.calculate();
        System.out.println("Calculo da Derivada = " + resul);
        return resul;
    }
}
