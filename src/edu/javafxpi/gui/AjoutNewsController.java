/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.javafxpi.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import edu.javafxpi.entities.Newsletter;
import edu.javafxpi.services.CRUDNewsletter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AjoutNewsController implements Initializable {

    @FXML
    private BorderPane border_pane;
    @FXML
    private HBox menubar;
    @FXML
    private JFXButton ajouter;
    @FXML
    private Label sujet;
    @FXML
    private TextField title;
    @FXML
    private Label titre;
    @FXML
    private TextField description;
    @FXML
    private Label Lien;
    @FXML
    private TextField realisateur;
    @FXML
    private JFXDatePicker datecreation;
    @FXML
    private TextArea contenu;
    @FXML
    private BorderPane border_pane1;
    @FXML
    private Label txtsujet;
    @FXML
    private Label txttitre;
    @FXML
    private Label txtdatecreation;
    @FXML
    private JFXTextArea txtcontent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtsujet.textProperty().bind(title.textProperty());
      txttitre.textProperty().bind(description.textProperty());
       txtcontent.textProperty().bind(contenu.textProperty());
              txtdatecreation.textProperty().bind(datecreation.valueProperty().asString());

        try {
         Parent sidebar = FXMLLoader.load(getClass().getResource("/edu/javafxpi/gui/Sidebar.fxml"));
         border_pane.setLeft(sidebar);
        
         
         
     } catch (IOException ex) {
         Logger.getLogger(ListPostsController.class.getName()).log(Level.SEVERE, null, ex);
     }
        // TODO
    }    

    @FXML
    private void AjouterPost(ActionEvent event) throws SQLException, IOException {
    
        if (contenu.getText().isEmpty()||description.getText().isEmpty()||title.getText().isEmpty())
        {Alert alert5 = new Alert(Alert.AlertType.ERROR);
            alert5.setTitle("Verifier news ");
            alert5.setHeaderText(null);
            alert5.setContentText("Merci de bien vouloir remplir votre champ");
            alert5.showAndWait();}
       else
             if(!title.getText().matches("^[a-zA-Z\\s]*$") )
         {
              Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Valider sujet ");
            alert1.setHeaderText(null);
            alert1.setContentText("Veuillez valider sujet ");
            alert1.showAndWait();
         }
        
        else if(LocalDate.now().isAfter(datecreation.getValue()))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Date Du Panier est inférieure à la date actuelle!");
            alert.setContentText("Erreur");
            alert.showAndWait();
        }else
        {
         Newsletter n = new    Newsletter(
              title.getText(),
              description.getText(),
              contenu.getText(),
          realisateur.getText(),
          datecreation.getValue()
          );
      
        CRUDNewsletter cr = new CRUDNewsletter();
       cr.ajouterNewsletter(n);
        //cleartext();
       new Alert(Alert.AlertType.INFORMATION,"Ajout effectuée avec succées" ).show();
       FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/javafxpi/gui/ListNews.fxml"));
            Parent root;
            root = loader.load();
            ajouter.getScene().setRoot(root);
        }
    }
    
}
