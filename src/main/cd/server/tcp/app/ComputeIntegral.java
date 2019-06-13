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
 * @param <T> PARAMETROS
 */
public class ComputeIntegral<T> implements AppTask<T>, Serializable {

    Expression e;
    String expfinal;

    public ComputeIntegral(String funcao, String limInf, String limSup) {
        System.out.println(funcao + " para ["+limInf+","+limSup+"]");
        expfinal = "int(" + funcao + ",x," + limInf + "," + limSup + ")";
    }

    public Double solveIntegral() {
        e = new Expression(expfinal);
        System.out.println("Resolver Integral para "+ e.getExpressionString());
        double resul = e.calculate();
        System.out.println("Resultado da Integral = " + resul);
        return resul;
    }

    @Override
    public T calculate() {
        return  (T) solveIntegral();
    }

}
