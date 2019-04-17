/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.javafxpi.services;

import edu.javafxpi.entities.Promotion;
import edu.javafxpi.tools.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class PromotionCrud {
       public void ajouterPromotion (Promotion p) {
        String requete ="INSERT INTO promotion (dateDeb,dateFin,Pourcentage,active,Prom_film) VALUES"+"('"+p.getDateDeb()+"','"+p.getDateFin()+"','"+p.getPourcentage()+"','"+p.getActive()+"','"+p.getProm_film()+"')";
        try {
            Statement st =  MyConnection.getInstance().getCnx().createStatement();
            st.executeUpdate(requete);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     public void supprimerPromotion(int id ){
        String requete="DELETE FROM promotion  where id=?";
        try {
            PreparedStatement pst=  MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1,id);
            pst.executeUpdate();
            System.out.println("Promotion supprimée !");
        } catch (SQLException ex) {
        }
        
    }
    
      public List<Promotion> afficherPromotion(){
         List<Promotion> myList= new ArrayList<>();
         String requete="SElECT * from promotion";
         try {
             Statement st= MyConnection.getInstance().getCnx().createStatement();
             
             ResultSet rs = st.executeQuery(requete);
             while(rs.next()){
                 Promotion p = new Promotion();
                 p.setId(rs.getInt("id"));
                 p.setDateDeb(rs.getDate("dateDeb"));
                 p.setDateFin(rs.getDate("dateFin"));
                 p.setPourcentage(rs.getDouble("pourcentage"));
                 p.setActive(rs.getInt("active"));
                 p.setProm_film(rs.getInt("Prom_film"));
                 myList.add(p);
                 
             }
         } catch (SQLException ex) {
             System.out.println(ex.getMessage());
         }
         
         return myList;
         
     }
      
       public void modifierPromotion(Promotion p,int id ){
        String requete="UPDATE promotion SET dateDeb=?,dateFin=?,Pourcentage=?,Prom_film=? where id=?";
        try {
            PreparedStatement pst=  MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setDate(1, p.getDateDeb());
            pst.setDate(2, p.getDateFin());
            pst.setDouble(3,p.getPourcentage());
            pst.setInt(4,p.getProm_film());
            pst.setInt(5,id);
            pst.executeUpdate();
        } catch (SQLException ex) {
        }
        
    }
}
