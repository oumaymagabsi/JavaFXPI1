/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.javafxpi.entities;

/**
 *
 * @author User
 */
public class Categorie {
      private String nomcategorie;
    private int id;

    public Categorie() {
    }

    public Categorie( int id,String categorie) {
        this.nomcategorie = categorie;
        this.id = id;
    }
       public Categorie( String categorie) {
        this.nomcategorie = categorie;
      
    }

    public String getCategorie() {
        return nomcategorie;
    }

    public void setCategorie(String categorie) {
        this.nomcategorie = categorie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return nomcategorie;
    }
     
}
