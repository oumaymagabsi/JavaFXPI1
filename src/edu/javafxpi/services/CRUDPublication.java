/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.javafxpi.services;

import DataStorage.myDB;
import edu.javafxpi.entities.Post;
import edu.javafxpi.entities.Publication;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
/**
 *
 * @author asus
 */
public class CRUDPublication {
    Connection con;

    public CRUDPublication() throws SQLException {
        this.con = myDB.getInstance().getConnexion();
    }
    public List<Publication> getPublicationByUser(Post u) {
        String req = "SELECT * FROM publication where postid=? order by datePublication desc";
        ResultSet rs= null;
        try {
            PreparedStatement ps = con.prepareStatement(req);
            ps.setInt(1, u.getId());
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Publication.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<Publication> p = new ArrayList<>();
        try {
            while (rs.next()){
                Post user = new Post();
                user.setId(rs.getInt("postid"));
                p.add(new Publication(rs.getInt("id"), user.getId(), rs.getString("contenu"), rs.getTimestamp("datePublication")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CRUDPublication.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }
      public  void supprimerPublicaion(int id) throws SQLException{
         PreparedStatement pt=null;
        try {
          pt =con.prepareStatement("Delete from publication where id=?");
            pt.setInt(1,id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CRUDPost.class.getName()).log(Level.SEVERE, null, ex);
        }
        pt.close();
} 
       
        public ArrayList<Publication> afficherpub(){
          ArrayList<Publication> list = new ArrayList<>() ;

         try {
            Statement st=con.createStatement();
            String req="Select * from publication order by datePublication desc";
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
             
              Publication n= new Publication(rs.getString(3));
            list.add(n);            
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(CRUDPost
                    .class.getName()).log(Level.SEVERE, null, ex);
        }
       return list ;
         
     }
   
  
    public void ajouterPublication(Publication p) {
        Calendar c = Calendar.getInstance();
        Timestamp ts = new Timestamp(c.getTimeInMillis());
        try {
            String req = "INSERT INTO publication (postid,contenu, datePublication,IdUser) VALUES (?,?,?,?)";
            PreparedStatement pre = con.prepareStatement(req);
            pre.setInt(1,Post.getPost_courant());
             pre.setString(2, p.getContenu());
            pre.setTimestamp(3, ts);
            pre.setInt(4, 2);
          //  pre.setInt(4, getUserId);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CRUDPublication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public void modifierPublication(Publication p) {
        Calendar c = Calendar.getInstance();
        Timestamp ts = new Timestamp(c.getTimeInMillis());
        try {
            String req = "update publication set contenu=?, datePublication=? where id=? ";
            PreparedStatement pre = con.prepareStatement(req);
            pre.setString(1, p.getContenu());
            
            pre.setTimestamp(2, ts);
            
            pre.setInt(3, p.getId());
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CRUDPublication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    public void supprimerPublication(Publication p) {
        try {
            String req = "delete from publication where id=? ";
            PreparedStatement pre = con.prepareStatement(req);
            pre.setInt(1, p.getId());
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CRUDPublication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public Publication getPublicationById(int id) throws SQLException {
        String req = "SELECT * FROM publication where id=?";
        ResultSet rs= null;
        try {
            PreparedStatement ps = con.prepareStatement(req);
            ps.setInt(1, id);
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(CRUDPublication.class.getName()).log(Level.SEVERE, null, ex);
        }
        Publication p = new Publication();
        while (rs.next()){
            Post user = new Post();
            p.setId(rs.getInt("id"));
            p.setContenu(rs.getString("contenu"));
            p.setDatePublication(rs.getTimestamp("datePublication"));
            p.setPostid(user.getId());
            p.setIdUser(1);
            
        }
        return p;
    }


    
}
