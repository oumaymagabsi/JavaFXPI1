/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.Projet.controllers;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class GestionNewsletterController implements Initializable {

    @FXML
    private Button Ajouter;
    @FXML
    private TextField sujet;
    @FXML
    private TextField titre;
    @FXML
    private JFXDatePicker datecreation;
    @FXML
    private TextField lien;
    @FXML
    private TextArea contenu;
    @FXML
    private BorderPane border_pane;
    @FXML
    private Label txtsujet;
    @FXML
    private Label txttitre;
    @FXML
    private JFXTextArea txtcontent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouterNewsletter(ActionEvent event) {
    }
    
}
