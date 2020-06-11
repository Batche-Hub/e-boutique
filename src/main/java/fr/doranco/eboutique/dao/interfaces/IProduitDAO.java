/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.doranco.eboutique.dao.interfaces;

import fr.doranco.eboutique.entity.Produit;
import java.util.List;

/**
 *
 * @author Boule
 */
public interface IProduitDAO {

    public Produit getProduit(Integer id) throws Exception;

    public List<Produit> getProduitsByCategorie(String categorie) throws Exception;

    public Produit addProduit(Produit produit) throws Exception;

    public void updateProduit(Produit produit) throws Exception;

    public void removeProduit(Integer id) throws Exception;
}
