/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.doranco.eboutique.dao;

import fr.doranco.connexion.SecuriteDataSource;
import fr.doranco.eboutique.dao.interfaces.IProduitDAO;
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
public class ProduitDAO implements IProduitDAO{

    public ProduitDAO() {
    }

    @Override
    public Produit getProduit(Integer id) throws Exception {
        Produit produit = null;
        Connection connexion = SecuriteDataSource.getInstance().getConnection();

        PreparedStatement ps = null;
        try {
            String requete = "SELECT * FROM produit WHERE id = ?";
            ps = connexion.prepareStatement(requete);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                produit = new Produit();
                produit.setId(rs.getInt("id"));
                produit.setNom(rs.getString("nom"));
                produit.getCategorie().setDescription(rs.getString("categorie"));
                produit.setDescription(rs.getString("description"));
                produit.setPrix(rs.getFloat("prix"));
                produit.setRemise(rs.getInt("remise"));
            }
        } catch (Exception e) {
            System.out.println("Une erreur est survenue : " + e);
            return null;
        } finally {
            if (connexion != null) {
                try {
                    connexion.close();
                } catch (SQLException ex) {
                    System.err.println("Une erreur Sql est survenue : " + ex);
                }
            }
        }
        return produit;
    }

    @Override
    public List<Produit> getProduitsByCategorie(String categorie) throws Exception {
        List<Produit> listeProduits = new ArrayList<>();

        Connection connexion = SecuriteDataSource.getInstance().getConnection();

        String requete = "SELECT * FROM produit WHERE categorie = ?";
        PreparedStatement ps = null;
        try {
            ps = connexion.prepareStatement(requete);
            ps.setString(1, categorie);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Produit produit = new Produit();

                produit.setId(rs.getInt("id"));
                produit.setNom(rs.getString("nom"));
                produit.getCategorie().setDescription(rs.getString("categorie"));
                produit.setDescription(rs.getString("description"));
                produit.setPrix(rs.getFloat("prix"));
                produit.setRemise(rs.getInt("remise"));
                listeProduits.add(produit);

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

        return listeProduits;
    }

    @Override
    public Produit addProduit(Produit produit) throws Exception {
        Produit produitAjoute = null;
        Connection connexion = SecuriteDataSource.getInstance().getConnection();

        PreparedStatement ps = null;
        try {
            String requete = "INSERT INTO produit(nom, categorie, description, prix,remise) VALUE(?,?,?,?,?)";
            ps = connexion.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, produit.getNom());
            ps.setString(2, produit.getCategorie().getDescription());
            ps.setString(3, produit.getDescription());
            ps.setFloat(4, produit.getPrix());
            ps.setInt(5, produit.getRemise());
            ps.executeUpdate();

            ResultSet resultSet = ps.getGeneratedKeys();
            produitAjoute = produit;
            if (resultSet.next()) {
                Integer idProduit = resultSet.getInt(1);
                produitAjoute.setId(idProduit);
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
        return produitAjoute;
    }

    @Override
    public void updateProduit(Produit produit) throws Exception {
        Connection connexion = SecuriteDataSource.getInstance().getConnection();
        String requete = "UPDATE produit SET nom = ?, categorie=?, description=?, prix = ?, remise = ? WHERE id=?";
        PreparedStatement ps = null;
        try {
            ps = connexion.prepareStatement(requete);

            ps.setString(1, produit.getNom());
            ps.setString(2, produit.getCategorie().getDescription());
            ps.setString(3, produit.getDescription());
            ps.setFloat(4, produit.getPrix());
            ps.setInt(5, produit.getRemise());
            ps.setInt(6, produit.getId());
            ps.executeUpdate();;
            // Je mets à jour
            ps.executeUpdate();
        } catch (Exception e) {
            System.err.println("Une erreur de connexion est survenue." + e);
        } finally {
            if (connexion != null) {
                try {
                    connexion.close();
                } catch (SQLException ex) {
                    System.err.println("Une erreur SQL est survenue : " + ex);
                }
            }
        }
    }

    @Override
    public void removeProduit(Integer id) throws Exception {
        Connection connexion = SecuriteDataSource.getInstance().getConnection();

        String requete = "DELETE FROM produit WHERE id = " + id;

        try {
            PreparedStatement ps = connexion.prepareStatement(requete);
            ps.executeUpdate();
        } catch (Exception e) {
            System.err.println("Une erreur de connexion est survenue." + e);
        } finally {
            if (connexion != null) {
                try {
                    connexion.close();
                } catch (SQLException ex) {
                    System.err.println("Erreur de connexion SQL : " + ex);
                }
            }
        }
    }
    
}
