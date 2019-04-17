/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.javafxpi.services;

import DataStorage.myDB;
import edu.javafxpi.entities.Categorie;
import static edu.javafxpi.services.CRUDPost.cnx;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class CRUDCategorie {
      static Connection cnx ;

    public CRUDCategorie() throws SQLException {
         this.cnx =myDB.getInstance().getConnexion();

    }
        public  void supprimerCategorie(int id){
        
        try {
            
            System.out.println(id); 
            PreparedStatement pt=cnx.prepareStatement("Delete from post where categorie_id=?");
            pt.setInt(1,id);
            System.out.println(pt.toString()); 
            pt.executeUpdate();
             pt=cnx.prepareStatement("Delete from categorie where id=?");
            pt.setInt(1,id);
            System.out.println(pt.toString()); 
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CRUDPost.class.getName()).log(Level.SEVERE, null, ex);
        }
} 
     public void ajouterCategorie(Categorie c)
    {
          String query="INSERT INTO categorie (id, categorie)  VALUES ('"+c.getId()+"','"+
                 
                 c.getCategorie()+"')";
           try {
             

               Statement st=cnx.createStatement();
           st.executeUpdate(query);
           }catch (SQLException e)
         {
             System.out.println(e.getMessage());
         }
          
     }
        public ArrayList<Categorie> afficherCategorie(){
          ArrayList<Categorie> list = new ArrayList<>() ;

         try {
            Statement st=cnx.createStatement();
            String req="Select * from categorie";
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
             
              Categorie c= new Categorie(rs.getInt(1),rs.getString(2));
            list.add(c);            
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(CRUDPost
                    .class.getName()).log(Level.SEVERE, null, ex);
        }
       return list ;
         
     }
        public   void modifierCat(Categorie c,int id ){
      
  
        try {
           
            PreparedStatement pt=cnx.prepareStatement("Update categorie set categorie= ?  where id=? ");
           
              
            pt.setString(1, c.getCategorie());
           
         
            pt.setInt(2,id);
            
            pt.executeUpdate();
            //System.out.println(pt);
        } catch (SQLException ex) {
            Logger.getLogger(CRUDPost.class.getName()).log(Level.SEVERE, null, ex);
        }
        }

        public Categorie getCategorieParId(long id){
          Categorie cat = new Categorie() ;

         try {
            Statement st=cnx.createStatement();
            String req="Select * from categorie where id="+id;
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
             
             cat.setId(rs.getInt(1));
             cat.setCategorie(rs.getString(2));
                      
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(CRUDPost
                    .class.getName()).log(Level.SEVERE, null, ex);
        }
       return cat ;
         
     }  
         public Categorie getCategorieParNom(String nom){
          Categorie cat = new Categorie() ;

         try {
            Statement st=cnx.createStatement();
            String req="Select * from categorie where categorie='"+nom+"'";
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
             
             cat.setId(rs.getInt(1));
             cat.setCategorie(rs.getString(2));
                      
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(CRUDPost
                    .class.getName()).log(Level.SEVERE, null, ex);
        }
       return cat ;
         
     }  
      
}
 