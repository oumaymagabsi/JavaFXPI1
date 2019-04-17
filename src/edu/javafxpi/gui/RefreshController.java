/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.javafxpi.gui;

import DataStorage.myDB;
import edu.javafxpi.entities.Post;
import edu.javafxpi.entities.Publication;
import edu.javafxpi.services.CRUDPost;
import edu.javafxpi.services.CRUDPublication;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class RefreshController implements Initializable {

    @FXML
    private BorderPane border_pane;
    @FXML
    private VBox content;
    @FXML
    private HBox menubar;
    @FXML
    private TableView<?> table;
    @FXML
    private TableColumn<String,String> publication;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                    try {
                
         Parent sidebar = FXMLLoader.load(getClass().getResource("/edu/javafxpi/gui/Sidebar.fxml"));
         border_pane.setLeft(sidebar);
         affichertable();
         
     } catch (IOException ex) {
         Logger.getLogger(ListPostsController.class.getName()).log(Level.SEVERE, null, ex);
     }
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
    private void meilleurpost(ActionEvent event) 
    {     HashMap< Integer,Integer> pub=new HashMap<Integer,Integer>();
            try{
        String publication = "select count(postid) as nb,postid FROM `publication` group BY postid ";
        Connection con = myDB.getInstance().getConnexion();
            ResultSet rs = con.createStatement().executeQuery(publication);
            
            while( rs.next()){
                pub.put(rs.getInt(2), rs.getInt(1));
            }
             }   catch (SQLException ex) { 
            Logger.getLogger(RefreshController.class.getName()).log(Level.SEVERE, null, ex);
        } 
            int max=0;
            int max1=0;
            for (Map.Entry<Integer, Integer> entry : pub.entrySet()) {
                if(entry.getValue()>= max)
                {
                    max=entry.getValue();
                    max1=entry.getKey();
                          
                            
                    
                  
                }
                
	
	}
           // System.out.println("max "+max+max1);
            try{
            CRUDPost p=new CRUDPost();
           Post pos= p.getPostParId(max1);
 
          Alert alert5 = new Alert(Alert.AlertType.INFORMATION);
            alert5.setTitle("Post");
            alert5.setHeaderText(null);
            alert5.setContentText("le Post le plus populaire est    "+pos.getTitle());
            alert5.showAndWait();
            
            } catch (SQLException ex) { 
            Logger.getLogger(RefreshController.class.getName()).log(Level.SEVERE, null, ex);
        } 
            
            
    }

    @FXML
    private void Rafraishir(ActionEvent event) {
        ArrayList<Integer> pub=new ArrayList<Integer>();
        try{
         
         String publication = "SELECT * FROM `publication`";
           String mot_bloquer= "SELECT * FROM `mot_bloquer`";
            Connection con = myDB.getInstance().getConnexion();
            ResultSet rs = con.createStatement().executeQuery(publication);
            ResultSet rs1 = con.createStatement().executeQuery(mot_bloquer);
            while( rs1.next()){
                 
                   while( rs.next())
                   {
                      
                    if(rs.getString("contenu").contains(rs1.getString("mot")))
                   {
                   
                   pub.add(rs.getInt("id"));
                   
                   
                   
                   }      
                   }
                   
                
            }
            rs.close();
            rs1.close();
            for(int id:pub)
            {
                CRUDPublication p=new  CRUDPublication();
                   p.supprimerPublicaion(id);
           Alert alert5 = new Alert(Alert.AlertType.INFORMATION);
            alert5.setTitle("Blog");
            alert5.setHeaderText(null);
            alert5.setContentText("Commentaire bloqué");
            alert5.showAndWait();
    
         affichertable();
            }
            
         
  
     
       
    }   catch (SQLException ex) { 
            Logger.getLogger(RefreshController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        
    }
  
     private void affichertable() {
       
     try {
         
         
         CRUDPublication p=new CRUDPublication();
         ArrayList<Publication> cat= p.afficherpub();
         
         ObservableList observableList = FXCollections.observableArrayList(cat);
         table.setItems(observableList);
         publication.setCellValueFactory(new PropertyValueFactory<>("contenu"));
         
         

      
     }
      catch (SQLException ex) {
         Logger.getLogger(ListPostsController.class.getName()).log(Level.SEVERE, null, ex);
     }
     }

   
    
}
