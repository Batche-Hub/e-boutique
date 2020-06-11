/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.doranco.eboutique.entity;

import fr.doranco.eboutique.entity.*;

/**
 *
 * @author Boule
 */
public class Adresse {
    private Integer id;
    private Integer numeroRue;
    private String nomRue;
    private String nomVille;
    private Integer codePostal;

    public Adresse() {
    }

    public Adresse(Integer numeroRue, String nomRue, String nomVille, Integer codePostal) {
        this.numeroRue = numeroRue;
        this.nomRue = nomRue;
        this.nomVille = nomVille;
        this.codePostal = codePostal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumeroRue() {
        return numeroRue;
    }

    public void setNumeroRue(Integer numeroRue) {
        this.numeroRue = numeroRue;
    }

    public String getNomRue() {
        return nomRue;
    }

    public void setNomRue(String nomRue) {
        this.nomRue = nomRue;
    }

    public String getNomVille() {
        return nomVille;
    }

    public void setNomVille(String nomVille) {
        this.nomVille = nomVille;
    }

    public Integer getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(Integer codePostal) {
        this.codePostal = codePostal;
    }

    @Override
    public String toString() {
        return "Adresse{" + "id=" + id + ", numeroRue=" + numeroRue + ", nomRue=" + nomRue + ", nomVille=" + nomVille + ", codePostal=" + codePostal + '}';
    }
    
    
}
