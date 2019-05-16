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
public class LinearSystems implements Serializable {
    private double[][] matrix;
    private double[] vetor;
    private double[] result;
    
     private static final long serialVersionUID = -5399605122490343339L;
     
    public LinearSystems(double[][] matrix, double[] vetor){
        this.matrix = matrix;
        this.vetor = vetor;
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public double[] getVetor() {
        return vetor;
    }
    
    public void setResult(double[] result){
        this.result = result;
    }
    
    
    
}
