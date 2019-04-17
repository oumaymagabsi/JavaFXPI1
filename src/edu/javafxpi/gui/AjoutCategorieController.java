/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.javafxpi.gui;

import com.jfoenix.controls.JFXButton;
import edu.javafxpi.entities.Categorie;
import edu.javafxpi.entities.Post;
import edu.javafxpi.services.CRUDCategorie;
import edu.javafxpi.services.CRUDPost;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author Hsine
 */
public class AjoutCategorieController implements Initializable {

    @FXML
    private BorderPane border_pane;
    @FXML
    private HBox menubar;
    @FXML
    private JFXButton ajouter;
    @FXML
    private TextField categorie;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    @FXML
    private void AjouterPost(ActionEvent event) throws IOException, SQLException {
           if (categorie.getText().isEmpty())
        {Alert alert5 = new Alert(Alert.AlertType.ERROR);
            alert5.setTitle("Verifier categorie ");
            alert5.setHeaderText(null);
            alert5.setContentText("Merci de bien vouloir remplir votre champ");
            alert5.showAndWait();
        }else
             if(!categorie.getText().matches("^[a-zA-Z\\s]*$") )
         {
              Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Valider categorie ");
            alert1.setHeaderText(null);
            alert1.setContentText("Veuillez valider categorie ");
            alert1.showAndWait();
         }else
             {
           CRUDCategorie po = new CRUDCategorie() ;
    
  

   
      Categorie p= new Categorie(
                  categorie.getText());
      
      
       po.ajouterCategorie(p);
               
        new Alert(Alert.AlertType.INFORMATION,"Ajout effectuée avec succées" ).show();
        
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/javafxpi/gui/ListCategorie.fxml"));
            Parent root;
            root = loader.load();
            ajouter.getScene().setRoot(root);}
    }
    
}
