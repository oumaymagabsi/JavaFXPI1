/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.javafxpi.services;

import DataStorage.myDB;
import edu.javafxpi.entities.Newsletter;

import java.sql.Connection;
import java.sql.Date;
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
public class CRUDNewsletter {
    static Connection cnx ;

    public CRUDNewsletter() throws SQLException {
        this.cnx =myDB.getInstance().getConnexion();
    }
      public  void supprimerNewsletter(int id){
        
        try {
            PreparedStatement pt=cnx.prepareStatement("Delete from newsletter where id=?");
            pt.setInt(1,id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CRUDPost.class.getName()).log(Level.SEVERE, null, ex);
        }
} 
     public void ajouterNewsletter(Newsletter n)
    {
          String query="INSERT INTO newsletter(id, sujet, dateCreation, titre, contenu, lienDeAbonnement) VALUES ('"+n.getId()+"','"+
                 
                 n.getSujet()+"','"+n.getDatecreation()+"','"+n.getTitre()+"','"+n.getContenu()+"','"+n.getLien()+"')";
           try {
             

               Statement st=cnx.createStatement();
           st.executeUpdate(query);
           }catch (SQLException e)
         {
             System.out.println(e.getMessage());
         }
          
     }
      public   void modifierNewsletter(Newsletter n,int id ){
      
  
        try {
           
            PreparedStatement pt=cnx.prepareStatement("Update newsletter set sujet= ?, dateCreation= ?,titre= ?, contenu= ? ,lienDeAbonnement= ? where id=? ");
           
              
            pt.setString(1, n.getSujet());
              
               
          pt.setDate(2, Date.valueOf(n.getDatecreation()));
           pt.setString(3, n.getTitre());
            pt.setString(4, n.getContenu());
             pt.setString(5, n.getLien());
            pt.setInt(6,id);
            
            pt.executeUpdate();
            //System.out.println(pt);
        } catch (SQLException ex) {
            Logger.getLogger(CRUDPost.class.getName()).log(Level.SEVERE, null, ex);
        }
        

          
      
}
       
        public ArrayList<Newsletter> afficherNewsletter(){
          ArrayList<Newsletter> list = new ArrayList<>() ;

         try {
            Statement st=cnx.createStatement();
            String req="Select * from newsletter";
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
             
              Newsletter n= new Newsletter(rs.getInt(1),rs.getString(2),rs.getDate(3).toLocalDate(),rs.getString(4),rs.getString(5),rs.getString(6));
            list.add(n);            
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(CRUDPost
                    .class.getName()).log(Level.SEVERE, null, ex);
        }
       return list ;
         
     }
           
        public Newsletter Newsletterbyid( int id){
         // ArrayList<Newsletter> list = new ArrayList<>() ;
         Newsletter n=null;
         try {
            Statement st=cnx.createStatement();
            String req="Select * from newsletter where id="+id;
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
             
               n= new Newsletter(rs.getInt(1),rs.getString(2),rs.getDate(3).toLocalDate(),rs.getString(4),rs.getString(5),rs.getString(6));
           // list.add(n);            
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(CRUDPost
                    .class.getName()).log(Level.SEVERE, null, ex);
        }
       return n ;
         
     }
    
}
