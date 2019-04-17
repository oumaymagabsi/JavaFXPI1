/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.javafxpi.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import controller.ProduitsFXMLController;
import static controller.ProduitsFXMLController.nombreproduits;
import edu.javafxpi.entities.Post;
import edu.javafxpi.services.CRUDPost;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ListPostsFront1Controller implements Initializable {
    CRUDPost service;
    Post p = new Post();
    @FXML
    private BorderPane border_pane;
    @FXML
    private Label lbl_currentprojects;
    @FXML
    private Label lbl_pending;
    @FXML
    private Label lbl_completed;
    @FXML
    private JFXListView<Pane> ListView_Produits;

    public ListPostsFront1Controller() throws SQLException {
        this.service = new CRUDPost();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Parent sidebar = FXMLLoader.load(getClass().getResource("/edu/javafxpi/gui/SidebarFront.fxml"));
             border_pane.setLeft(sidebar);
        } catch (IOException ex) {
          
        }
   ListView_Produits.setFocusTraversable(false);
           getShowPane();
      

        if (nombreproduits >= 1) {
            nombreproduits--;
            ListeActualites();
        }

        // TODO
    }    
     public void ListeActualites() {
        ObservableList<Pane> refresh = FXCollections.observableArrayList();

    }

    public void getShowPane() {

        List<Post> AllProducts = new ArrayList();
        
            for (Post p : service.afficherPost()) {
                AllProducts.add(p);
            }
       

        
        int i = 0;
        int j = 0;
        ObservableList<Pane> Panes = FXCollections.observableArrayList();

        List<Post> ThreeProducts = new ArrayList();
        for (Post p : AllProducts) {
            if (i == 0) {
                ThreeProducts.add(p);
                i++;
                j++;

                if (j == AllProducts.size()) {
                    Panes.add(AddPane(ThreeProducts));

                    ThreeProducts.clear();
                }

            } else {
                ThreeProducts.add(p);
                i++;
                j++;
                if ((i % 3 == 0) || (j == AllProducts.size())) {
                    Panes.add(AddPane(ThreeProducts));

                    ThreeProducts.clear();

                }
            }
        }

        ObservableList<Pane> refresh = FXCollections.observableArrayList();
        ListView_Produits.setItems(refresh);
        ListView_Produits.setItems(Panes);
    }

    public Pane AddPane(List<Post> ThreeProduct) {
        Pane pane = new Pane();
        pane.setStyle(" -fx-background-color: white");
        int k = 1;
        for (Post p3 : ThreeProduct) {
            if (k == 1) {
                Pane pane2 = new Pane();
                pane2.setLayoutX(25);
                pane2.setLayoutY(50);
                pane2.setPrefWidth(pane2.getWidth() + 215);
                pane2.setPrefHeight(pane2.getHeight() + 200);

                //pane2.setStyle("-fx-background-radius: 50;");
                pane2.setStyle(" -fx-border-radius: 10;-fx-border-color: #ff4f00 ;-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 8, 0, 0, 0); ");
                JFXButton t = new JFXButton("Détails");
//                        JFXButton t1=new JFXButton("acheter");    
                t.setStyle("-fx-font-weight: bold;");
//                        t1.setStyle("-fx-font-weight: bold;");

                HBox hb = new HBox(t);
//                        HBox hb2=new HBox(t1);

                hb.setLayoutX(0);
                hb.setLayoutY(170);
                hb.setPrefWidth(hb.getWidth() + 215);
                hb.setPrefHeight(hb.getHeight() + 35);
                hb.setStyle("-fx-background-color: #ff4f00; -fx-background-radius: 0 0 10 10;");

//                                hb2.setLayoutX(155);
//                                hb2.setLayoutY(170);
//                                hb2.setPrefWidth(hb.getWidth() + 61); 
//                                hb2.setPrefHeight(hb.getHeight() + 35);
//                                hb2.setStyle("-fx-background-color: #ea7066; ; -fx-background-radius: 0 0 10 0;");
                pane2.getChildren().addAll(hb);

                

               
                
               // Image image2 = p.getImage().getImage();

            /*    ImageView image = p.getImage();
                image.setFitWidth(140);
                image.setFitHeight(130);
                image.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 8, 0, 0, 0);");

                //image.setImage(image2);
                image.setLayoutX(40);
                image.setLayoutY(-45);
                pane2.getChildren().add(image);*/

                Text nomt = new Text("Nom : ");
                Label nom = new Label(p3.getTitle());
                Text prixt = new Text("Description : ");
                Label prix = new Label(p3.getDescription());
                nomt.setLayoutX(50);
                nomt.setLayoutY(160);
                nom.setLayoutX(100);
                nom.setLayoutY(145);
                prixt.setLayoutX(50);
                prixt.setLayoutY(180);
                prix.setLayoutX(125);
                prix.setLayoutY(165);
                nomt.setStyle("-fx-font-weight: bold;-fx-fill : #ff4f00");
                prixt.setStyle("-fx-font-weight: bold;-fx-fill : #ff4f00");

                t.setOnMouseClicked((MouseEvent event) -> {
                  /* Groups.setGroupe_courant(p3.getId());

                   loadView("/GUI/ProfilGroupe.fxml");*/
                });
                pane.getChildren().addAll(pane2, nomt, prixt, nom, prix);
            }          
            if (k == 2) {
                Pane pane2 = new Pane();
                pane2.setLayoutX(300);
                pane2.setLayoutY(50);
                pane2.setPrefWidth(pane2.getWidth() + 215);
                pane2.setPrefHeight(pane2.getHeight() + 200);
                //pane2.setStyle("-fx-background-radius: 50;");
                pane2.setStyle(" -fx-border-radius: 10 ;-fx-border-color: #ff4f00 ;-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 8, 0, 0, 0); ");

                JFXButton t = new JFXButton("Détails");
//                        JFXButton t1=new JFXButton("acheter");    
                t.setStyle("-fx-font-weight: bold;");
//                        t1.setStyle("-fx-font-weight: bold;");
                HBox hb = new HBox(t);
//                        HBox hb2=new HBox(t1);

                hb.setLayoutX(0);
                hb.setLayoutY(170);
                hb.setPrefWidth(hb.getWidth() + 215);
                hb.setPrefHeight(hb.getHeight() + 35);
                hb.setStyle("-fx-background-color: #ff4f00; -fx-background-radius: 0 0 10 10;");

//                                hb2.setLayoutX(155);
//                                hb2.setLayoutY(170);
//                                hb2.setPrefWidth(hb.getWidth() + 61); 
//                                hb2.setPrefHeight(hb.getHeight() + 35);
//                                hb2.setStyle("-fx-background-color: #ea7066; ; -fx-background-radius: 0 0 10 0;");
                pane2.getChildren().addAll(hb);

               

             
               // Image image2 = p.getImage().getImage();
/*
                ImageView image = p.getImage();
                image.setFitWidth(140);
                image.setFitHeight(130);
                image.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 8, 0, 0, 0);");

              //  image.setImage(image2);
                image.setLayoutX(40);
                image.setLayoutY(-45);
                pane2.getChildren().add(image);*/

                Text nomt = new Text("Nom : ");
                Label nom = new Label(p3.getTitle());
                Text prixt = new Text("Description : ");
                Label prix = new Label(p3.getDescription());
                nomt.setLayoutX(325);
                nomt.setLayoutY(160);
                nom.setLayoutX(375);
                nom.setLayoutY(145);
                prixt.setLayoutX(325);
                prixt.setLayoutY(180);
                prix.setLayoutX(400);
                prix.setLayoutY(165);
                nomt.setStyle("-fx-font-weight: bold;-fx-fill : #ff4f00");
                prixt.setStyle("-fx-font-weight: bold;-fx-fill : #ff4f00");

                t.setOnMouseClicked((MouseEvent event) -> {
                   /* Groups.setGroupe_courant(p3.getId());

                    loadView("/GUI/ProfilGroupe.fxml");*/
                });
                pane.getChildren().addAll(pane2, nomt, prixt, nom, prix);
            }

            if (k == 3) {
                Pane pane2 = new Pane();
                pane2.setLayoutX(575);
                pane2.setLayoutY(50);
                pane2.setPrefWidth(pane2.getWidth() + 215);
                pane2.setPrefHeight(pane2.getHeight() + 200);
                //pane2.setStyle("-fx-background-radius: 50;");
                pane2.setStyle(" -fx-border-radius: 10;-fx-border-color: #ff4f00 ;-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 8, 0, 0, 0); ");

                JFXButton t = new JFXButton("Détails");
                t.setStyle("-fx-font-weight: bold;");
                HBox hb = new HBox(t);

                hb.setLayoutX(0);
                hb.setLayoutY(170);
                hb.setPrefWidth(hb.getWidth() + 215);
                hb.setPrefHeight(hb.getHeight() + 35);
                hb.setStyle("-fx-background-color: #ff4f00; -fx-background-radius: 0 0 10 10;");
                pane2.getChildren().addAll(hb);

               
                //Image image2 = p.getImage().getImage();
/*
                ImageView image = p.getImage();
                image.setFitWidth(140);
                image.setFitHeight(130);
                image.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 8, 0, 0, 0);");

               // image.setImage(image2);
                image.setLayoutX(40);
                image.setLayoutY(-45);
                pane2.getChildren().add(image);*/
                Text nomt = new Text("Nom : ");
                Label nom = new Label(p3.getTitle());
                Text prixt = new Text("Description : ");
                Label prix = new Label(p3.getDescription());
                nomt.setLayoutX(600);
                nomt.setLayoutY(160);
                nom.setLayoutX(650);
                nom.setLayoutY(145);
                prixt.setLayoutX(600);
                prixt.setLayoutY(180);
                prix.setLayoutX(675);
                prix.setLayoutY(165);
                nomt.setStyle("-fx-font-weight: bold;-fx-fill : #ff4f00");
                prixt.setStyle("-fx-font-weight: bold;-fx-fill : #ff4f00");

                t.setOnMouseClicked((MouseEvent event) -> {
                  /*  Groups.setGroupe_courant(p3.getId());
                  loadView("/GUI/ProfilGroupe.fxml");*/
                  Parent root;
                    try {
                        
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/javafxpi/gui/commentaire.fxml"));

                        root = loader.load();
                                    t.getScene().setRoot(root);

                    } catch (IOException ex) {
                        Logger.getLogger(ListPostsFrontController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                pane.getChildren().addAll(pane2, nomt, prixt, nom, prix);
 
            }
            k++;

        }
        return pane;
    }
 
}
