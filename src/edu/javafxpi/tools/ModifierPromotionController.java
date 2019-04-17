/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.javafxpi.tools;

import edu.javafxpi.entities.Film;
import edu.javafxpi.entities.Promotion;
import edu.javafxpi.services.FilmCrud;
import edu.javafxpi.services.PromotionCrud;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class ModifierPromotionController implements Initializable {

     private Promotion selectedPromotion;
    
   boolean flag = true;
    @FXML
    BorderPane border_pane;
    @FXML
    DatePicker datedeb;
    @FXML
    DatePicker datefin;
    @FXML
    TextField pourcentage;
    @FXML
    ComboBox<Film>film ;
    @FXML
    Button valider;
     /**
     * This method accepts a person to initialize the view
     * @param p 
     */
    public void initData(Promotion p)
    {
        selectedPromotion = p;
        pourcentage.setText(Double.toString(p.getPourcentage()));
        datedeb.setValue(p.getDateDeb().toLocalDate() );
        datefin.setValue(p.getDateFin().toLocalDate() );
         FilmCrud filmCrud=new FilmCrud();
         ArrayList l = (ArrayList) filmCrud.afficherFilm();
       
         StringConverter<Film> converter = new StringConverter<Film>() {
    @Override
    public String toString(Film object) {
        return object.getTitre();
    }

    @Override
    public Film fromString(String string) {
        return null;
    }
};
          film.setConverter(converter);
          film.setItems(FXCollections.observableList(l));
          film.getSelectionModel().select(filmCrud.getFilm(p.getProm_film()));
         
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
     private void modifier(ActionEvent event) throws IOException {
        Date date1 = java.sql.Date.valueOf(datedeb.getValue());
        Date date2 = java.sql.Date.valueOf(datefin.getValue());
        Film f = (Film) film.getSelectionModel().getSelectedItem();
        Promotion p=new Promotion(date1,date2,Double.valueOf(pourcentage.getText()),f.getId());
        PromotionCrud promotionCrud=new PromotionCrud();
        System.out.println(p.getPourcentage());
        promotionCrud.modifierPromotion(p, selectedPromotion.getId());
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/edu/javafxpi/views/ListPromotions.fxml"));
        Parent root;
        root=loader.load();
        valider.getScene().setRoot(root);
        
        new Alert(Alert.AlertType.INFORMATION,"Modification effectuée avec succées" ).show();
    }
}
