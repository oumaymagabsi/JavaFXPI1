/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.javafxpi.entities;

import java.time.LocalDate;

/**
 *
 * @author User
 */
public class Newsletter {

    public Newsletter() {
    }

   
    private String sujet;
    private String titre;
    private String contenu;
    private String lien;
    private LocalDate datecreation;
    private int id;
   
 public Newsletter(String sujet, String titre, String contenu, String lien, LocalDate datecreation) {
        this.sujet = sujet;
        this.titre = titre;
        this.contenu = contenu;
        this.lien = lien;
        this.datecreation = datecreation;
    }

    public Newsletter(  int id,String sujet, LocalDate datecreation, String titre, String contenu, String lien) {
        this.sujet = sujet;
        this.titre = titre;
        this.contenu = contenu;
        this.lien = lien;
        this.datecreation = datecreation;
        this.id = id;
    }
    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getLien() {
        return lien;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }

    public LocalDate getDatecreation() {
        return datecreation;
    }

    public void setDatecreation(LocalDate datecreation) {
        this.datecreation = datecreation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
}
