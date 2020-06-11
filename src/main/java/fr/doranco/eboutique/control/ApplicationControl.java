/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.doranco.eboutique.control;

import java.io.File;

/**
 *
 * @author Boule
 */
public abstract class ApplicationControl {

    private static final File BASE_DIRECTORY = new File(System.getProperty("user.home") + "\\AppData\\LocalLow");

    public static void makeDirectories() {
        File eboutique = new File(BASE_DIRECTORY, "E-boutique");
        File userProfil = new File(eboutique, "UserPictures");
        File productPict = new File(eboutique, "ProductPictures");
        if (eboutique.exists() == false) {
            eboutique.mkdir();
        }
        if (userProfil.exists() == false) {
            userProfil.mkdir();
        }
        if (productPict.exists() == false) {
            productPict.mkdir();
        }
    }
}
