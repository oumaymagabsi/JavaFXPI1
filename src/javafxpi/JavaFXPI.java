/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxpi;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Lenovo
 */
public class JavaFXPI extends Application {
    public static Stage stage = null;
    @Override
    public void start(Stage stage) throws Exception {
      Parent root = FXMLLoader.load(getClass().getResource("/edu/javafxpi/gui/Main.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        this.stage = stage;

        stage.show();;
    }

   
   
    public static void main(String[] args) {
        launch(args);
         
    }
    
}
