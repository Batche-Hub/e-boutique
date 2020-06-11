/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.doranco.eboutique.vue;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileSystemView;
import org.apache.maven.shared.utils.io.FileUtils;

/**
 *
 * @author Boule
 */
public class NewClass extends JFrame implements ActionListener{
    static JLabel l; 
  
    // a default constructor 
    NewClass() 
    { 
    } 
  
    public static void main(String args[]) 
    { 
        // frame to contains GUI elements 
        JFrame f = new JFrame("file chooser"); 
  
        // set the size of the frame 
        f.setSize(400, 400); 
  
        // set the frame's visibility 
        f.setVisible(true); 
  
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
  
        // button to open save dialog 
        JButton button1 = new JButton("save"); 
  
        // button to open open dialog 
        JButton button2 = new JButton("open"); 
  
        // make an object of the class filechooser 
        NewClass f1 = new NewClass(); 
  
        // add action listener to the button to capture user 
        // response on buttons 
        button1.addActionListener(f1); 
        button2.addActionListener(f1); 
  
        // make a panel to add the buttons and labels 
        JPanel p = new JPanel(); 
  
        // add buttons to the frame 
        p.add(button1); 
        p.add(button2); 
  
        // set the label to its initial value 
        l = new JLabel("no file selected"); 
  
        // add panel to the frame 
        p.add(l); 
        f.add(p); 
  
        f.show(); 
    } 
    public void actionPerformed(ActionEvent evt) 
    { 
        // if the user presses the save button show the save dialog 
        String com = evt.getActionCommand(); 
  
        if (com.equals("save")) { 
            // create an object of JFileChooser class 
            JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); 
  
            // invoke the showsSaveDialog function to show the save dialog 
            int r = j.showSaveDialog(null); 
  
            // if the user selects a file 
            if (r == JFileChooser.APPROVE_OPTION) 
  
            { 
                // set the label to the path of the selected file
//                String url = j.getSelectedFile().getAbsolutePath();
                
                
//                ImageIcon imageIcon = new ImageIcon(j.getSelectedFile().getAbsolutePath());
//                ImageIcon img = scaleImage(imageIcon, imageIcon.getIconWidth(), imageIcon.getIconHeight());
//                Image image = img.getImage();
//                Image newimg = image.getScaledInstance(180, 190,  java.awt.Image.SCALE_SMOOTH);
                File f = new File(j.getSelectedFile().getAbsolutePath());
                File USERPIC_DIRECTORY = new File(System.getProperty("user.home") + "\\AppData\\LocalLow\\E-boutique\\UserPictures\\");
                
                try {
                    FileUtils.copyFileToDirectory(f, USERPIC_DIRECTORY);
                    
                    File f1 = new File (USERPIC_DIRECTORY.getAbsolutePath()+"\\"+f.getName());
                    ImageIcon newimg = rescaleImage(f1, 80, 90);
                    l.setIcon(newimg);
                    System.err.println(f1.getName()+f1.getAbsolutePath());
                } catch (IOException ex) {
                    Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                
                
            } 
            // if the user cancelled the operation 
            else
                l.setText("the user cancelled the operation"); 
        } 
  
        // if the user presses the open dialog show the open dialog 
        else { 
            // create an object of JFileChooser class 
            JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); 
  
            // invoke the showsOpenDialog function to show the save dialog 
            int r = j.showOpenDialog(null); 
  
            // if the user selects a file 
            if (r == JFileChooser.APPROVE_OPTION) 
  
            { 
                // set the label to the path of the selected file 
                l.setText(j.getSelectedFile().getAbsolutePath()); 
            } 
            // if the user cancelled the operation 
            else
                l.setText("the user cancelled the operation"); 
        } 
    }
    
    public ImageIcon scaleImage(ImageIcon icon, int w, int h)
    {
        int nw = icon.getIconWidth();
        int nh = icon.getIconHeight();

        if(icon.getIconWidth() > w)
        {
          nw = w;
          nh = (nw * icon.getIconHeight()) / icon.getIconWidth();
        }

        if(nh > h)
        {
          nh = h;
          nw = (icon.getIconWidth() * nh) / icon.getIconHeight();
        }

        return new ImageIcon(icon.getImage().getScaledInstance(nw, nh, Image.SCALE_DEFAULT));
    }
    
    public ImageIcon rescaleImage(File source,int maxHeight, int maxWidth)
{
    int newHeight = 0, newWidth = 0;        // Variables for the new height and width
    int priorHeight = 0, priorWidth = 0;
    BufferedImage image = null;
    ImageIcon sizeImage;

    try {
            image = ImageIO.read(source);        // get the image
    } catch (Exception e) {

            e.printStackTrace();
            System.out.println("Picture upload attempted & failed");
    }

    sizeImage = new ImageIcon(image);

    if(sizeImage != null)
    {
        priorHeight = sizeImage.getIconHeight(); 
        priorWidth = sizeImage.getIconWidth();
    }

    // Calculate the correct new height and width
    if((float)priorHeight/(float)priorWidth > (float)maxHeight/(float)maxWidth)
    {
        newHeight = maxHeight;
        newWidth = (int)(((float)priorWidth/(float)priorHeight)*(float)newHeight);
    }
    else 
    {
        newWidth = maxWidth;
        newHeight = (int)(((float)priorHeight/(float)priorWidth)*(float)newWidth);
    }


    // Resize the image

    // 1. Create a new Buffered Image and Graphic2D object
    BufferedImage resizedImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
    Graphics2D g2 = resizedImg.createGraphics();

    // 2. Use the Graphic object to draw a new image to the image in the buffer
    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    g2.drawImage(image, 0, 0, newWidth, newHeight, null);
    g2.dispose();

    // 3. Convert the buffered image into an ImageIcon for return
    return (new ImageIcon(resizedImg));
}
}
