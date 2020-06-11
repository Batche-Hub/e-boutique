/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.doranco.eboutique.dao.interfaces;

import fr.doranco.eboutique.entity.LigneCommande;
import java.util.List;

/**
 *
 * @author Boule
 */
public interface ILigneCommandeDAO {

    public List<LigneCommande> getLignesCommande(Integer idCommande) throws Exception;

    public LigneCommande addLigneCommande(LigneCommande ligneCommande, Integer idCommande) throws Exception;
}
