package fr.doranco.eboutique.enums;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Boule
 */
public enum Role {
    CLIENT("Client"), MAGASINIER("Magasinier"), ADMIN("Administrateur");

    private String description;

    private Role(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static Role getCLIENT() {
        return CLIENT;
    }

    public static Role getMAGASINIER() {
        return MAGASINIER;
    }

    public static Role getADMIN() {
        return ADMIN;
    }
    
    


}
