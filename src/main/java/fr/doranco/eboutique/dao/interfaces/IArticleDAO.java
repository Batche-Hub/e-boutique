/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.doranco.eboutique.dao.interfaces;

import fr.doranco.eboutique.entity.Article;
import java.util.List;

/**
 *
 * @author Boule
 */
public interface IArticleDAO {

    public List<Article> getArticles(Integer idUtilisateur) throws Exception;

    public Article addArticle(Article article, Integer idUtilisateur) throws Exception;

    public void removeArticle(Integer id) throws Exception;

    public void clear(Integer idUtilisateur) throws Exception;
}
