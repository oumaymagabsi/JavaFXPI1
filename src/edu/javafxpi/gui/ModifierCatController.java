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
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
 * @author asus
 */
public class ModifierCatController implements Initializable {

    @FXML
    private BorderPane border_pane;
    @FXML
    private HBox menubar;
    @FXML
    private JFXButton valider;
    @FXML
    private TextField title_mod;

    /**
     * Initializes the controller class.
     */
    public void initData(Categorie p) throws SQLException {
                selectedPost = p;

        title_mod.setText(p.getCategorie());
    }
    List<String> lstFile;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
       boolean flag = true;

    @FXML
    private void open_sidebar(ActionEvent event) throws IOException {
         BorderPane border_pane = (BorderPane) ((Node) event.getSource()).getScene().getRoot();
        if (flag == true) {
            Parent sidebar = FXMLLoader.load(getClass().getResource("/edu/javafxpi/views/Sidebar.fxml"));
            border_pane.setLeft(sidebar);
            flag = false;
        } else {
            border_pane.setLeft(null);
            flag = true;
        }
        
    }
       private Categorie selectedPost;

    @FXML
    private void modifier(ActionEvent event) throws SQLException, IOException {
              // Categorie c = (Categorie) categorie_mod.getSelectionModel().getSelectedItem();
        CRUDCategorie po = new CRUDCategorie();

    
        Categorie p = new Categorie(
                 title_mod.getText()
                
        );po.modifierCat(p, 0);
       
              po.modifierCat(p, selectedPost.getId());
               
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/edu/javafxpi/gui/ListCategorie.fxml"));
        Parent root;
        root=loader.load();
        valider.getScene().setRoot(root);
     
       // Parent root;
       // root=loader.load();
        //modifierPost.getScene().setRoot(root);
        
        
        new Alert(Alert.AlertType.INFORMATION,"Modification effectuée avec succées" ).show();
    }
    
}
