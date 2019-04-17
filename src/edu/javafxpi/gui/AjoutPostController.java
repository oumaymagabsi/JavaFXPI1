/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.javafxpi.gui;

import com.jfoenix.controls.JFXDatePicker;
import edu.javafxpi.entities.Categorie;
import edu.javafxpi.entities.Post;
import edu.javafxpi.services.CRUDPost;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */

public class AjoutPostController implements Initializable {
 List<String> lstFile;
  public ArrayList<Post> ran;
    @FXML
    private DatePicker datecreation;
    @FXML
    private TextField title;
    @FXML
    private TextField description;
    @FXML
    private TextArea contenu;
    @FXML
    private TextField realisateur;
   @FXML
    private ComboBox categorie;
    @FXML
    private Button image;
     @FXML
    private TextArea contenu_mod;
      @FXML
    private TextField title_mod;
       @FXML
    private Button image_mod;
        @FXML
    private TextField description_mod;
         @FXML
    private TextField realisateur_mod;
          @FXML
    private JFXDatePicker datecreation_mod;
          
       private Post selectedPost;
       @FXML
      private  BorderPane border_pane;
        @FXML
    private ComboBox categorie_mod;
    @FXML
    private TextField id;
    @FXML
    private Button ajouter;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     try {
         Parent sidebar = FXMLLoader.load(getClass().getResource("/edu/javafxpi/gui/Sidebar.fxml"));
         border_pane.setLeft(sidebar);
         
         lstFile= new ArrayList<>();
         lstFile.add("*.doc");
         lstFile.add("*.PNG");
         lstFile.add("*.JPG");
         lstFile.add("*.docx");
         lstFile.add("*.DOC");
         lstFile.add("*.DOCX");
         lstFile.add("*.png");
         lstFile.add("*.jpg");
         afficherCategorie();
     } catch (IOException ex) {
         Logger.getLogger(AjoutPostController.class.getName()).log(Level.SEVERE, null, ex);
     }
    }   
    
    
     private void afficherCategorie (){
        try {
            CRUDPost pos = new CRUDPost();
           ArrayList<Categorie> cat= pos.listeCategorie1();
          // categorie=new ComboBox();
           for(Categorie c:cat)
           {
             
            categorie.getItems().add(c.getCategorie());
           }
           // categorie.setValue();
        } catch (SQLException ex) {
           
        }
    }
    @FXML
    public void AjouterPost(ActionEvent event) throws SQLException, IOException {
        if (contenu.getText().isEmpty()||description.getText().isEmpty()||realisateur.getText().isEmpty()||image.getText().isEmpty())
        {Alert alert5 = new Alert(Alert.AlertType.ERROR);
            alert5.setTitle("Verifier post ");
            alert5.setHeaderText(null);
            alert5.setContentText("Merci de bien vouloir remplir votre champ");
            alert5.showAndWait();
        }else 
           if(!realisateur.getText().matches("^[a-zA-Z\\s]*$") )
         {
              Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Valider realisateur ");
            alert1.setHeaderText(null);
            alert1.setContentText("Veuillez valider realisateur ");
            alert1.showAndWait();
         }
       if(LocalDate.now().isAfter(datecreation.getValue()))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Date Du Panier est inférieure à la date actuelle!");
            alert.setContentText("Erreur");
            alert.showAndWait();
        }
        else
        {
     CRUDPost po = new CRUDPost() ;
    
     Categorie  c= po.getCategorieParNom(categorie.getValue().toString());

     Image im=new Image("file:"+image.getText());
     ImageView mv= new ImageView(im);
     mv.setFitWidth(70);
                        mv.setFitHeight(80);
      Post p= new Post(
                  datecreation.getValue(), 
                 title.getText(),
                c, 
              description.getText(),
               realisateur.getText(),
              mv,
              contenu.getText());
      
      
       po.ajouterPost(p);
        
        new Alert(Alert.AlertType.INFORMATION,"Ajout effectuée avec succées" ).show();
        
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/javafxpi/gui/ListPosts.fxml"));
            Parent root;
            root = loader.load();
            ajouter.getScene().setRoot(root);}
      //   cleartext();
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
    
    
     @FXML
    public void chooseImage(ActionEvent event) {
        FileChooser fc= new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image File",lstFile));
        File f=fc.showOpenDialog(null);
        if (f != null)
        {
        image.setText( f.getAbsolutePath());
        
    }
    }
}
