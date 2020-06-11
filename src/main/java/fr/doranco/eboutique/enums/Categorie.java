/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.doranco.eboutique.enums;

/**
 *
 * @author Boule
 */
public enum Categorie {
   ELECTRONIQUE("Electronique"), TELEPHONIE("Telephonie"), DECORATION("Décoration");
   
   private String description;

    private Categorie(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static Categorie getELECTRONIQUE() {
        return ELECTRONIQUE;
    }

    public static Categorie getTELEPHONIE() {
        return TELEPHONIE;
    }

    public static Categorie getDECORATION() {
        return DECORATION;
    }
   
   
}
