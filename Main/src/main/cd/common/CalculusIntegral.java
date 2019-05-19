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
public class CalculusIntegral implements Serializable {
    public String function;
    public String upperBound;
    public String lowerbound;
    public double result;

    public CalculusIntegral(String function, String upperBound, String lowerbound) {
        this.function = function;
        this.upperBound = upperBound;
        this.lowerbound = lowerbound;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public String getFunction() {
        return function;
    }

    public String getUpperBound() {
        return upperBound;
    }

    public String getLowerbound() {
        return lowerbound;
    }

    public double getResult() {
        return result;
    }
    
    
    
}
