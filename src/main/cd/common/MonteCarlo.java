/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.cd.common;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 *
 * @author gabriel
 */
public class MonteCarlo  implements Serializable{
    public String result;
    public long n;
    public int row;

    public MonteCarlo(long n, int row) {
        this.n = n;
        this.row = row;
    }
    
    public void setResult(String result){
        this.result = result;
    }
    
    public String getResult(){
        return this.result;
    }
    
    public int getRow(){
        return this.row;
    }
}

