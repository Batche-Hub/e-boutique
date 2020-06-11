/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.doranco.eboutique.vue;

import fr.doranco.eboutique.control.CarteControl;
import fr.doranco.eboutique.control.UtilisateurControl;
import fr.doranco.eboutique.dao.UtilisateurDAO;
import fr.doranco.eboutique.entity.CartePaiement;
import fr.doranco.eboutique.entity.Utilisateur;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.FileSystems;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author Boule
 */
public class Main {
    
    public static void main(String[] args) throws Exception {

            UtilisateurControl.getUtilisateurById(42);

            

                    
JFrame frame = buildFrame();

        final BufferedImage image = ImageIO.read(new File("C:\\Users\\Boule\\Documents\\NetBeansProjects\\e-boutique\\src\\main\\resources\\icons8-android-48.png"));

        JPanel pane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, null);
            }
        };


        frame.add(pane);
    }


    private static JFrame buildFrame() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(200, 200);
        frame.setVisible(true);
        return frame;
    }

//        CartePaiement carteP = new CartePaiement();
//        carteP.setDateValidite("06/06/1920");
//        carteP.setCryptograme("123");
//        carteP.setNumeroCarte("123456789");
//        
//        UtilisateurDAO utDAO = new UtilisateurDAO();
//        
//        Utilisateur ut = utDAO.getUtilisateurById(42);
//        CarteControl.addCartePaiement(carteP, 42);
//        
//        List<CartePaiement> listCarte = CarteControl.getListeCartesPaiement(42);
//        
//        System.err.println(ut.getTelephone());
//        CartePaiement carte = listCarte.get(0);
//        System.err.println(carte);
    }

