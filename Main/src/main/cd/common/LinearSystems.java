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
    double[][] matrix;
    double[] vetor;
    
    public LinearSystems(double[][] matrix, double[] vetor){
        this.matrix = matrix;
        this.vetor = vetor;
    }
    
    
}
