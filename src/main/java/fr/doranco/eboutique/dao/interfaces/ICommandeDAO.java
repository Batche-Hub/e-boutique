/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.doranco.eboutique.dao.interfaces;

import fr.doranco.eboutique.entity.Commande;
import java.util.List;

/**
 *
 * @author Boule
 */
public interface ICommandeDAO {

    public Commande getCommande(Integer idUtilisateur) throws Exception;

    public Commande addCommande(Commande commande, Integer idUtilisateur) throws Exception;

    public List<Commande> getCommandes(Integer idUtilisateur) throws Exception;
}
