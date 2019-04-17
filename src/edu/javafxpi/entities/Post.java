/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.javafxpi.entities;

import java.time.LocalDate;
import javafx.scene.image.ImageView;

/**
 *
 * @author Lenovo
 */
public class Post {
    //a3mlhom hawka 3andek string wint wdate  k tkmwl  le9 olcil é etrangére man5alihomch 
    //nn3adi bech nktbouhom lkol houni mefmech foreignkey fel java 7aseb me9alouli eli 3amlou projeyet java 9alouli semi lkol kifkif wte5rj 
    //deja ena 3amla hekkeka wccategorie_idv yefhem xDDD
    // behi ani bech ne5dem
    private static int post_courant;

    public static int getPost_courant() {
        return post_courant;
    }

    public static void setPost_courant(int post_courant) {
        Post.post_courant = post_courant;
    }
    private int id;
    private LocalDate datecreation;
    private String title;
    private Categorie categorie;
    private String contenu;

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
    
    private String description;
    private String realisateur;
    private ImageView image;

    public Post(int id, LocalDate datecreation, String title, Categorie categorie, String description, String realisateur, ImageView image,String contenu) {
        this.id = id;
        this.datecreation = datecreation;
        this.title = title;
        this.categorie = new Categorie();
        this.categorie = categorie;

        this.description = description;
        this.realisateur = realisateur;
        this.image = image;
        this.contenu=contenu;
 
    }
   public Post( LocalDate datecreation, String title,  Categorie categorie, String description, String realisateur, ImageView image,String contenu) {
        
        this.datecreation = datecreation;
        this.title = title;
        this.categorie = categorie;
       
        this.description = description;
        this.realisateur = realisateur;
        this.image = image;
        this.contenu=contenu;
    }

    public Post() {
    }

  
   public Post(int id, LocalDate datecreation, String title, Categorie categorie, String description, String realisateur,String contenu) {
        this.id = id;
        this.datecreation = datecreation;
        this.title = title;
        this.categorie = new Categorie();
        this.categorie = categorie;

        this.description = description;
        this.realisateur = realisateur;
        this.contenu=contenu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDatecreation() {
        return datecreation;
    }

    public void setDatecreation(LocalDate datecreation) {
        this.datecreation = datecreation;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRealisateur() {
        return realisateur;
    }

    public void setRealisateur(String realisateur) {
        this.realisateur = realisateur;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }
     
}

