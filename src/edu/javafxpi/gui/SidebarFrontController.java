/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.javafxpi.gui;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class SidebarFrontController implements Initializable {

    @FXML
    private JFXButton prom;
    @FXML
    private JFXButton pack;
    @FXML
    private ImageView img;
    @FXML
    private JFXButton abonne;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
            abonne.setOnMouseClicked(event -> {
            try {
                Parent parent2 = FXMLLoader
                        .load(getClass().getResource("/edu/javafxpi/gui/Abonne.fxml"));

                Scene scene = new Scene(parent2);
                Stage stage = (Stage) ((Node) event.getSource())
                        .getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("abonnement");
                stage.show();

            } catch (IOException ex) {
            }
        });
    }    
    
}
