/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.javafxpi.gui;

import edu.javafxpi.entities.Personne;
import edu.javafxpi.entities.Post;
import static edu.javafxpi.entities.Post.getPost_courant;
import edu.javafxpi.entities.Publication;
import edu.javafxpi.services.CRUDPost;
import edu.javafxpi.services.CRUDPublication;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.http.auth.UsernamePasswordCredentials;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class CommentaireController implements Initializable {

    @FXML
    private ImageView p_image;
    @FXML
    private HBox bouttonFB;
    @FXML
    private Label nom_p;
    @FXML
    private Button signalerProfil;
    
    @FXML
    private Text date2;
    @FXML
    private TextField reclamaer;
    @FXML
    private Button reclamation;
    @FXML
    private VBox vboxStatus;
    @FXML
    private MenuItem modifierStatus;
    @FXML
    private MenuItem supprimerStatus;
    /**
     * Initializes the controller class.
     */
    CRUDPost postCrud;
    int postcourant;
      public Post EvenementCourant = new Post();
      CRUDPublication ser=new CRUDPublication();
    @FXML
    private Label p_contenu;
            

    public CommentaireController() throws SQLException {
        this.postCrud = new CRUDPost();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
            // TODO
      
          
        
        postcourant=Post.getPost_courant();
     
        Post postecourant1=postCrud.getPostParId(postcourant);
       
       p_image.setImage(postecourant1.getImage().getImage());
     
        
 List<Publication> pulications = ser.getPublicationByUser(postecourant1);
        vboxStatus.getChildren().clear();
        for (Publication p : pulications) {
            vboxStatus.getChildren().add(publicationItem(postCrud.getAcualiteById(postcourant), p));
        }

        
       
         
        //System.out.println("88888888"+postecourant1.getTitle());
               System.out.println("kkk"  + postecourant1.getContenu());

       p_contenu.setText(postecourant1.getContenu());
       date2.setText(String.valueOf(postecourant1.getDatecreation()));
         nom_p.setText(postecourant1.getTitle());
        
    }    

    @FXML
    private void signalerProfilAction(ActionEvent event) {}
    private AnchorPane publicationItem(Post user, Publication publication) {
        Font prefFont = new Font("System Bold", 12);
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefSize(435, 128);
       /* ImageView userImage = new ImageView(getClass().getResource("/Images/" + sd.getUserById(getUserId()).getImage()).toExternalForm());

        userImage.setFitWidth(60);
        userImage.setFitHeight(60);
        userImage.setPickOnBounds(true);
        userImage.setPreserveRatio(true);
        userImage.setClip(new Circle(30, 30, 30));*/
      Label userName = new Label(user.getTitle());
        userName.setLayoutX(62);
        userName.setLayoutY(3);
        userName.setFont(prefFont);

        SimpleDateFormat formater = new SimpleDateFormat("EEEE, dd-MM-yyyy HH:mm");
        Label pubDate = new Label(formater.format(publication.getDatePublication()));
        pubDate.setLayoutX(63);
        pubDate.setLayoutY(20);
        pubDate.setFont(prefFont);
        Text contenu = new Text(publication.getContenu());
        contenu.setLayoutX(13);
        contenu.setLayoutY(70);
        contenu.setStrokeType(StrokeType.OUTSIDE);
        contenu.setStrokeWidth(0);
        contenu.setWrappingWidth(350);
        MenuButton menuButton = new MenuButton("Action");
        menuButton.setLayoutX(358);
        menuButton.setLayoutY(9);
        menuButton.setMnemonicParsing(false);
        MenuItem updateItem = new MenuItem("Modifier");
        updateItem.setMnemonicParsing(false);

        updateItem.setId(publication.getId().toString());
        updateItem.setOnAction(this::modifierPublicationAction);

        MenuItem deleteItem = new MenuItem("Supprimer");
        deleteItem.setMnemonicParsing(false);

        deleteItem.setId(publication.getId().toString());
        deleteItem.setOnAction(this::supprimerPublicationAction);

        menuButton.getItems().addAll(updateItem, deleteItem);
        anchorPane.getChildren().addAll(userName, pubDate, contenu, menuButton);
        VBox.setMargin(anchorPane, new Insets(0, 0, 30, 0));
        return anchorPane;
    }

    @FXML
    private void voidReclamation(ActionEvent event) {

       Publication p = new Publication(reclamaer.getText());
        ser.ajouterPublication(p);
        //---
        
        postcourant=Post.getPost_courant();
     
        Post postecourant1=postCrud.getPostParId(postcourant);
        List<Publication> pulications = ser.getPublicationByUser(postecourant1);
        vboxStatus.getChildren().clear();
        for (Publication pu : pulications) {
            vboxStatus.getChildren().add(publicationItem(postCrud.getAcualiteById(postcourant), pu));
        }
   
    }

    @FXML
    private void supprimerPublicationAction(ActionEvent event)  {
        MenuItem x = (MenuItem) event.getSource();
        Publication p = new Publication();
        p.setId(Integer.parseInt(x.getId()));
        ser.supprimerPublication(p);
        //-------
        try {
            //---
            sleep(1500);
        } catch (InterruptedException ex) {
            Logger.getLogger(CommentaireController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //---
        postcourant=Post.getPost_courant();
     
        Post postecourant1=postCrud.getPostParId(postcourant);
        List<Publication> pulications = ser.getPublicationByUser(postecourant1);
        vboxStatus.getChildren().clear();
        for (Publication pu : pulications) {
            vboxStatus.getChildren().add(publicationItem(postCrud.getAcualiteById(postcourant), pu));
        }

    }

    @FXML
    private void modifierPublicationAction(ActionEvent event){
        MenuItem x = (MenuItem) event.getSource();
        Publication p = new Publication();
        try {
            p = ser.getPublicationById(Integer.parseInt(x.getId()));
        } catch (SQLException ex) {
            Logger.getLogger(CommentaireController.class.getName()).log(Level.SEVERE, null, ex);
        }
        p.setPostid(p.getPostid());
        //System.out.println(p);
        //------------------
        TextInputDialog dialog = new TextInputDialog(p.getContenu());
        dialog.setTitle("Modifier publication");
        //dialog.setContentText("Please enter your name:");
        dialog.setHeaderText(null);
        dialog.setGraphic(null);
        dialog.initStyle(StageStyle.UNDECORATED);
        dialog.getDialogPane().setBackground(new Background(new BackgroundFill(Color.GREY, new CornerRadii(2), new Insets(2))));
        dialog.getDialogPane().setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        //dialog.getDialogPane().setStyle("-fx-border-color: black");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextArea contenuModifie = new TextArea();
        contenuModifie.setPrefWidth(300);
        contenuModifie.setPrefHeight(100);
        contenuModifie.setText(p.getContenu());

        grid.add(new Label("Contenu: "), 0, 0);
        grid.add(contenuModifie, 1, 0);

        dialog.getDialogPane().setContent(grid);
        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            //System.out.println(contenuModifie.getText());
            p.setContenu(contenuModifie.getText());
            ser.modifierPublication(p);
              postcourant=Post.getPost_courant();
     
        Post postecourant1=postCrud.getPostParId(postcourant);
        List<Publication> pulications = ser.getPublicationByUser(postecourant1);
        vboxStatus.getChildren().clear();
        for (Publication pu : pulications) {
            vboxStatus.getChildren().add(publicationItem(postCrud.getAcualiteById(postcourant), pu));
        }
            

        }
    }

    
}
