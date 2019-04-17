/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.javafxpi.gui;

import com.jfoenix.controls.JFXTextArea;
import edu.javafxpi.entities.Categorie;
import edu.javafxpi.entities.Post;
import edu.javafxpi.services.CRUDCategorie;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hsine
 */
public class ListCategorieController implements Initializable {

    @FXML
    private BorderPane border_pane;
    @FXML
    private VBox content;
    @FXML
    private HBox menubar;
    @FXML
    private TableView<Categorie> table;
    @FXML
    private TableColumn<?, ?> idcat;
    @FXML
    private TableColumn<?, ?> categoriecat;
   public ArrayList<Categorie> ran;
    boolean flag = true;
    /**
     * Initializes the controller class.
     */
     public ObservableList<Categorie> getData() throws SQLException {
		
	        CRUDCategorie c=new CRUDCategorie();
        ObservableList<Categorie> data = FXCollections.observableArrayList(c.afficherCategorie());
				
		return data;
	}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          try {
         Parent sidebar = FXMLLoader.load(getClass().getResource("/edu/javafxpi/gui/Sidebar.fxml"));
         border_pane.setLeft(sidebar);
          AfficherCategorie() ;
         
         
     } catch (IOException ex) {
         Logger.getLogger(ListPostsController.class.getName()).log(Level.SEVERE, null, ex);
     }
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
    
  private void AfficherCategorie() {
         Task<ArrayList<Categorie>>task = new Task()
          {

            @Override
            protected Object call() throws SQLException {
                ran = (ArrayList<Categorie>) new CRUDCategorie().afficherCategorie();
                return ran;
            }
        };
        task.setOnSucceeded((WorkerStateEvent e) -> {
            System.out.println("5555555555555555555");
            idcat.setCellValueFactory(new PropertyValueFactory<>("id"));
           categoriecat.setCellValueFactory(new PropertyValueFactory<>("categorie"));
         
            table.setItems(FXCollections.observableArrayList(task.getValue()));
        });
        task.setOnFailed(e -> {
            AfficherCategorie();
        });
        Thread th = new Thread(task);
        th.start();
    }
  @FXML
    private void SupprimerCategorie(ActionEvent event) throws IOException {
          try {
         ObservableList<Categorie> allPosts;
         allPosts = table.getItems();
         Categorie postSelected = new Categorie();
         CRUDCategorie postCrud = new CRUDCategorie();
         
         if (table.getSelectionModel().getSelectedItem() == null) {
             new Alert(Alert.AlertType.INFORMATION, "Veuillez selectionner un categorie !").show();
         } else {
             postSelected = table.getSelectionModel().getSelectedItem();
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
             alert.setTitle("Suppression");
             alert.setHeaderText("Veuillez Confirmer");
             alert.setContentText("Etes-vous sur de supprimer cette promotion ?");
             
             Optional<ButtonType> result = alert.showAndWait();
             if (result.get() == ButtonType.OK) {
                 postCrud.supprimerCategorie(postSelected.getId());
                 new Alert(Alert.AlertType.INFORMATION, "Suppression effectuée").show();
                 
             } else {
                 
             }
             Parent parent2 = FXMLLoader
                     .load(getClass().getResource("/edu/javafxpi/gui/ListCategorie.fxml"));
             
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
    private void ModifierCategorie(ActionEvent event) throws IOException, SQLException {
         if (table.getSelectionModel().getSelectedItem() == null) {
             new Alert(Alert.AlertType.INFORMATION, "Veuillez selectionner un categorie !").show();
         } else {
        FXMLLoader loader = new FXMLLoader();
        System.out.println(table.getSelectionModel().getSelectedItems());
        loader.setLocation(getClass().getResource("/edu/javafxpi/gui/ModifierCat.fxml"));
        Parent tableViewParent = loader.load();
        
        Scene tableViewScene = new Scene(tableViewParent);
      
        //access the controller and call a method
        ModifierCatController controller = loader.getController();
       
        controller.initData(table.getSelectionModel().getSelectedItem());
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
         }
    }

@FXML
    private void AjouterCategorie(ActionEvent event) throws IOException {
         Parent parent2 = FXMLLoader
                .load(getClass().getResource("/edu/javafxpi/gui/ajoutCat.fxml"));

        Scene scene = new Scene(parent2);
        Stage stage = (Stage) ((Node) event.getSource())
                .getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Ajouter Post");
        stage.show();
    }

    
}
