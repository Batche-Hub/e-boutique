/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.doranco.eboutique.dao;

import fr.doranco.connexion.SecuriteDataSource;
import fr.doranco.eboutique.dao.interfaces.ILigneCommandeDAO;
import fr.doranco.eboutique.entity.LigneCommande;
import fr.doranco.eboutique.entity.Produit;
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
public class LigneCommandeDAO implements ILigneCommandeDAO{

    public LigneCommandeDAO() {
    }

    
    @Override
    public List<LigneCommande> getLignesCommande(Integer idCommande) throws Exception {
        List<LigneCommande> listeLigneCommandes = new ArrayList<>();

        Connection connexion = SecuriteDataSource.getInstance().getConnection();

        String requete = "SELECT ligneC.quantite quantite_produit, p.* FROM produit p INNER JOIN ligne_commande ligneC ON ligneC.produit_ligne_commande_id = p.id WHERE ligneC.ommande_ligne_commande_id = ?";
        PreparedStatement ps = null;
        try {
            ps = connexion.prepareStatement(requete);
            ps.setInt(1, idCommande);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Produit produit = new Produit();
                LigneCommande ligneCommande = new LigneCommande();

                produit.setNom(rs.getString("p.nom"));
                produit.setDescription(rs.getString("p.description"));
                produit.setPrix(rs.getFloat("p.prix"));
                produit.setRemise(rs.getInt("p.remise"));
                produit.getCategorie().setDescription("p.categorie");//Si erreur sur description c'est sûrement ici. "p.categorie.description"
                ligneCommande.setQuantite(rs.getInt("quantite_produit"));
                
                ligneCommande.setProduit(produit);
                
                listeLigneCommandes.add(ligneCommande);

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

        return listeLigneCommandes; 
    }

    @Override
    public LigneCommande addLigneCommande(LigneCommande ligneCommande, Integer idCommande) throws Exception {
        LigneCommande ligneCommandeAjoutee = null;
        Connection connexion = SecuriteDataSource.getInstance().getConnection();

        PreparedStatement ps = null;
        try {
            String requete = "INSERT INTO article(produit_ligne_commande_id, quantite_produit, commande_ligne_commande_id) VALUE(?,?,?)";
            ps = connexion.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, ligneCommande.getProduit().getId());
            ps.setInt(2, ligneCommande.getQuantite());
            ps.setInt(3, idCommande);

            ps.executeUpdate();

            ResultSet resultSet = ps.getGeneratedKeys();
            ligneCommandeAjoutee = ligneCommande;
            if (resultSet.next()) {                
                Integer idLigneCommande = resultSet.getInt(1);
                ligneCommandeAjoutee.setId(idLigneCommande);
            }
        } catch (Exception e) {
            System.out.println("Une erreur est survenue : " + e);
        } finally {
            if (connexion != null) {
                try {
                    connexion.close();
                } catch (SQLException ex) {
                    System.err.println("Une erreur Sql est survenue : " + ex);
                }
            }
        }
        return ligneCommandeAjoutee;
    }
    
}
