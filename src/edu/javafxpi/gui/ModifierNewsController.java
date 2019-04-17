/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.javafxpi.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import edu.javafxpi.entities.Categorie;
import edu.javafxpi.entities.Newsletter;
import edu.javafxpi.entities.Post;
import edu.javafxpi.services.CRUDCategorie;
import edu.javafxpi.services.CRUDNewsletter;
import edu.javafxpi.services.CRUDPost;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ModifierNewsController implements Initializable {

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
        // TODO
    }    
       private Newsletter selectedPost;

    @FXML
    private void AjouterPost(ActionEvent event) throws IOException, SQLException {
        
          
     
         Date date = java.sql.Date.valueOf(datecreation.getValue());
          // Categorie c = (Categorie) categorie_mod.getSelectionModel().getSelectedItem();
        CRUDNewsletter po = new CRUDNewsletter();

    
        
        Newsletter p = new Newsletter(title.getText(), description.getText(), contenu.getText(), Lien.getText(), datecreation.getValue() );
       
              po.modifierNewsletter(p, selectedPost.getId());
               
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/edu/javafxpi/gui/ListNews.fxml"));
        Parent root;
        root=loader.load();
        ajouter.getScene().setRoot(root);
     
       // Parent root;
       // root=loader.load();
        //modifierPost.getScene().setRoot(root);
        
        
        new Alert(Alert.AlertType.INFORMATION,"Modification effectuée avec succées" ).show();
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
     public void initData(Newsletter p) throws SQLException
    {
        selectedPost = p;
        title.setText(p.getTitre());
         description.setText(p.getSujet());
           realisateur.setText(p.getLien());
        datecreation.setValue(p.getDatecreation());
        contenu.setText(p.getContenu());
       /* txttitre.setText(p.getTitre());
         txtsujet.setText(p.getSujet());
           txtsujet.setText(p.getLien());
        txtcontent.setText(p.getContenu());*/
       
         
    }
         List<String> lstFile;

    
}
