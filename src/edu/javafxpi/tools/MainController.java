/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.javafxpi.tools;
import javafxpi1.JavaFXPI1;
import javafx.event.EventHandler;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class MainController implements Initializable {
    
  
    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private BorderPane border_pane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // makeStageDrageable();
        try {
            Parent contentarea = FXMLLoader.load(getClass().getResource("/edu/javafxpi/views/Content.fxml"));
            Parent sidebar = FXMLLoader.load(getClass().getResource("/edu/javafxpi/views/Sidebar.fxml"));
            border_pane.setCenter(contentarea);
            border_pane.setLeft(sidebar);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
   private void makeStageDrageable() {
        border_pane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        border_pane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                JavaFXPI1.stage.setX(event.getScreenX() - xOffset);
                JavaFXPI1.stage.setY(event.getScreenY() - yOffset);
                JavaFXPI1.stage.setOpacity(0.7f);
            }
        });
        border_pane.setOnDragDone((e) -> {
            JavaFXPI1.stage.setOpacity(1.0f);
        });
        border_pane.setOnMouseReleased((e) -> {
            JavaFXPI1.stage.setOpacity(1.0f);
        });

    }
}


