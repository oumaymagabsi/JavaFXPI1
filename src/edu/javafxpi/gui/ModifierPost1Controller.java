/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.javafxpi.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import edu.javafxpi.entities.Categorie;
import edu.javafxpi.entities.Post;
import edu.javafxpi.services.CRUDCategorie;
import edu.javafxpi.services.CRUDPost;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Hsine
 */
public class ModifierPost1Controller implements Initializable {
       private Post selectedPost;

    @FXML
    private BorderPane border_pane;
    @FXML
    private HBox menubar;
    @FXML
    private JFXButton valider;
    @FXML
    private TextArea contenu_mod;
    @FXML
    private TextField title_mod;
    @FXML
    private TextField description_mod;
    @FXML
    private TextField realisateur_mod;
    @FXML
    private JFXDatePicker datecreation_mod;
    @FXML
    private Button image_mod;
    @FXML
    private ComboBox<Categorie> categorie_mod;

    /**
     * Initializes the controller class.
     */
     public void initData(Post p) throws SQLException
    {
        selectedPost = p;
        title_mod.setText(p.getTitle());
         description_mod.setText(p.getDescription());
           realisateur_mod.setText(p.getRealisateur());
        datecreation_mod.setValue(p.getDatecreation());
        contenu_mod.setText(p.getContenu());
         CRUDCategorie filmCrud=new CRUDCategorie();
         ArrayList l = (ArrayList) filmCrud.afficherCategorie();
       
         StringConverter<Categorie> converter = new StringConverter<Categorie>() {
    @Override
    public String toString(Categorie object) {
        return object.getCategorie();
    }

    @Override
    public Categorie fromString(String string) {
        return null;
    }
};
          categorie_mod.setConverter(converter);
          categorie_mod.setItems(FXCollections.observableList(l));
          categorie_mod.getSelectionModel().select(filmCrud.getCategorieParNom(p.getCategorie().getCategorie()));
         
    }
         List<String> lstFile;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
         lstFile= new ArrayList<>();
        lstFile.add("*.doc");
        lstFile.add("*.PNG");
        lstFile.add("*.JPG");
        lstFile.add("*.docx");
        lstFile.add("*.DOC");
        lstFile.add("*.DOCX");
        lstFile.add("*.png");
        lstFile.add("*.jpg");
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

    @FXML
    private void modifier(ActionEvent event) throws SQLException, IOException {
          
        Categorie c= (Categorie) categorie_mod.getSelectionModel().getSelectedItem();
     
         Date date = java.sql.Date.valueOf(datecreation_mod.getValue());
          // Categorie c = (Categorie) categorie_mod.getSelectionModel().getSelectedItem();
     CRUDPost po = new CRUDPost() ;

     Image im=new Image("file:"+image_mod.getText());
     ImageView mv= new ImageView(im);
     mv.setFitWidth(70);
                        mv.setFitHeight(80);
        
        Post p = new Post(
                   datecreation_mod.getValue(), 
                 title_mod.getText(),
                c, 
              description_mod.getText(),
               realisateur_mod.getText(),
               mv,
              contenu_mod.getText()
        );
       
           CRUDPost cp = new CRUDPost();
              cp.modifierPost(p, selectedPost.getId());
               
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/edu/javafxpi/gui/ListPosts.fxml"));
        Parent root;
        root=loader.load();
        valider.getScene().setRoot(root);
     
       // Parent root;
       // root=loader.load();
        //modifierPost.getScene().setRoot(root);
        
        
        new Alert(Alert.AlertType.INFORMATION,"Modification effectuée avec succées" ).show();
    }

    @FXML
    private void chooseImage(ActionEvent event) {
        FileChooser fc= new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image File",lstFile));
        File f=fc.showOpenDialog(null);
        if (f != null)
        {
        image_mod.setText( f.getAbsolutePath());
        
    }
    }
    
}
