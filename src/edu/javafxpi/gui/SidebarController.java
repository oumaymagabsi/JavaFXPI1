/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.javafxpi.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
    @FXML
    private HBox occasion;
    @FXML
    private Label GererPost;
    @FXML
    private Label GererCategorie;
    @FXML
    private Label GererNewslletter;
    @FXML
    private Label controle;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         controle.setOnMouseClicked(event -> {
            try {
                Parent parent2 = FXMLLoader
                        .load(getClass().getResource("/edu/javafxpi/gui/Refresh.fxml"));

                Scene scene = new Scene(parent2);
                Stage stage = (Stage) ((Node) event.getSource())
                        .getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("List News");
                stage.show();

            } catch (IOException ex) {
            }
        });
         GererNewslletter.setOnMouseClicked(event -> {
            try {
                Parent parent2 = FXMLLoader
                        .load(getClass().getResource("/edu/javafxpi/gui/ListNews.fxml"));

                Scene scene = new Scene(parent2);
                Stage stage = (Stage) ((Node) event.getSource())
                        .getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("List News");
                stage.show();

            } catch (IOException ex) {
            }
        });
          GererCategorie.setOnMouseClicked(event -> {
            try {
                Parent parent2 = FXMLLoader
                        .load(getClass().getResource("/edu/javafxpi/gui/ListCategorie.fxml"));

                Scene scene = new Scene(parent2);
                Stage stage = (Stage) ((Node) event.getSource())
                        .getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("List Categorie");
                stage.show();

            } catch (IOException ex) {
            }
        });
    
  GererPost.setOnMouseClicked(event -> {
            try {
                Parent parent2 = FXMLLoader
                        .load(getClass().getResource("/edu/javafxpi/gui/ListPosts.fxml"));

                Scene scene = new Scene(parent2);
                Stage stage = (Stage) ((Node) event.getSource())
                        .getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("List Posts");
                stage.show();

            } catch (IOException ex) {
            }
        });    
   
    
}
}