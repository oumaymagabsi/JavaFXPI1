/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.javafxpi.entities;

import java.util.Date;



/**
 *
 * @author asus
 */
public class Publication {
     private Integer id;
    private int postid;
    private String contenu;
    private Date datePublication;
    private int IdUser;

    public Publication(String contenu) {
        this.contenu = contenu;
    }

   

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

   

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Date getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(Date datePublication) {
        this.datePublication = datePublication;
    }

    public int getIdUser() {
        return IdUser;
    }

    public void setIdUser(int IdUser) {
        this.IdUser = IdUser;
    }

    public int getPostid() {
        return postid;
    }

    public void setPostid(int postid) {
        this.postid = postid;
    }

    @Override
    public String toString() {
        return "PublicationGroup{" + "id=" + id + ", groupid=" + postid + ", contenu=" + contenu + ", datePublication=" + datePublication + ", IdUser=" + IdUser + '}';
    }

    public Publication(Integer id, String contenu, Date datePublication) {
        this.id = id;
        this.contenu = contenu;
        this.datePublication = datePublication;
    }

    public Publication(Integer id, int postid, String contenu, Date datePublication) {
        this.id = id;
        this.postid = postid;
        this.contenu = contenu;
        this.datePublication = datePublication;
    }

    public Publication() {
    }
    

    
}
