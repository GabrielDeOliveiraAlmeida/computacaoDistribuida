/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.cd.server.tcp.app;

import java.io.Serializable;
import java.util.Arrays;
import main.cd.server.common.AppTask;

/**
 *
 * @author gabriel
 * @param <T> PARAMETROS
 */
public class ComputeSistema<T> implements AppTask<T>,Serializable {

    double[][] matrix;
    double[] vetor;
    double[] x;

    private static final long serialVersionUID = 227L;

    public ComputeSistema(double[][] matrix, double[] vetor) {
        this.matrix = matrix;
        this.vetor = vetor;
    }
    
    public void setMatrix(double[][] coefficient) {
        System.out.println("Setar matriz de coeficientes: ");
        printMatrix(coefficient);
        this.matrix = coefficient;

    }
    public void setIndTerms(double[] independente)  {
        System.out.println("\nSetar matriz de Termos independentes: ");
        printVetor(independente);
        this.vetor = independente;
    }

    private double[] gaussMethod() {
        System.out.println("\nIniciando metodo de Gauss");
        int n = matrix.length;
        double[] x0 = new double[n];
        x = new double[n];
        Arrays.fill(x0, 0);
        Arrays.fill(x, 0);
        
        double erro = 0.000000001;
        double errox = erro + 1;
        double soma;

        while (errox > erro) {
            for (int i = 0; i < n; i++) {
                soma = 0;
                for (int j = 0; j < n; j++) {
                    if (i != j) {
                        soma = soma + (matrix[i][j] * x[j]);
                    }
                }
                x[i] = (vetor[i] - soma) / matrix[i][i];
            }
            errox = absoluteError(x, x0);
            x0 = x.clone();
        }
        System.out.println("Solucao numerica encontrada");
        printVetor(x);
        return x;
    }

    private double absoluteError(double[] x, double[] x0) {
        double max = Arrays.stream(x).max().getAsDouble();
        double max2 = Arrays.stream(x0).max().getAsDouble();
        return (Math.abs(max - max2) / Math.abs(max2));
    }

    public boolean isConvergent() {
        for (int i = 0; i < matrix.length; i++) {
            double diagonal = Math.abs(matrix[i][i]);
            double soma = 0;
            for (int j = 0;  j< matrix.length; j++) {
                if (i != j) {
                    soma += Math.abs(matrix[i][j]);
                }
            }
            if (soma >= diagonal)   return false;
        }
        return true;
    }

    private void printVetor(double[] x) {
        System.out.println("\n");
        for (int i = 0; i < x.length; i++) {
            System.out.printf(" %f,", x[i]);
        }
        System.out.println("\n");
    }

    private void printMatrix(double[][] matrix) {
        System.out.println("\n");
        for(double[] vet : matrix){
            System.out.printf("\n");
            for(double elem : vet){
                System.out.printf(" %f,", elem);
            }
        }
        System.out.println("\n");
    }

    @Override
    public T calculate(){
        return (T) gaussMethod();
    }
}
