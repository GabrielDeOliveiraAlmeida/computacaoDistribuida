/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.cd.server.tcp.app;

import java.io.Serializable;
import main.cd.server.common.AppTask;
import org.mariuszgromada.math.mxparser.Expression;

/**
 *
 * @author gabriel
 */
public class ComputeDerivada<T> implements AppTask<T>,Serializable{
    
    Expression e;
    String expfinal;

    public ComputeDerivada(String funcao, String xValue) {
        System.out.println( funcao+ " para x = "+xValue);
        expfinal = "der(" + funcao +",x,"+xValue+")";
    }
    
    public Double solveDerivada(){
        e = new Expression(expfinal);
        System.out.println("Resolver Derivada para "+ e.getExpressionString());
        double resul = (e.calculate());
        System.out.println("Resultado = " + resul);
        return resul;
    }

    @Override
    public T calculate() {
        return (T) solveDerivada();
    }
    
    
}
