/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.javafxpi.services;

import DataStorage.myDB;
import edu.javafxpi.entities.Abonne;
import edu.javafxpi.entities.Categorie;
import static edu.javafxpi.services.CRUDCategorie.cnx;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author asus
 */
public class CRUDAbonne {
      static Connection cnx ;

    public CRUDAbonne() throws SQLException {
         this.cnx =myDB.getInstance().getConnexion();

    }
       public void ajouter(Abonne c)
    {
          String query="INSERT INTO abonne (id, email)  VALUES ('"+c.getId()+"','"+
                 
                 c.getEmail()+"')";
           try {
             

               Statement st=cnx.createStatement();
           st.executeUpdate(query);
           }catch (SQLException e)
         {
             System.out.println(e.getMessage());
         }
          
     }
    
}
