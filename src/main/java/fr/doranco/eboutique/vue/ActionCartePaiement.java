/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.doranco.eboutique.vue;

import fr.doranco.eboutique.control.CarteControl;
import fr.doranco.eboutique.control.UtilisateurControl;
import fr.doranco.eboutique.entity.CartePaiement;
import fr.doranco.eboutique.entity.Utilisateur;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Boule
 */
public abstract class ActionCartePaiement {
   
    
    
    
    public static void ajouterCarterPaiement(String dateValidite, String numeroCarte,String cryptogramme, Integer connectedUserId) throws Exception{
            Utilisateur ut = UtilisateurControl.getUtilisateurById(connectedUserId);

            CartePaiement carte = new CartePaiement();

            carte.setDateValidite(dateValidite);
            carte.setNumeroCarte(numeroCarte);
            carte.setCryptograme(cryptogramme);

            CarteControl.addCartePaiement(carte, ut.getId());
    }
    
    public static String[] tableauCartePaiement(Integer connectedUserId) throws Exception{
    List<CartePaiement> listCarte = CarteControl.getListeCartesPaiement(connectedUserId);
        String[] strings = new String[listCarte.size()];

        for (int i = 0; i < listCarte.size(); i++) {
            Integer n = i + 1;

            strings[i] = "Carte " + n.toString() + " : | Date Validité : " + listCarte.get(i).getDateValidite() + " | Numéro de carte : " + listCarte.get(i).getNumeroCarte().substring(0, listCarte.get(i).getNumeroCarte().length()-12)+"************" + " | Cryptogramme : " + listCarte.get(i).getCryptograme().substring(0, listCarte.get(i).getCryptograme().length()-3)+"***";
        }
        return strings;
    }
    
    public static List<Integer> listeIdCartePaiement(Integer connectedUserId) throws Exception{
        List<CartePaiement> listCarte = CarteControl.getListeCartesPaiement(connectedUserId);

        Integer[] carteId = new Integer[listCarte.size()];

        for (int i = 0; i < listCarte.size(); i++) {
            carteId[i] = listCarte.get(i).getId();
        }
        return Arrays.asList(carteId);
    }
    
}
