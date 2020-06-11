/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.doranco.eboutique.dao;

import fr.doranco.connexion.SecuriteDataSource;
import fr.doranco.eboutique.dao.interfaces.ICommandeDAO;
import fr.doranco.eboutique.entity.Commande;
import fr.doranco.eboutique.entity.Utilisateur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Boule
 */
public class CommandeDAO implements ICommandeDAO{

    public CommandeDAO() {
    }

    
    @Override
    public Commande getCommande(Integer idCommande) throws Exception {
        Commande commande = null;
        Connection connexion = SecuriteDataSource.getInstance().getConnection();
   
        PreparedStatement ps = null;
        try {
            String requete = "SELECT * FROM commande WHERE id = ?";
            ps = connexion.prepareStatement(requete);
            ps.setInt(1, idCommande);
                        
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                commande = new Commande();
                commande.setId(rs.getInt("id"));
                commande.setDateCreation(rs.getString("date_creation"));
                commande.setDateLivraison(rs.getString("date_livraison")); 
                commande.setPrixTotal(rs.getFloat("prix_total"));
            }
        } catch (Exception e) {
            System.out.println("Une erreur est survenue : " + e);
            return null;
        }       
        finally {
            if (connexion != null) {
                try {
                    connexion.close();
                } catch (SQLException ex) {
                    System.err.println("Une erreur Sql est survenue : " + ex);                   
                }
            }
        }
        return commande;
    }

    @Override
    public Commande addCommande(Commande commande, Integer idUtilisateur) throws Exception {
        Utilisateur utilisateurDOJO = null;
        Commande commandeAjoutee = null;
        List<Commande> listeCommande = new ArrayList<>();
        Connection connexion = SecuriteDataSource.getInstance().getConnection();
   
        PreparedStatement ps = null;
        try {
            String requete = "INSERT INTO commande(date_creation, date_livraison, prix_total, utilisateur_commande_id) VALUE(?,?,?,?)";
            ps = connexion.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, commande.getDateCreation());
            ps.setString(2, commande.getDateLivraison());
            ps.setFloat(3, commande.getPrixTotal());
            ps.setInt(4, idUtilisateur);
            ps.executeUpdate();

            ResultSet resultSet = ps.getGeneratedKeys();
            commandeAjoutee = commande;
            if (resultSet.next()) {
                utilisateurDOJO = new Utilisateur();
                Integer idCommande = resultSet.getInt(1);
                commandeAjoutee.setId(idCommande);
                listeCommande.add(commande);
                utilisateurDOJO.setCommandes(listeCommande);
            }
        } catch (Exception e) {
            System.out.println("Une erreur est survenue : " + e);
        }       
        finally {
            if (connexion != null) {
                try {
                    connexion.close();
                } catch (SQLException ex) {
                    System.err.println("Une erreur Sql est survenue : " + ex);                   
                }
            }
        }
        return commandeAjoutee;
    }

    @Override
    public List<Commande> getCommandes(Integer idUtilisateur) throws Exception {
        List<Commande> listeCommandes = new ArrayList<>();

        Connection connexion = SecuriteDataSource.getInstance().getConnection();

        String requete = "SELECT * FROM commande WHERE utilisateur_commande_id = ?";
        PreparedStatement ps = null;
        try {
            ps = connexion.prepareStatement(requete);
            ps.setInt(1, idUtilisateur);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Commande commande = new Commande();

                commande.setId(rs.getInt("id"));
                commande.setDateCreation(rs.getString("date_creation"));
                commande.setDateLivraison(rs.getString("date_livraison")); 
                commande.setPrixTotal(rs.getFloat("prix_total"));
                
                listeCommandes.add(commande);

            }

        } catch (SQLException e) {
            System.err.println("Une erreur SQL est survenue : " + e);
        } finally {
            if (connexion != null) {
                try {
                    connexion.close();
                } catch (SQLException ex) {
                    System.err.println("Erreur de connexion SQL : " + ex);
                }
            }
        }

        return listeCommandes;
    }
    
}
