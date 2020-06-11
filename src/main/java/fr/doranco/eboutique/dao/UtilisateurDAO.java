/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.doranco.eboutique.dao;



import fr.doranco.eboutique.dao.interfaces.IUtilisateurDAO;
import fr.doranco.connexion.SecuriteDataSource;
import fr.doranco.eboutique.entity.Utilisateur;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author Boule
 */
public class UtilisateurDAO implements IUtilisateurDAO {

    //Constructeur
    public UtilisateurDAO() {
    }
    
    @Override
    public Utilisateur getUtilisateurByEmail(String email) throws Exception{
        
        Utilisateur utilisateur = null;

        Connection connexion = SecuriteDataSource.getInstance().getConnection();
   
        PreparedStatement ps = null;
        try {
            utilisateur = new Utilisateur();
            
            String requete = "SELECT * FROM utilisateur WHERE email = ?";
            ps = connexion.prepareStatement(requete);
            ps.setString(1, email);
                        
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) { 
                utilisateur.setId(rs.getInt("id"));
                utilisateur.setNom(rs.getString("nom"));
                utilisateur.setPrenom(rs.getString("prenom"));
                utilisateur.setAge(rs.getInt("age"));
                utilisateur.setEmail(rs.getString("email"));
                utilisateur.setTelephone(rs.getString("telephone"));
                utilisateur.setMotDepasse(rs.getBytes("password"));
                utilisateur.setCle(rs.getBytes("cle"));
                utilisateur.setIsActive(rs.getBoolean("is_active"));
                utilisateur.setPhotoProfil(rs.getString("photo_profil"));
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
        return utilisateur;
    }

    public Utilisateur getUtilisateurById(Integer id) throws Exception{
              
        Utilisateur utilisateur = null;
        Connection connexion = SecuriteDataSource.getInstance().getConnection();
   
        PreparedStatement ps = null;
        try {
            utilisateur = new Utilisateur();
            
            String requete = "SELECT * FROM utilisateur WHERE id = ?";
            ps = connexion.prepareStatement(requete);
            ps.setInt(1, id);
                        
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) { 
                utilisateur.setId(rs.getInt("id"));
                utilisateur.setNom(rs.getString("nom"));
                utilisateur.setPrenom(rs.getString("prenom"));
                utilisateur.setAge(rs.getInt("age"));
                utilisateur.setEmail(rs.getString("email"));
                utilisateur.setTelephone(rs.getString("telephone"));
                utilisateur.setMotDepasse(rs.getBytes("password"));
                utilisateur.setCle(rs.getBytes("cle"));
                utilisateur.setIsActive(rs.getBoolean("is_active"));
                utilisateur.setPhotoProfil(rs.getString("photo_profil"));
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
        return utilisateur;  
    }

    @Override
    public Utilisateur addUtilisateur(Utilisateur utilisateurDOJO) throws Exception{
        Utilisateur utilisateurAjoute = null;
        Connection connexion = SecuriteDataSource.getInstance().getConnection();
        
        PreparedStatement ps = null;        
        try {
            String requete = "INSERT INTO utilisateur(nom, prenom, age, email, role, is_online, password, cle, telephone, is_active, photo_profil) VALUE(?,?,?,?,?,?,?,?,?,?,?)";
            ps = connexion.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
            System.err.println("Nom");
            ps.setString(1, utilisateurDOJO.getNom());
            
            System.err.println("Prénom");
            ps.setString(2, utilisateurDOJO.getPrenom());
            
            ps.setInt(3, utilisateurDOJO.getAge());
            
            System.err.println("Email");
            ps.setString(4, utilisateurDOJO.getEmail());
            
            System.err.println("ROLE");
            ps.setString(5, utilisateurDOJO.getRole().getDescription());
            
            System.err.println("Online");
            ps.setBoolean(6, utilisateurDOJO.getIsOnline());
            
            System.err.println("MDP");
            ps.setBytes(7, utilisateurDOJO.getMotDepasse());
            
            System.err.println("Clé : "+utilisateurDOJO.getCle());
            ps.setBytes(8, utilisateurDOJO.getCle());
            
            ps.setString(9, utilisateurDOJO.getTelephone());
            
            ps.setBoolean(10, utilisateurDOJO.getIsActive());

            ps.setString(11, utilisateurDOJO.getPhotoProfil());
            
            ps.executeUpdate();
            utilisateurAjoute = utilisateurDOJO;
            ResultSet resultSet = ps.getGeneratedKeys();

            if (resultSet.next()) {
                Integer id = resultSet.getInt(1);
                utilisateurAjoute.setId(id);
            }

        } catch (Exception e) {
            System.err.println("Une erreur de connexion est survenue dans la requête concernant Add utilisateur." + e);
        } finally {
            if (connexion != null) {
                try {
                    connexion.close();
                } catch (SQLException ex) {
                   System.err.println("Une erreur SQL est survenue : "+ex);
                }
            }
        }
        return utilisateurAjoute;
    }

    @Override
    public void updateTelephoneUtilisateur(Utilisateur utilisateur) throws Exception {
        Connection connexion = SecuriteDataSource.getInstance().getConnection();
        String requete = "UPDATE utilisateur SET telephone = ? WHERE id=?";
        PreparedStatement ps = null;
        try {
            ps = connexion.prepareStatement(requete);

            ps.setString(1, utilisateur.getTelephone());
            ps.setInt(2, utilisateur.getId()); 
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
    public void updateIsActiveUtilisateur(Utilisateur utilisateur) throws Exception {
       Connection connexion = SecuriteDataSource.getInstance().getConnection();
        String requete = "UPDATE utilisateur SET is_active = ? WHERE id=?";
        PreparedStatement ps = null;
        try {
            ps = connexion.prepareStatement(requete);

            ps.setBoolean(1, utilisateur.getIsActive());
            ps.setInt(2, utilisateur.getId()); 

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

    

    

}
