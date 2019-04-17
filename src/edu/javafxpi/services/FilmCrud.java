/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.javafxpi.services;

import edu.javafxpi.entities.Film;
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
public class FilmCrud {

    public List<Film> afficherFilm() {
        List<Film> myList = new ArrayList<>();
        String requete = "SElECT * from film";
        try {
            Statement st = MyConnection.getInstance().getCnx().createStatement();

            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Film f = new Film();
                f.setId(rs.getInt("id"));
                f.setTitre(rs.getString("titre"));
                f.setDescription(rs.getString("description"));
                f.setDateSortie(rs.getDate("dateSortie"));
                f.setAffiche(rs.getString("affiche"));
                f.setDuree(rs.getDouble("duree"));
                f.setNbPlaces(rs.getInt("nbPlances"));
                f.setPrix(rs.getDouble("prix"));
                myList.add(f);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return myList;

    }

    public Film getFilm(int id) {
        Film f = new Film();
       
        String requete = "Select * FROM film  where id="+id+"";
        try {
           // PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            Statement st= MyConnection.getInstance().getCnx().createStatement();
           
            ResultSet  rs = st.executeQuery(requete);
            rs.next();
            f.setId(rs.getInt("id"));
            f.setTitre(rs.getString("titre"));
            f.setDescription(rs.getString("description"));
            f.setDateSortie(rs.getDate("dateSortie"));
            f.setAffiche(rs.getString("affiche"));
            f.setDuree(rs.getDouble("duree"));
            f.setNbPlaces(rs.getInt("nbPlances"));
            f.setPrix(rs.getDouble("prix"));
              
        } catch (SQLException ex) {
        }
        return f;
    }
}
