/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.javafxpi.entities;

import java.sql.Date;

/**
 *
 * @author Lenovo
 */
public class Promotion {
    private int id;
    private Date dateDeb;
    private Date dateFin;
    private double pourcentage;
    private int active=1;
    private Film film;
    private int Prom_film;

    public Promotion(int id, Date date1, Date date2, Double valueOf, int id0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getProm_film() {
        return Prom_film;
    }

    public void setProm_film(int Prom_film) {
        this.Prom_film = Prom_film;
    }

    public Promotion( Date dateDeb, Date dateFin, double pourcentage, Film film) {
       
        this.dateDeb = dateDeb;
        this.dateFin = dateFin;
        this.pourcentage = pourcentage;
        this.film = film;
    }

    public Promotion( Date dateDeb, Date dateFin, double pourcentage, int Prom_film) {
        
        this.dateDeb = dateDeb;
        this.dateFin = dateFin;
        this.pourcentage = pourcentage;
        this.Prom_film = Prom_film;
    }

    public Promotion() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateDeb() {
        return dateDeb;
    }

    public void setDateDeb(Date dateDeb) {
        this.dateDeb = dateDeb;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public double getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(double pourcentage) {
        this.pourcentage = pourcentage;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    @Override
    public String toString() {
        return "Promotion{" + "id=" + id + ", dateDeb=" + dateDeb + ", dateFin=" + dateFin + ", pourcentage=" + pourcentage + ", active=" + active + ", film=" + film + '}';
    }
    
}
