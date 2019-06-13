/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.cd.server.tcp.app;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import main.cd.server.common.AppTask;

/**
 *
 * @author gabriel
 */
public class ComputePIMonteCarlo<T> implements AppTask<T>,Serializable {
    public long n;
    
    public ComputePIMonteCarlo(long n){
        this.n=n;
    }
    
    public String monteCarlo(){
        System.out.println("Aproximando o numero PI (Algoritmo de Monte Carlo)");
        int acertos=0;
        double x, y;
        long i=n;
        while(i > 0){
            x = Math.random();
            y = Math.random();
            if((x*x + y*y )< 1){
                acertos++;
            }
            i--;
        }
        BigDecimal result = new BigDecimal((4.0*acertos)/n);
        System.out.println("Para " + n + " iteracoes, PI = "+ result.toString());
        return result.toPlainString();
    }

    @Override
    public T calculate() {
        return  (T) monteCarlo();
    }
    
    
}
