/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.awt.Label;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import main.cd.client.Client;
import main.cd.server.rmi.ServerRMI;
import main.cd.server.tcp.tcp.Server;

/**
 *
 * @author gabriel
 */
public class MainInterface extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        Button btn2 = new Button();
        Button btn3 = new Button();
        
        btn.setLayoutX(100);
        btn.setLayoutY(50);
        btn2.setLayoutX(100);
        btn2.setLayoutY(100);
        btn3.setLayoutX(100);
        btn3.setLayoutY(150);
        
        btn.setText("Executar Servidor RMI");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ServerRMI.init();
                primaryStage.close();
            }
        });
        btn2.setText("Executar Servidor TCP");
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Server.init();
                primaryStage.close();
            }
        });
        btn3.setText("Executar Cliente");
        btn3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Client.init();
                primaryStage.close();
            }
        });
        AnchorPane root = new AnchorPane();
        
        root.getChildren().addAll(btn, btn2, btn3);
        
        Scene scene = new Scene(root, 300, 250);
        //primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
