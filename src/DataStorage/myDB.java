/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStorage;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class myDB {
    
    
      Connection connexion;
    final  String url = "jdbc:mysql://localhost:3306/projetpi";
    final  String user = "root";
    final  String password = "";
    private static myDB instance=null;
    
    private myDB() {
        
          try {
              connexion = (Connection) DriverManager.getConnection(url, user, password);
          } catch (SQLException ex) {
              Logger.getLogger(myDB.class.getName()).log(Level.SEVERE, null, ex);
          }
            System.out.println("\nConnexion établie");
        
    }
    
    public static myDB getInstance() throws SQLException{
        if( instance == null)
            instance = new myDB();
        
        return instance;
    }
    
    public Connection getConnexion() {
        return connexion;
    }
    
}
