/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.client;


import java.math.BigDecimal;
import java.math.BigInteger;
import main.cd.client.ClientTCP;
import main.cd.common.LinearSystems;

/**
 *
 * @author gabriel
 */
public class ClientUIController {

    public static ClientUIController controller = new ClientUIController();
    public static ClientUI ui;

    public static void calcular(double[][] matriz, double[] vetor) {
        ClientTCP.calcular(matriz, vetor);
    }

    public static void showResposta(LinearSystems resposta) {
        ui.showResposta(resposta);
    }

    public static void showUI() {
        ui = new ClientUI();
        ui.setVisible(true);
    }

    public static void solveIntegral(String function, String top, String bot) {
        ClientTCP.solveIntegral(function, top, bot);
    }

    public static void solveDerivative(String function, String x) {
        ClientTCP.solveDerivative(function, x);
    }

    public static void showIntegral(double resposta) {
        ui.showIntegral(resposta);
    }

    public static void showDerivada(double resposta) {
        ui.showDerivada(resposta);
    }
    
    public static void showError(){
        ui.showError();
    }
    
    public static void piNumber(String pi, int row){
        ui.piNumber(pi, row);
    }
    
    public static void pi(long n, int row){
        ClientTCP.pi(n, row);
    }
}
