/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.doranco.eboutique.entity;

import fr.doranco.eboutique.enums.Categorie;

/**
 *
 * @author Boule
 */
public class Produit {
    private Integer id;
    private String nom;
    private String description;
    private Float prix;
    private Integer remise;
    private Categorie categorie;

    public Produit() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public Integer getRemise() {
        return remise;
    }

    public void setRemise(Integer remise) {
        this.remise = remise;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie catagorie) {
        this.categorie = catagorie;
    }
    
    
    
}
