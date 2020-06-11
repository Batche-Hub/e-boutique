/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.doranco.eboutique.dao;



import fr.doranco.eboutique.dao.interfaces.IAdresseDAO;
import fr.doranco.connexion.SecuriteDataSource;
import fr.doranco.eboutique.entity.Adresse;
import fr.doranco.eboutique.entity.Utilisateur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author Boule
 */
public class AdresseDAO implements IAdresseDAO {

    //Constructeur
    public AdresseDAO() {
    }
    

    @Override
    public Adresse getAdresseByIdUtilisateur(Integer idUtilisateur) throws Exception{

        Adresse adresse = null;
        Connection connexion = SecuriteDataSource.getInstance().getConnection();
   
        PreparedStatement ps = null;
        try {
            String requete = "SELECT * FROM adresse WHERE utilisateur_id = ?";
            ps = connexion.prepareStatement(requete);
            ps.setInt(1, idUtilisateur);
                        
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                adresse = new Adresse();
                adresse.setId(rs.getInt("id"));
                adresse.setNumeroRue(rs.getInt("numero"));
                adresse.setNomRue(rs.getString("rue"));
                adresse.setNomVille(rs.getString("ville"));
                adresse.setCodePostal(rs.getInt("code_postal"));
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
        return adresse;
    }

    public void addAdresse(Adresse adresse, Integer idUtilisateur) throws Exception{
              
        Utilisateur utilisateurDOJO = null;
        Adresse adresseAjoutee = null;
        Connection connexion = SecuriteDataSource.getInstance().getConnection();
   
        PreparedStatement ps = null;
        try {
            String requete = "INSERT INTO adresse(numero, rue, ville, code_postal, utilisateur_id) VALUE(?,?,?,?,?)";
            ps = connexion.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, adresse.getNumeroRue());
            ps.setString(2, adresse.getNomRue());
            ps.setString(3, adresse.getNomVille());
            ps.setInt(4, adresse.getCodePostal());
            ps.setInt(5, idUtilisateur);
            ps.executeUpdate();

            ResultSet resultSet = ps.getGeneratedKeys();
            adresseAjoutee = adresse;
            if (resultSet.next()) {
                utilisateurDOJO = new Utilisateur();
                Integer idAdresse = resultSet.getInt(1);
                adresseAjoutee.setId(idAdresse);
                utilisateurDOJO.setAdresse(adresseAjoutee);
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
    }

    @Override
    public void updateAdresse(Adresse adresse) throws Exception {
        Connection connexion = SecuriteDataSource.getInstance().getConnection();
        String requete = "UPDATE adresse SET numero = ?, rue=?, ville=?, code_postal= ? WHERE id=?";
        PreparedStatement ps = null;
        try {
            ps = connexion.prepareStatement(requete);

            ps.setInt(1, adresse.getNumeroRue());
            ps.setString(2, adresse.getNomRue());
            ps.setString(3, adresse.getNomVille());
            ps.setInt(4, adresse.getCodePostal());
            ps.setInt(5, adresse.getId());
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
    public void removeAdresse(Integer id) throws Exception {
        Connection connexion = SecuriteDataSource.getInstance().getConnection();

        String requete = "DELETE FROM adresse WHERE id = " + id;

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
