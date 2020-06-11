/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.doranco.eboutique.vue;

import fr.doranco.eboutique.control.ConnexionControl;
import fr.doranco.eboutique.control.UtilisateurControl;
import fr.doranco.eboutique.entity.Utilisateur;

/**
 *
 * @author Boule
 */
public abstract class ActionConnexion {
    
    public static int seConnecter(String email, char[]password ) throws Exception {
         if (ConnexionControl.seConnecter(email, String.valueOf(password)) != null) {
                System.err.println("avant création");
                Utilisateur ut = UtilisateurControl.getUtilisateurByEmail(email);
                
                return ut.getId();
                
            }else{
            System.err.println("Coucou");
            return 0;
        }
        
    }

    public static void seDeconnecter(Integer userConnectedId) throws Exception {
        ConnexionControl.seDeconnecter(userConnectedId);
        
    }
    
    public static Boolean isEmailExist(String email) throws Exception{
        return UtilisateurControl.isEmailExist(email);
    }
    
}
