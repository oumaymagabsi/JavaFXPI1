/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.javafxpi.gui;

import edu.javafxpi.entities.Categorie;
import edu.javafxpi.entities.Post;
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
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class ListPostsController implements Initializable {
 @FXML
    private BorderPane border_pane;
 @FXML
    private TableView<Post> table;
      @FXML
    private TableColumn<?, ?> idPost;
       @FXML
    private TableColumn<?, ?> datePost;
        @FXML
    private TableColumn<?, ?> titlePost;
         @FXML
    private TableColumn<?,?> categorie_idPost;
          @FXML
    private TableColumn<?, ?> descriptionPost;
           @FXML
    private TableColumn<?, ?> realisateurPost;
     @FXML
private TableColumn<?, ?>  imagePost;
    
    public ArrayList<Post> ran;
    boolean flag = true;
    @FXML
    private VBox content;
    @FXML
    private HBox menubar;
    @FXML
    private TextField tf_search;
    /**
     * Initializes the controller class.
     */
     public ObservableList<Post> getData() throws SQLException {
		
	CRUDPost p=new CRUDPost();
        ObservableList<Post> data = FXCollections.observableArrayList(p.afficherPost());
				
		return data;
	}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     try {
         Parent sidebar = FXMLLoader.load(getClass().getResource("/edu/javafxpi/gui/Sidebar.fxml"));
         border_pane.setLeft(sidebar);
           affichertable();
         
            Search();
         
     } catch (IOException ex) {
         Logger.getLogger(ListPostsController.class.getName()).log(Level.SEVERE, null, ex);
     } catch (SQLException ex) {
         Logger.getLogger(ListPostsController.class.getName()).log(Level.SEVERE, null, ex);
     }
    }    
    private ObservableList<Post> data;
      public void Search() throws SQLException{
        CRUDPost bs = new CRUDPost();
    
        tf_search.setOnKeyReleased(e->{
            if(tf_search.getText().equals("")){
                bs.afficherPost();
            }
            else {
                data.clear();
                data=bs.Search(tf_search.getText());
                table.setItems(data);
            }
        });

}
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

     private void affichertable() {
       
     try {
         
         
         CRUDPost pos = new CRUDPost();
         ArrayList<Post> cat= pos.afficherPost();
         
         ObservableList observableList = FXCollections.observableArrayList(cat);
         table.setItems(observableList);
     
         idPost.setCellValueFactory(new PropertyValueFactory<>("id"));
         datePost.setCellValueFactory(new PropertyValueFactory<>("datecreation"));
         titlePost.setCellValueFactory(new PropertyValueFactory<>("title"));
         categorie_idPost.setCellValueFactory(new PropertyValueFactory<>("categorie"));
         descriptionPost.setCellValueFactory(new PropertyValueFactory<>("description"));
         realisateurPost.setCellValueFactory(new PropertyValueFactory<>("realisateur"));
         
         imagePost.setCellValueFactory(new PropertyValueFactory<>("image"));
      
     }
      catch (SQLException ex) {
         Logger.getLogger(ListPostsController.class.getName()).log(Level.SEVERE, null, ex);
     }
     }

@FXML
    private void delete(ActionEvent event) throws IOException {
     try {
         ObservableList<Post> allPosts;
         allPosts = table.getItems();
         Post postSelected = new Post();
         CRUDPost postCrud = new CRUDPost();
         
         if (table.getSelectionModel().getSelectedItem() == null) {
             new Alert(Alert.AlertType.ERROR, "Veuillez selectionner un post !").show();
         } else {
             postSelected = table.getSelectionModel().getSelectedItem();
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
             alert.setTitle("Suppression");
             alert.setHeaderText("Veuillez Confirmer");
             alert.setContentText("Etes-vous sur de supprimer ce post ?");
             
             Optional<ButtonType> result = alert.showAndWait();
             if (result.get() == ButtonType.OK) {
                 postCrud.supprimerPost(postSelected.getId());
                 new Alert(Alert.AlertType.INFORMATION, "Suppression effectuée").show();
                 
             } else {
                 
             }
             Parent parent2 = FXMLLoader
                     .load(getClass().getResource("/edu/javafxpi/gui/ListPosts.fxml"));
             
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
    private void ajout(ActionEvent event) throws IOException {

        Parent parent2 = FXMLLoader
                .load(getClass().getResource("/edu/javafxpi/gui/ajoutPost.fxml"));

        Scene scene = new Scene(parent2);
        Stage stage = (Stage) ((Node) event.getSource())
                .getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Ajouter Post");
        stage.show();

    }
     @FXML
    private void modifier(ActionEvent event) throws IOException, SQLException{
         if (table.getSelectionModel().getSelectedItem() == null) {
             new Alert(Alert.AlertType.INFORMATION, "Veuillez selectionner un post !").show();
         } else {
       FXMLLoader loader = new FXMLLoader();
        System.out.println(table.getSelectionModel().getSelectedItems());
        loader.setLocation(getClass().getResource("/edu/javafxpi/gui/ModifierPost.fxml"));
        Parent tableViewParent = loader.load();
        
        Scene tableViewScene = new Scene(tableViewParent);
        
        //access the controller and call a method
        ModifierPost1Controller controller = loader.getController();
       
        controller.initData(table.getSelectionModel().getSelectedItem());
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
           
         }
    }


}   


  

