/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.doranco.eboutique.dao;

import fr.doranco.connexion.SecuriteDataSource;
import fr.doranco.eboutique.dao.interfaces.ICartePaiementDAO;
import fr.doranco.eboutique.entity.CartePaiement;
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
public class CartePaiementDAO implements ICartePaiementDAO {

    public CartePaiementDAO() {
    }

    
    @Override
    public CartePaiement getCartePaiement(Integer id) throws Exception {
        CartePaiement cartePaiement = null;
        Connection connexion = SecuriteDataSource.getInstance().getConnection();

        PreparedStatement ps = null;
        try {
            String requete = "SELECT * FROM carte_paiement WHERE id = ?";
            ps = connexion.prepareStatement(requete);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                cartePaiement = new CartePaiement();
                cartePaiement.setId(rs.getInt("id"));
                cartePaiement.setDateValidite(rs.getString("date_validite"));
                cartePaiement.setNumeroCarte(rs.getString("numero_carte"));
                cartePaiement.setCryptograme(rs.getString("cryptogramme"));

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
        return cartePaiement;
    }

    @Override
    public List<CartePaiement> getCartesPaiement(Integer idUtilisateur) throws Exception {
        List<CartePaiement> listeCartes = new ArrayList<>();

        Connection connexion = SecuriteDataSource.getInstance().getConnection();

        String requete = "SELECT * FROM carte_paiement WHERE utilisateur_carte_paiement_id = ?";
        PreparedStatement ps = null;
        try {
            ps = connexion.prepareStatement(requete);
            ps.setInt(1, idUtilisateur);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                CartePaiement cartePaiement = new CartePaiement();

                cartePaiement.setId(rs.getInt("id"));
                cartePaiement.setDateValidite(rs.getString("date_validite"));
                cartePaiement.setNumeroCarte(rs.getString("numero_carte"));
                cartePaiement.setCryptograme(rs.getString("cryptogramme"));
                listeCartes.add(cartePaiement);

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

        return listeCartes;
    }

    @Override
    public void addCartePaiement(CartePaiement cartePaiement, Integer idUtilisateur) throws Exception {
        Utilisateur utilisateurDOJO = null;
        CartePaiement cartePaiementAjoutee = null;
        Connection connexion = SecuriteDataSource.getInstance().getConnection();

        PreparedStatement ps = null;
        try {
            String requete = "INSERT INTO carte_paiement(date_validite, numero_carte, cryptogramme, utilisateur_carte_paiement_id) VALUE(?,?,?,?)";
            ps = connexion.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, cartePaiement.getDateValidite());
            ps.setString(2, cartePaiement.getNumeroCarte());
            ps.setString(3, cartePaiement.getCryptograme());
            ps.setInt(4, idUtilisateur);
            ps.executeUpdate();

            ResultSet resultSet = ps.getGeneratedKeys();
            cartePaiementAjoutee = cartePaiement;
            if (resultSet.next()) {
                Integer idCarte = resultSet.getInt(1);
                cartePaiementAjoutee.setId(idCarte);

            }
        } catch (Exception e) {
            System.out.println("Une erreur est survenue Add Carte Paiment: " + e);
        } finally {
            if (connexion != null) {
                try {
                    connexion.close();
                } catch (SQLException ex) {
                    System.err.println("Une erreur Sql est survenue Add Carte Paiment:: " + ex);
                }
            }
        }
    }

    @Override
    public void updateCartePaiment(CartePaiement cartePaiement) throws Exception {
        Connection connexion = SecuriteDataSource.getInstance().getConnection();
        String requete = "UPDATE carte_paiement SET date_validite = ?, numero_carte=?, cryptogramme=? WHERE id=?";
        PreparedStatement ps = null;
        try {
            ps = connexion.prepareStatement(requete);

            ps.setString(1, cartePaiement.getDateValidite());
            ps.setString(2, cartePaiement.getNumeroCarte());
            ps.setString(3, cartePaiement.getNumeroCarte());
            ps.setInt(4, cartePaiement.getId());
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
    public void removeCartePaiement(Integer id) throws Exception {
        Connection connexion = SecuriteDataSource.getInstance().getConnection();

        String requete = "DELETE FROM carte_paiement WHERE id = " + id;

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
