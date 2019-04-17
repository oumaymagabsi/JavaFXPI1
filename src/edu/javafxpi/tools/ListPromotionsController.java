/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.javafxpi.tools;

import edu.javafxpi.entities.Personne;
import edu.javafxpi.entities.Promotion;
import edu.javafxpi.entities.Film;
import edu.javafxpi.services.PromotionCrud;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class ListPromotionsController implements Initializable {
    @FXML
    private BorderPane border_pane;
    
    boolean flag = true;
    
    
    @FXML
    private TableView<Promotion> listPromotions;
    @FXML
    private TableColumn<Promotion, Integer> id;
    @FXML
    private TableColumn<Promotion, Date> dateDeb;
    @FXML
    private TableColumn<Promotion, Date> dateFin;
    @FXML
    private TableColumn<Promotion, String> pourcentage;
     @FXML
    private TableColumn<Promotion, String> film;
      @FXML
    private Button delete;

       
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {
            Parent sidebar = FXMLLoader.load(getClass().getResource("/edu/javafxpi/views/Sidebar.fxml"));
            border_pane.setLeft(sidebar);
            
            
        PromotionCrud promotionCrud=new PromotionCrud();
        ArrayList arrayList= (ArrayList) promotionCrud.afficherPromotion();
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        listPromotions.setItems(observableList);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        dateDeb.setCellValueFactory(new PropertyValueFactory<>("dateDeb"));
        dateFin.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
        pourcentage.setCellValueFactory(new PropertyValueFactory<>("pourcentage"));
        film.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Promotion, String>, ObservableValue<String>>() {
    @Override
    public ObservableValue<String> call(TableColumn.CellDataFeatures<Promotion, String> p) {
        return new SimpleStringProperty(p.getValue().getProm_film()+"");
    }
    
       
});
        
        } catch (IOException ex) {
            Logger.getLogger(ListPromotionsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


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
    private void delete(ActionEvent event) throws IOException {
        ObservableList<Promotion> productSelected,allProducts;
        PromotionCrud promotionCrud=new PromotionCrud();
        allProducts= listPromotions.getItems();
        productSelected=listPromotions.getSelectionModel().getSelectedItems();
        productSelected.forEach(allProducts::remove);
        productSelected.forEach((e)->promotionCrud.supprimerPromotion(e.getId()));
        new Alert(Alert.AlertType.INFORMATION, "Suppression effectuée").show();
    }
    @FXML
    private void ajout(ActionEvent event) throws IOException{
        
                Parent parent2=FXMLLoader
                        .load(getClass().getResource("/edu/javafxpi/views/AjoutPromotion.fxml"));
                
                Scene scene=new Scene(parent2);
                Stage stage=(Stage) ((Node) event.getSource())
                        .getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Ajouter Promotion");
                stage.show();

           
        
    }
     @FXML
    private void modifier(ActionEvent event) throws IOException{
       FXMLLoader loader = new FXMLLoader();
        System.out.println(listPromotions.getSelectionModel().getSelectedItems());
        loader.setLocation(getClass().getResource("/edu/javafxpi/views/ModifierPromotion.fxml"));
        Parent tableViewParent = loader.load();
        
        Scene tableViewScene = new Scene(tableViewParent);
        
        //access the controller and call a method
        ModifierPromotionController controller = loader.getController();
       
        controller.initData(listPromotions.getSelectionModel().getSelectedItem());
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
           
        
    }
    
   
}
