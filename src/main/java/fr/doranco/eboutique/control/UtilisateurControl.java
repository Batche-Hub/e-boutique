/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.doranco.eboutique.control;

import fr.doranco.cryptage.des.CryptageDES;
import fr.doranco.eboutique.dao.AdresseDAO;
import fr.doranco.eboutique.dao.UtilisateurDAO;
import fr.doranco.eboutique.entity.Adresse;
import fr.doranco.eboutique.entity.Utilisateur;
import fr.doranco.eboutique.enums.Role;
import fr.doranco.utils.Utils;
import java.io.UnsupportedEncodingException;
import javax.crypto.BadPaddingException;
import javax.crypto.SecretKey;

/**
 *
 * @author Boule
 */
public abstract class UtilisateurControl{

    public UtilisateurControl() {
    }
    public static Utilisateur getUtilisateurByEmail(String email) throws Exception{
        UtilisateurDAO utDAO = new UtilisateurDAO();
        Utilisateur utChecke = new Utilisateur();
        Adresse adresseUt = new Adresse();
        AdresseDAO adresseDAO = new AdresseDAO();
        utChecke = utDAO.getUtilisateurByEmail(email);
        adresseUt = adresseDAO.getAdresseByIdUtilisateur(utChecke.getId());
        utChecke.setAdresse(adresseUt);
        return utChecke;
    }
    
    public static Utilisateur getUtilisateurById(Integer utilisateurId) throws Exception{
       UtilisateurDAO utDAO = new UtilisateurDAO();
        Utilisateur utChecke = new Utilisateur();
        Adresse adresseUt = new Adresse();
        AdresseDAO adresseDAO = new AdresseDAO();
        utChecke = utDAO.getUtilisateurById(utilisateurId);
        adresseUt = adresseDAO.getAdresseByIdUtilisateur(utChecke.getId());
        utChecke.setAdresse(adresseUt);
        return utChecke; 
    }

    public static Utilisateur addUtilisateur(Utilisateur utilisateur, char[] password)throws Exception, BadPaddingException, UnsupportedEncodingException{        
        SecretKey cle = CryptageDES.generateKey();
        
        UtilisateurDAO dao = new UtilisateurDAO();
        AdresseDAO adresseDAO = new AdresseDAO();
        
        Utilisateur utilisateurDOJO = new Utilisateur();
        Adresse adresse = new Adresse();
        
        utilisateurDOJO.setNom(utilisateur.getNom());
        utilisateurDOJO.setPrenom(utilisateur.getPrenom());
        utilisateurDOJO.setAge(utilisateur.getAge());
        utilisateurDOJO.setEmail(utilisateur.getEmail());
        utilisateurDOJO.setTelephone(utilisateur.getTelephone());
        utilisateurDOJO.setRole(Role.CLIENT);
        utilisateurDOJO.setIsActive(true);
        utilisateurDOJO.setIsOnline(false);
        utilisateurDOJO.setPhotoProfil(utilisateur.getPhotoProfil());
        
        String passwordStr = String.valueOf(password);
        
        utilisateurDOJO.setMotDepasse(CryptageDES.encrypter(passwordStr, cle));
        utilisateurDOJO.setCle(Utils.convertToBytes(cle));
        
        adresse.setNumeroRue(utilisateur.getAdresse().getNumeroRue());
        adresse.setNomRue(utilisateur.getAdresse().getNomRue());
        adresse.setNomVille(utilisateur.getAdresse().getNomVille());
        adresse.setCodePostal(utilisateur.getAdresse().getCodePostal());
        
        utilisateurDOJO.setAdresse(adresse);
        
        Utilisateur utilisateurAjoute = dao.addUtilisateur(utilisateurDOJO);
        utilisateurAjoute.setAdresse(utilisateurDOJO.getAdresse());
        
        adresseDAO.addAdresse(utilisateurAjoute.getAdresse(), utilisateurAjoute.getId());

        return utilisateurAjoute;        
}
    
    public static void updateUtilisateur(Utilisateur utilisateur, Adresse adresse) throws Exception{
        UtilisateurDAO utDAO = new UtilisateurDAO();
        
        AdresseDAO adDAO = new AdresseDAO();
        
        Adresse adresseAUpdater = adDAO.getAdresseByIdUtilisateur(utilisateur.getId());
        
        adresse.setId(adresseAUpdater.getId());
        
        adDAO.updateAdresse(adresse);
        
        utDAO.updateTelephoneUtilisateur(utilisateur);
    }
    
    public static void desactiverCompteUtilisateur(Integer connectedUtilisateurId) throws Exception{
        Utilisateur ut = UtilisateurControl.getUtilisateurById(connectedUtilisateurId);
        
        ut.setIsActive(false);
        UtilisateurDAO utDAO = new UtilisateurDAO();        
        utDAO.updateIsActiveUtilisateur(ut);        
    }
    
    public static void reactiverCompteUtilisateur(Integer connectedUtilisateurId) throws Exception{
        Utilisateur ut = UtilisateurControl.getUtilisateurById(connectedUtilisateurId);
        
        ut.setIsActive(true);
        UtilisateurDAO utDAO = new UtilisateurDAO();        
        utDAO.updateIsActiveUtilisateur(ut);
    }

    public static boolean isUtilisateurIsActive(Integer connectedUtilisateurId) throws Exception{
        Utilisateur ut = UtilisateurControl.getUtilisateurById(connectedUtilisateurId);
        if(ut.getIsActive()==true){
            return true;
        }else{
            return false;
        }
    }
    
    public static boolean isEmailExist(String email) throws Exception{
        UtilisateurDAO utDAO = new UtilisateurDAO();
        Utilisateur utChecke = new Utilisateur();
        utChecke = utDAO.getUtilisateurByEmail(email);

        if(utChecke.getEmail() == null){
            return false;
        }else{
            return true;
        }
    }
    
    
}
