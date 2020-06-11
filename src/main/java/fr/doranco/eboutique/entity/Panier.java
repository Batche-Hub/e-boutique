/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.doranco.eboutique.entity;


import java.util.Map;

/**
 *
 * @author Boule
 */
public class Panier {
    private Integer id;
    private Map<Produit , Integer> produitAcommander;

    public Panier(Map<Produit, Integer> produitAcommander) {
        this.produitAcommander = produitAcommander;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Map<Produit, Integer> getProduitAcommander() {
        return produitAcommander;
    }

    public void setProduitAcommander(Map<Produit, Integer> produitAcommander) {
        this.produitAcommander = produitAcommander;
    }
    
    
}
