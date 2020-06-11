/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.doranco.eboutique.vue;

import fr.doranco.eboutique.control.UtilisateurControl;
import fr.doranco.eboutique.entity.Adresse;
import fr.doranco.eboutique.entity.Utilisateur;
import fr.doranco.utils.Utils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.maven.shared.utils.io.FileUtils;

/**
 *
 * @author Boule
 */
public abstract class UtilisateurAction {

    private static final File USERPIC_DIRECTORY = new File(System.getProperty("user.home") + "\\AppData\\LocalLow\\E-boutique\\UserPictures\\");

    public static void creerCompte(String nom, String prenom, String age, String numeroRue, String rue, String ville, String codePostal, String email, String telephone, char[] password, String pathPhotoProfil) throws IOException, Exception {
        Utilisateur ut = new Utilisateur();
        Adresse adresse = new Adresse();
        adresse.setNumeroRue(Integer.parseInt(numeroRue));
        adresse.setNomRue(rue);
        adresse.setNomVille(ville);
        adresse.setCodePostal(Integer.parseInt(codePostal));

        ut.setNom(nom);
        ut.setPrenom(prenom);
        ut.setAge(Integer.parseInt(age));
        ut.setEmail(email);
        ut.setTelephone(telephone);
        ut.setAdresse(adresse);
        ut.setPhotoProfil(pathPhotoProfil);
        ut.setMotDepasse(Utils.convertToBytes(password));
        UtilisateurControl.addUtilisateur(ut, password);

    }

    public static void editerInformationsCompte(Integer connectedUtilisateurId, String numeroRue, String rue, String ville, String codePostal, String telephone) throws Exception {
        Utilisateur ut = UtilisateurControl.getUtilisateurById(connectedUtilisateurId);
        Adresse ad = new Adresse();

        ad.setNumeroRue(Integer.parseInt(numeroRue));
        ad.setNomRue(rue);
        ad.setNomVille(ville);
        ad.setCodePostal(Integer.parseInt(codePostal));
        ut.setTelephone(telephone);

        UtilisateurControl.updateUtilisateur(ut, ad);
    }

    public static void desactiverCompteUtilisateur(Integer connectedUtilisateurId) throws Exception {
        UtilisateurControl.desactiverCompteUtilisateur(connectedUtilisateurId);
    }

    public static void activerCompteUtilisateur(Integer connectedUtilisateurId) throws Exception {
        UtilisateurControl.reactiverCompteUtilisateur(connectedUtilisateurId);
    }

    public static boolean isUtilisateurIsActive(Integer connectedUtilisateurId) throws Exception {
        return UtilisateurControl.isUtilisateurIsActive(connectedUtilisateurId);
    }

    public static File createUserProfilPicture(String pathSelectedPicture) throws FileNotFoundException, IOException {
        File selectedPicture = new File(pathSelectedPicture);
        
        FileUtils.copyFileToDirectory(selectedPicture, USERPIC_DIRECTORY);
                    
        return new File (USERPIC_DIRECTORY.getAbsolutePath()+"\\"+selectedPicture.getName());
     
    }

    public static File getUserProfilPicture(String pathOfUserProfilPicture){
        return new File(pathOfUserProfilPicture);
    }

}
