/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.cd.common;

import java.io.Serializable;

/**
 *
 * @author gabriel
 */
public class CalculusDerivative implements Serializable{
    String function;
    String x;
    double result;

    public CalculusDerivative(String function, String x) {
        this.function = function;
        this.x = x;
    }

    public String getFunction() {
        return function;
    }

    public String getX() {
        return x;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

  
    
    
    
}
