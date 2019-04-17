/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.javafxpi.services;

import DataStorage.myDB;
import edu.javafxpi.entities.Categorie;
import edu.javafxpi.entities.Post;
import edu.javafxpi.tools.MyConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author User
 */
public class CRUDPost {
    static Connection cnx ;

    public CRUDPost() throws SQLException {
        this.cnx =myDB.getInstance().getConnexion();
    }
    

    public void ajouterPost(Post p)
    {
          String query="INSERT INTO post (id, categorie_id, title, datecreation, description, realisateur, image, contenu)  VALUES ('"+p.getId()+"','"+p.getCategorie().getId()+"','"+p.getTitle()+"','"+p.getDatecreation()+"','"
                  +p.getDescription()+"','"+
                  p.getRealisateur()+"','"+
                  p.getImage().getImage().impl_getUrl()+"','"+
                  p.getContenu()+"')";
           try {
             

               Statement st=cnx.createStatement();
           st.executeUpdate(query);
           }catch (SQLException e)
         {
             System.out.println(e.getMessage());
         }
          
     }
        public ArrayList<Post> afficherPost(){
          ArrayList<Post> list = new ArrayList<>() ;

         try {
            Statement st=cnx.createStatement();
            String req="Select * from post";
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
             Categorie c=getCategorieParId(rs.getInt(2));
             System.out.println(rs.getString(4));
               Image im=new Image (rs.getString("image"));
                ImageView mv = new ImageView(im);
                        mv.setFitWidth(70);
                        mv.setFitHeight(90);
              Post p = new Post(rs.getInt("id"),rs.getDate("datecreation").toLocalDate(),rs.getString("title")
                      ,c,rs.getString("description"),rs.getString("realisateur"),mv,rs.getString("contenu"));
              /*  Post p = new Post(rs.getInt("id"),rs.getDate("datecreation").toLocalDate(),rs.getString("title")
                      ,c,rs.getString("description"),rs.getString("realisateur"),rs.getString("contenu"));*/
           
            list.add(p);            
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(CRUDPost
                    .class.getName()).log(Level.SEVERE, null, ex);
        }
       return list ;
         
     }
    public  void supprimerPost(int id){
        
        try {
            PreparedStatement pt=cnx.prepareStatement("Delete from post where id=? ");
            pt.setInt(1, id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CRUDPost.class.getName()).log(Level.SEVERE, null, ex);
        }
} 
    public   void modifierPost(Post p,int id ){
      
      Date  Date1;
        try {
           
            PreparedStatement pt=cnx.prepareStatement("Update post  set datecreation= ?, title= ? , categorie_id= ? , description= ? ,realisateur= ? ,image= ? ,contenu= ?  where id=? ");
             pt.setDate(1, Date.valueOf(p.getDatecreation()));
              pt.setString(2, p.getTitle());
            pt.setInt(3, p.getCategorie().getId());
          pt.setString(4, p.getDescription());
          
           pt.setString(5, p.getRealisateur());
             pt.setString(6, p.getImage().getImage().impl_getUrl());
              
            pt.setString(7, p.getContenu());
           
         
            pt.setInt(8,id);
            
            pt.executeUpdate();
            //System.out.println(pt);
        } catch (SQLException ex) {
            Logger.getLogger(CRUDPost.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    
      
    
    
   public ArrayList<Categorie> listeCategorie1(){
          ArrayList<Categorie> list = new ArrayList<>() ;

         try {
            Statement st=cnx.createStatement();
            String req="Select * from categorie ";
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
             Categorie c=new Categorie();
             c.setId(rs.getInt(1));
             c.setCategorie(rs.getString(2));
            list.add(c);            
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(CRUDPost
                    .class.getName()).log(Level.SEVERE, null, ex);
        }
       return list ;
         
     }  
    public ArrayList<Categorie> listeCategorie(){
          ArrayList<Categorie> list = new ArrayList<>() ;
           

         try {
            Statement st=cnx.createStatement();
            String req="SELECT DISTINCT c.categorie FROM   categorie c, post p\n" +
"WHERE  c.id= p.categorie_id";
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
             Categorie c=new Categorie();
             c.setCategorie(rs.getString(1));
            list.add(c);            
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(CRUDPost
                    .class.getName()).log(Level.SEVERE, null, ex);
        }
       return list ;
         
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
        
       public List<Post> getPostParIdCategorie(long id){
          List<Post> po=new ArrayList<Post>();

         try {
            Statement st=cnx.createStatement();
            String req="Select * from post where categorie_id="+id;
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                
             
             Categorie c=getCategorieParId(id);
             System.out.println(rs.getString(4));
               Image im=new Image (rs.getString("image"));
                ImageView mv = new ImageView(im);
                        mv.setFitWidth(70);
                        mv.setFitHeight(90);
              Post p = new Post(rs.getInt("id"),rs.getDate("datecreation").toLocalDate(),rs.getString("title")
                      ,c,rs.getString("description"),rs.getString("realisateur"),mv,rs.getString("contenu"));
              /*  Post p = new Post(rs.getInt("id"),rs.getDate("datecreation").toLocalDate(),rs.getString("title")
                      ,c,rs.getString("description"),rs.getString("realisateur"),rs.getString("contenu"));*/
           
            po.add(p);    
                      
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(CRUDPost
                    .class.getName()).log(Level.SEVERE, null, ex);
        }
       return po ;
         
     }  
       public Post getPostParId(int id){
         
Post p=new Post();
         try {
            Statement st=cnx.createStatement();
            String req="Select * from post where id="+id;
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                
             
             Categorie c=getCategorieParId(rs.getInt(2));
           //  System.out.println(rs.getString(4));
               Image im=new Image (rs.getString("image"));
                ImageView mv = new ImageView(im);
                        mv.setFitWidth(70);
                        mv.setFitHeight(90);
               p = new Post(rs.getInt("id"),rs.getDate("datecreation").toLocalDate(),rs.getString("title")
                      ,c,rs.getString("description"),rs.getString("realisateur"),mv,rs.getString("contenu"));
              /*  Post p = new Post(rs.getInt("id"),rs.getDate("datecreation").toLocalDate(),rs.getString("title")
                      ,c,rs.getString("description"),rs.getString("realisateur"),rs.getString("contenu"));*/
           
              return p;
                     
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(CRUDPost
                    .class.getName()).log(Level.SEVERE, null, ex);
        }
       return p ;
         
     } 
       
       public  ObservableList<Post> Search(String tf){
        ObservableList myList = FXCollections.observableArrayList();
        
        try {
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            String str = "SELECT * FROM post WHERE id LIKE '%"+tf+"%'"
                        +"UNION SELECT * FROM post WHERE title LIKE '%"+tf+"%'"
                        +"UNION SELECT * FROM post WHERE datecreation LIKE '%"+tf+"%'"
                        +"UNION SELECT * FROM post WHERE description LIKE'%"+tf+"%'"
                        +"UNION SELECT * FROM post WHERE realisateur LIKE'%"+tf+"%'"
                        +"UNION SELECT * FROM post WHERE contenu LIKE '%"+tf+"%'"
                       ;
           
            ResultSet rs = st.executeQuery(str);
             Categorie c=getCategorieParId(rs.getInt(2));
              Image im=new Image (rs.getString("image"));
                ImageView mv = new ImageView(im);
                        mv.setFitWidth(70);
                        mv.setFitHeight(90);
            while(rs.next()){
            myList.add(new Post(rs.getInt("id"),rs.getDate("datecreation").toLocalDate(),rs.getString("title")
                      ,c,rs.getString("description"),rs.getString("realisateur"),mv,rs.getString("contenu")));
            }    
        } catch (SQLException ex) {
            
            System.out.println("erreur lors de l'affichage " + ex.getMessage());
        }      
        return myList;
    }
 
       
    public Post getAcualiteById(int idCourant) {
Post pr = new Post();
       
        try {
            String query = "select * from post where id = ?";
            PreparedStatement ps;

            ps = cnx.prepareStatement(query);
            ps.setInt(1, idCourant);
            ResultSet rest = ps.executeQuery();

            while (rest.next()) {
                pr.setId(rest.getInt("id"));
                pr.setTitle(rest.getString("title"));
                pr.setDescription(rest.getString("description"));
                pr.setDatecreation(rest.getDate("datecreation").toLocalDate());
                pr.setCategorie(getCategorieParId(rest.getInt("categorie_id")));
                 pr.setRealisateur(rest.getString("realisateur"));
                 pr.setContenu(rest.getNString("contenu"));
                 Image im=new Image (rest.getString("image"));
                ImageView mv = new ImageView(im);
                        mv.setFitWidth(70);
                        mv.setFitHeight(90);
                        pr.setImage(mv);

            }
           
return pr;
        } catch (SQLException ex) {
            System.out.println("404");        }
  
        return pr;

    }

}
