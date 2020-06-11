/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.doranco.eboutique.dao.interfaces;
import fr.doranco.eboutique.entity.Utilisateur;


/**
 *
 * @author Boule
 */
public interface IUtilisateurDAO {    
    public Utilisateur getUtilisateurByEmail(String email) throws Exception;
    public Utilisateur addUtilisateur(Utilisateur utilisateur) throws Exception;
    public void updateTelephoneUtilisateur(Utilisateur utilisateur) throws Exception;
    public void updateIsActiveUtilisateur(Utilisateur utilisateur) throws Exception;
//    public void updateUtilisateur(Utilisateur utlisateur) throws Exception;
//    public void removeUtilisateur(Integer id) throws Exception;
//    public List<Utilisateur> getListeUtilisateur() throws Exception;
}
