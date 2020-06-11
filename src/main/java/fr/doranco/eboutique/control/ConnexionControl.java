/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.doranco.eboutique.control;

import fr.doranco.cryptage.des.CryptageDES;
import fr.doranco.eboutique.dao.UtilisateurDAO;
import fr.doranco.eboutique.entity.Utilisateur;
import fr.doranco.utils.Utils;
import javax.crypto.SecretKey;

/**
 *
 * @author Boule
 */
public abstract class ConnexionControl {

    private ConnexionControl() {
    }

    public static Utilisateur seConnecter(String email, String password )throws Exception{
        UtilisateurDAO utDAO = new UtilisateurDAO();
        Utilisateur utConnecte = null;
        
        Utilisateur utilisateurDOJO = utDAO.getUtilisateurByEmail(email);
        
        String passwordDAO = CryptageDES.decrypter(utilisateurDOJO.getMotDepasse(), (SecretKey)Utils.convertFromBytes(utilisateurDOJO.getCle()));
        
        if(password.equals(passwordDAO)){
            utConnecte = new Utilisateur();
            utConnecte.setNom(utilisateurDOJO.getNom());
            utConnecte.setPrenom(utilisateurDOJO.getPrenom());
            utConnecte.setAge(utilisateurDOJO.getAge());
            utConnecte.setId(utilisateurDOJO.getId());
            utConnecte.setEmail(utilisateurDOJO.getEmail());
            utConnecte.setTelephone(utilisateurDOJO.getTelephone());
            utConnecte.setIsActive(utilisateurDOJO.getIsActive());
            utConnecte.setIsOnline(true);
            return utConnecte;
        }else{
            utConnecte = null;
            return utConnecte;
        }
    }
    
    public static void seDeconnecter(Integer idUtilisateur) throws Exception{
        UtilisateurDAO utDAO = new UtilisateurDAO();        
        Utilisateur utilisateurDOJO = utDAO.getUtilisateurById(idUtilisateur);
        utilisateurDOJO.setIsOnline(false);
    }
}
