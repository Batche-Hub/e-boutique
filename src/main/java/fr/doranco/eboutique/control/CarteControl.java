/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.doranco.eboutique.control;

import fr.doranco.eboutique.dao.CartePaiementDAO;
import fr.doranco.eboutique.entity.CartePaiement;
import java.util.List;

/**
 *
 * @author Boule
 */
public abstract class CarteControl {
    
    
    
    public static void addCartePaiement(CartePaiement cartePaiement, Integer idUtilisateur) throws Exception{
        CartePaiementDAO carteDAO = new CartePaiementDAO();        
        carteDAO.addCartePaiement(cartePaiement, idUtilisateur);   
    }
    
    public static List<CartePaiement> getListeCartesPaiement(Integer idUtilisateur) throws Exception{
        CartePaiementDAO carteDAO = new CartePaiementDAO();
        return carteDAO.getCartesPaiement(idUtilisateur);
    }
    
    public static void supprimerCartePaiement(Integer idCarte) throws Exception{
        CartePaiementDAO carteDAO = new CartePaiementDAO();
        carteDAO.removeCartePaiement(idCarte);
    }
}
