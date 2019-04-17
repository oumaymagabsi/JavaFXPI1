/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.javafxpi.gui;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class ItemController implements Initializable {

    @FXML
    private Label prix;
    @FXML
    private Label titre;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }


    public void setPrix(Double prix) {
        this.prix.setText(prix.toString());
    }

   

    public void setTitre(String titre) {
        this.titre.setText(titre);
    }

}
