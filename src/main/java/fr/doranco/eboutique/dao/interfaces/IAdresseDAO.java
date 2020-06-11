/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.doranco.eboutique.dao.interfaces;

import fr.doranco.eboutique.entity.Adresse;


/**
 *
 * @author Boule
 */
public interface IAdresseDAO {

    public Adresse getAdresseByIdUtilisateur(Integer idUtilisateur) throws Exception;

    public void addAdresse(Adresse adresse, Integer idUtilisateur) throws Exception;
    
    public void updateAdresse(Adresse adresse) throws Exception;

    public void removeAdresse(Integer idUtilisateur)throws Exception;
}
