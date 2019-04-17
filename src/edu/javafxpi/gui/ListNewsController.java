/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.javafxpi.gui;

import com.jfoenix.controls.JFXButton;
import edu.javafxpi.entities.Newsletter;
import edu.javafxpi.entities.Post;
import edu.javafxpi.services.CRUDNewsletter;
import edu.javafxpi.services.CRUDPost;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ListNewsController implements Initializable {

    @FXML
    private BorderPane border_pane;
    @FXML
    private VBox content;
    @FXML
    private HBox menubar;
    @FXML
    private TableView<Newsletter> table;
    @FXML
    private TableColumn<?, ?> idPost;
    @FXML
    private TableColumn<?, ?> titlePost;
    @FXML
    private TableColumn<?, ?> descriptionPost;
    public ArrayList<Newsletter> ran;
    @FXML
    private JFXButton Envoyer;

    /**
     * Initializes the controller class.
     */
     public ObservableList<Newsletter> getData() throws SQLException {
		
	CRUDNewsletter p=new CRUDNewsletter();
        ObservableList<Newsletter> data = FXCollections.observableArrayList(p.afficherNewsletter());
				
		return data;
	}
     private void affichertable() {
       
     try {
         
         
         CRUDNewsletter pos = new CRUDNewsletter();
         ArrayList<Newsletter> cat= pos.afficherNewsletter();
         
         ObservableList observableList = FXCollections.observableArrayList(cat);
         table.setItems(observableList);
     
         idPost.setCellValueFactory(new PropertyValueFactory<>("id"));
         titlePost.setCellValueFactory(new PropertyValueFactory<>("titre"));
         descriptionPost.setCellValueFactory(new PropertyValueFactory<>("sujet"));
         
      
     }
      catch (SQLException ex) {
         Logger.getLogger(ListPostsController.class.getName()).log(Level.SEVERE, null, ex);
     }
     }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
         Parent sidebar = FXMLLoader.load(getClass().getResource("/edu/javafxpi/gui/Sidebar.fxml"));
         border_pane.setLeft(sidebar);
           affichertable();
         
         
     } catch (IOException ex) {
         Logger.getLogger(ListPostsController.class.getName()).log(Level.SEVERE, null, ex);
     }
        // TODO
    }    
    boolean flag = true;

    @FXML
    private void open_sidebar(ActionEvent event) throws IOException {
          BorderPane border_pane = (BorderPane) ((Node) event.getSource()).getScene().getRoot();
        if (flag == true) {
            Parent sidebar = FXMLLoader.load(getClass().getResource("/edu/javafxpi/gui/Sidebar.fxml"));
            border_pane.setLeft(sidebar);
            flag = false;
        } else {
            border_pane.setLeft(null);
            flag = true;
        }
    }
@FXML
   private void delete(ActionEvent event) throws IOException {
     try {
         ObservableList<Newsletter> allPosts;
         allPosts = table.getItems();
         Newsletter postSelected = new Newsletter();
         CRUDNewsletter postCrud = new CRUDNewsletter();
         
         if (table.getSelectionModel().getSelectedItem() == null) {
             new Alert(Alert.AlertType.INFORMATION, "Veuillez selectionner un post !").show();
         } else {
             postSelected = table.getSelectionModel().getSelectedItem();
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
             alert.setTitle("Suppression");
             alert.setHeaderText("Veuillez Confirmer");
             alert.setContentText("Etes-vous sur de supprimer cette Nexsletter ?");
             
             Optional<ButtonType> result = alert.showAndWait();
             if (result.get() == ButtonType.OK) {
                 postCrud.supprimerNewsletter(postSelected.getId());
                 new Alert(Alert.AlertType.INFORMATION, "Suppression effectuée").show();
                 
             } else {
                 
             }
             Parent parent2 = FXMLLoader
                     .load(getClass().getResource("/edu/javafxpi/gui/ListNews.fxml"));
             
             Scene scene = new Scene(parent2);
             Stage stage = (Stage) ((Node) event.getSource())
                     .getScene().getWindow();
             stage.setScene(scene);
             stage.show();
         }
     } catch (SQLException ex) {
         Logger.getLogger(ListPostsController.class.getName()).log(Level.SEVERE, null, ex);
     }
    }

    @FXML
    private void modifier(ActionEvent event) throws IOException, SQLException {
         if (table.getSelectionModel().getSelectedItem() == null) {
             new Alert(Alert.AlertType.INFORMATION, "Veuillez selectionner un post!").show();
         } else {
        FXMLLoader loader = new FXMLLoader();
        System.out.println(table.getSelectionModel().getSelectedItems());
        loader.setLocation(getClass().getResource("/edu/javafxpi/gui/modifierNews.fxml"));
        Parent tableViewParent = loader.load();
        
        Scene tableViewScene = new Scene(tableViewParent);
        
        //access the controller and call a method
        ModifierNewsController controller = loader.getController();
       
        controller.initData(table.getSelectionModel().getSelectedItem());
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
         }
       
    }

    @FXML
    private void ajout(ActionEvent event) throws IOException {
         Parent parent2 = FXMLLoader
                .load(getClass().getResource("/edu/javafxpi/gui/ajoutNews.fxml"));

        Scene scene = new Scene(parent2);
        Stage stage = (Stage) ((Node) event.getSource())
                .getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Ajouter News");
        stage.show();
    }

    @FXML
    private void EnvoyerNews(ActionEvent event) {
        Newsletter postSelected = new Newsletter();
           postSelected = table.getSelectionModel().getSelectedItem();
         
     
          System.out.println(postSelected.getId());
             NewsController.id =postSelected.getId();
     
        
         try {
            //;
            FXMLLoader l=new FXMLLoader();
            Parent root = l.load(getClass().getResource("/edu/javafxpi/gui/News.fxml"));
            Scene scene = new Scene(root);
          
            // scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
          Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
           window.setScene(scene);
          // window.getProperties().put("k", selectedNewsletter.getId());
         // SendNewsletterController c= l.getController();
         // c.initData(selectedNewsletter.getId());
            window.show();
             border_pane.setCenter(root);
        } catch(Exception e) {
            e.printStackTrace();
        }
     
       // Envoyer.getScene().setRoot(root);
    }
        
    }
    

