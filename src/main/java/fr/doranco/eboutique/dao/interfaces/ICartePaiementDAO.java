/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.doranco.eboutique.dao.interfaces;

import fr.doranco.eboutique.entity.CartePaiement;
import java.util.List;

/**
 *
 * @author Boule
 */
public interface ICartePaiementDAO {

    public CartePaiement getCartePaiement(Integer idUtilisateur) throws Exception;

    public List<CartePaiement> getCartesPaiement(Integer idUtilisateur) throws Exception;

    public void addCartePaiement(CartePaiement cartePaiement, Integer idUtilisateur) throws Exception;
    
    public void updateCartePaiment(CartePaiement cartePaiement) throws Exception;

    public void removeCartePaiement(Integer id) throws Exception;
}
