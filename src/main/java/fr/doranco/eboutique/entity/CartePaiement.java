/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.doranco.eboutique.entity;


/**
 *
 * @author Boule
 */
public class CartePaiement {
   private Integer id;
   private String dateValidite;
   private String cryptograme;
   private String numeroCarte;
   
    public CartePaiement() {
    }

    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDateValidite() {
        return dateValidite;
    }

    public void setDateValidite(String dateValidite) {
        this.dateValidite = dateValidite;
    }

    public String getCryptograme() {
        return cryptograme;
    }

    public void setCryptograme(String cryptograme) {
        this.cryptograme = cryptograme;
    }

    public String getNumeroCarte() {
        return numeroCarte;
    }

    public void setNumeroCarte(String numeroCarte) {
        this.numeroCarte = numeroCarte;
    }

    @Override
    public String toString() {
        return "CartePaiement{" + "id=" + id + ", dateValidite=" + dateValidite + ", cryptograme=" + cryptograme + ", numeroCarte=" + numeroCarte + '}';
    }

}
