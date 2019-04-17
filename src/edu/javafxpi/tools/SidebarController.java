/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.javafxpi.tools;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class SidebarController implements Initializable {
    @FXML
    private VBox sidebar;
    @FXML
    private HBox prom;
     @FXML
    private HBox pack;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       prom.setOnMouseClicked(event->{
             try {
                Parent parent2=FXMLLoader
                        .load(getClass().getResource("/edu/javafxpi/views/ListPromotions.fxml"));
                
                Scene scene=new Scene(parent2);
                Stage stage=(Stage) ((Node) event.getSource())
                        .getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("List Promotion");
                stage.show();

            } catch (IOException ex) {
                Logger.getLogger(SidebarController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        pack.setOnMouseClicked(event->{
             try {
                Parent parent2=FXMLLoader
                        .load(getClass().getResource("/edu/javafxpi/views/ListPacks.fxml"));
                
                Scene scene=new Scene(parent2);
                Stage stage=(Stage) ((Node) event.getSource())
                        .getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("List Packs");
                stage.show();

            } catch (IOException ex) {
                Logger.getLogger(SidebarController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }   
    
}
