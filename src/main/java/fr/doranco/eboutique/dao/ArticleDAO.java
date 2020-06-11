/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.doranco.eboutique.dao;

import fr.doranco.connexion.SecuriteDataSource;
import fr.doranco.eboutique.dao.interfaces.IArticleDAO;
import fr.doranco.eboutique.entity.Article;
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
public class ArticleDAO implements IArticleDAO {

    public ArticleDAO() {
    }

    @Override
    public List<Article> getArticles(Integer idUtilisateur) throws Exception {
        List<Article> listeArticles = new ArrayList<>();

        Connection connexion = SecuriteDataSource.getInstance().getConnection();

        
        PreparedStatement ps = null;
        try {
            String requete = "SELECT art.quantite quantite_produit_article, p.*  FROM produit p INNER JOIN article art ON art.produit_article_id = p.id WHERE art.utilisateur_article_id = ?";
            ps = connexion.prepareStatement(requete);
            ps.setInt(1, idUtilisateur);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Produit produit = new Produit();
				Article article = new Article();
				produit.setNom(rs.getString("p.nom"));
				produit.setDescription(rs.getString("p.description"));
				produit.setPrix(rs.getFloat("p.prix"));
				produit.setRemise(rs.getInt("p.remise"));
				produit.getCategorie().setDescription(rs.getString("p.categorie"));
				article.setQuantite(rs.getInt("quantite_produit_article"));
			
				article.setProduit(produit);

				listeArticles.add(article);

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

        return listeArticles;
    }

    @Override
    public Article addArticle(Article article, Integer idUtilisateur) throws Exception {
        Article articleAjoute = null;
        Connection connexion = SecuriteDataSource.getInstance().getConnection();

        PreparedStatement ps = null;
        try {
            String request = "INSERT INTO article(produit_article_id, quantite_produit_article, utilisateur_article_id) VALUE (?, ?, ?)";
            ps = connexion.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, article.getProduit().getId());
            ps.setInt(2, article.getQuantite());
            ps.setInt(3, idUtilisateur);

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            System.out.println(rs.toString());
            if (rs.next()) {
                article.setId(rs.getInt(1));
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
        return articleAjoute;
    }

    @Override
    public void removeArticle(Integer id) throws Exception {
        Connection connexion = SecuriteDataSource.getInstance().getConnection();

        PreparedStatement ps = null;

        try {
            String requete = "DELETE FROM article WHERE id = " + id;
            ps = connexion.prepareStatement(requete);
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

    @Override
    public void clear(Integer idUtilisateur) throws Exception {
        Connection connexion = SecuriteDataSource.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String requete = "DELETE FROM article WHERE utilisateur_article_id=?";
            ps = connexion.prepareStatement(requete);

            ps.setInt(1, idUtilisateur);
            
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
