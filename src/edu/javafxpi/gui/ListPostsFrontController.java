/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.javafxpi.gui;

import com.jfoenix.controls.JFXComboBox;
import edu.javafxpi.entities.Categorie;
import edu.javafxpi.entities.Post;
import edu.javafxpi.services.CRUDPost;
import edu.javafxpi.gui.ItemController;
import edu.javafxpi.services.CRUDCategorie;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class ListPostsFrontController implements Initializable {

    @FXML
    private BorderPane border_pane;
    @FXML
    private VBox pnl_scroll;

    Pagination pagination;
    @FXML
    private Label lbl_currentprojects;
    @FXML
    private Label lbl_pending;
    @FXML
    private Label lbl_completed;
    @FXML
    private JFXComboBox<String> categorie;
   

    public ListPostsFrontController() throws SQLException {
        this.service = new CRUDPost();
    }

    private void handleButtonAction(MouseEvent event) {
        refreshNodes();
    }

    private void afficherCategorie() {
        try {
            CRUDPost pos = new CRUDPost();
            ArrayList<Categorie> cat = pos.listeCategorie();
            // categorie=new ComboBox();
            for (Categorie c : cat) {

                categorie.getItems().add(c.getCategorie());
            }
            // categorie.setValue();
        } catch (SQLException ex) {

        }
    }
CRUDPost service;
    CRUDCategorie service1 =new CRUDCategorie();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            Parent sidebar = FXMLLoader.load(getClass().getResource("/edu/javafxpi/gui/SidebarFront.fxml"));
            border_pane.setLeft(sidebar);
        } catch (IOException ex) {

        }
        afficherCategorie();
categorie.setOnAction(e->{

refreshNodes();
}
);
        

    }
   List<Post> myList;
    Node[] nodes;

    private void refreshNodes() {

        pnl_scroll.getChildren().clear();
        myList = new ArrayList<>();
        CRUDPost postCrud = null;
        try {
            postCrud = new CRUDPost();
        } catch (SQLException ex) {
            Logger.getLogger(ListPostsFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
      myList = postCrud.getPostParIdCategorie(service1.getCategorieParNom(categorie.getValue()).getId());
        nodes = new Node[myList.size()];

        try {
            for (int i = 0; i < myList.size(); i++) {
                System.out.println(myList.get(i));
                Categorie c = new Categorie();
                c = postCrud.getCategorieParId(myList.get(i).getCategorie().getId());
                nodes[i] = (Node) FXMLLoader.load(getClass().getResource("/edu/javafxpi/gui/item.fxml"));
                Label tb = (Label) nodes[i].lookup("#titre");
                Label description = (Label) nodes[i].lookup("#description");
                Label dayDeb = (Label) nodes[i].lookup("#dayDeb");
                Label realisateur = (Label) nodes[i].lookup("#realisateur");
                Label monthDeb = (Label) nodes[i].lookup("#monthDeb");

                Label yearDeb = (Label) nodes[i].lookup("#yearDeb");

                ImageView image = (ImageView) nodes[i].lookup("#image");

                tb.setText(myList.get(i).getTitle());
                description.setText(myList.get(i).getDescription());
                String datedeb = myList.get(i).getDatecreation().toString();
                dayDeb.setText(datedeb.substring(8, 10));
                monthDeb.setText(getMonthForInt(myList.get(i).getDatecreation().getMonthValue()));
                yearDeb.setText(datedeb.substring(0, 4));
                realisateur.setText(myList.get(i).getRealisateur());
                //Image im = new Image(myList.get(i).getImage());

                System.out.println(myList.get(i).getImage());
                // image= myList.get(i).getImage();
                //image.setImage(new Image(myList.get(i).getImage().toString()));
                image.setImage(myList.get(i).getImage().getImage());

                // pnl_scroll.getChildren().add(nodes[i]);
                int xxx=myList.get(i).getId();
                image.setOnMousePressed(s->{
                    Stage stage=new Stage();
                    Post.setPost_courant(xxx);
           Parent root;
                    try {
                        root = FXMLLoader.load(getClass().getResource("/edu/javafxpi/gui/commentaire.fxml"));
                        Scene scene = new Scene(root);
        stage.setScene(scene);
        

        stage.show();;
                    
                    } catch (IOException ex) {
                        System.out.println("404");                    }

        
                
                
                });
            }

        } catch (IOException e) {

        }
        System.out.println(myList.size());
        pagination = new Pagination();
        pagination.setPageCount((int) Math.ceil((double) myList.size() / itemsPerPage()));
        //pagination.setPageCount(3);
        System.out.println((int) Math.ceil((double) myList.size() / itemsPerPage()));
        pagination.setStyle("-fx-border-color:blue");

        pagination.setPageFactory(new Callback<Integer, Node>() {
            @Override
            public Node call(Integer pageIndex) {
                return createPage(pageIndex);
            }

        });

        pnl_scroll.getChildren().add(pagination);
        

    }

    public static String getMonthForInt(int month) {
        String[] monthNames = {"JAN", "FEB", "MAR", "APR", "MAY", "JUNE", "JULY", "AUG", "SEP", "OCR", "NOV", "DEC"};
        return monthNames[month];
    }

    public int itemsPerPage() {
        return 2;
    }

    public VBox createPage(int pageIndex) {
        VBox box = new VBox(5);
        int page = pageIndex * itemsPerPage();

        int to = page + itemsPerPage();
        int to1 = Math.min(page + itemsPerPage(), myList.size());
        for (int i = page; i < to1; i++) {
            VBox element = new VBox();
            Hyperlink link = new Hyperlink("Item " + myList.get(i));
            link.setVisited(true);
            Label text = new Label("Search results\nfor " + link);
            element.getChildren().addAll(nodes[i]);
            box.getChildren().add(element);
        }
        return box;
    }

}
